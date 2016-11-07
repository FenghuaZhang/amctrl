package com.chinatele.app.amctrl.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.chinatele.app.amctrl.rest.exception.AmCtrlException;
import com.chinatele.app.amctrl.rest.na.RequestAppClient;
import com.chinatele.app.amctrl.rest.vo.DeviceDown;
import com.chinatele.app.amctrl.rest.vo.ResponseVo;
import com.chinatele.app.amctrl.rest.vo.request.AllocateBlock;
import com.chinatele.app.amctrl.rest.vo.request.ConfigInfo;
import com.chinatele.app.amctrl.rest.vo.request.Device;
import com.chinatele.app.amctrl.rest.vo.request.DeviceConfigInfo;
import com.chinatele.app.amctrl.rest.vo.request.RecycleBlock;
import com.chinatele.app.amctrl.rest.vo.request.ReleaseAddrPoolInfo;
import com.chinatele.app.amctrl.rest.vo.response.AllocateRecycleAddress;
import com.chinatele.app.amctrl.rest.vo.response.AllocateRecyclePool;
import com.chinatele.app.amctrl.rest.vo.response.ControllerState;
import com.chinatele.app.amctrl.rest.vo.response.ReportAddressBlockConfirm;
import com.chinatele.app.amctrl.rest.vo.response.ReportDeviceState;
import com.chinatele.app.amctrl.rest.vo.response.ReportPoolStatus;
import com.chinatele.app.amctrl.service.ControllerService;
import com.chinatele.app.amctrl.service.NetconfDeviceService;
import com.chinatele.app.amctrl.service.dto.BlockLackingNotification;
import com.chinatele.app.amctrl.service.dto.ConfirmInfo;
import com.chinatele.app.amctrl.service.dto.DeviceDownNotification;
import com.chinatele.app.amctrl.service.dto.DeviceState;
import com.chinatele.app.amctrl.service.dto.HeartBeatNotification;
import com.chinatele.app.amctrl.service.dto.IPv4ConfigConfirmNotification;
import com.chinatele.app.amctrl.service.dto.IPv4RecycleConfirmNotification;
import com.chinatele.app.amctrl.service.dto.IPv6ConfigConfirmNotification;
import com.chinatele.app.amctrl.service.dto.IPv6RecycleConfirmNotification;
import com.chinatele.app.amctrl.service.dto.PoolStatusNotification;
import com.chinatele.app.amctrl.service.dto.RegisterConfirmNotifaction;
import com.chinatele.app.amctrl.util.ConfigUtil;
import com.chinatele.app.amctrl.util.Constants;
import com.chinatele.app.amctrl.util.JaxbUtil;

/**
 * @author zhangfh
 *
 */
public class ControllerServiceImpl implements ControllerService, MessageListener {

    private Logger log = LoggerFactory.getLogger(ControllerServiceImpl.class);

    @Autowired
    private NetconfDeviceService netconfDeviceService;

    /** 设备列表同步锁 */
    private byte[] deviceIdLock = new byte[0];

    /** 设备id与设备的对应关系(key:deviceId, value:device object) */
    private Map<Integer, Device> idObjectMap = new HashMap<Integer, Device>();

    /** 设备存活状态信息 */
    private Map<Integer, List<DeviceState>> deviceAliveStateMap = new HashMap<Integer, List<DeviceState>>();

    /** 设备连接线程自增线程号 */
    private AtomicInteger threadNumber = new AtomicInteger();

    @Override
    public ResponseVo config(ConfigInfo configInfo) throws AmCtrlException{
        ResponseVo vo = new ResponseVo();
        if (configInfo.getDevice_list() == null) {
            throw new AmCtrlException(Constants.RETURN_CODE_LACK_KEY_PARAMETER_ERROR, Constants.LACK_KEY_PARAMETER_ERROR_MESSAGE);
        } else {
            // 检查所连接的设备是否已经连接过
            boolean flag = checkDeviceRepeatConnected(configInfo);
            if (!flag) {
                // 直接把设备添加到列表中，但是设备状态为“连接中”
                synchronized (deviceIdLock) {
                	log.info("start configure device param...");
                    for (Device device : configInfo.getDevice_list()) {
                        device.setDevice_keep_alive_interval(configInfo.getDevice_keep_alive_interval());
                        idObjectMap.put(device.getDevice_id(), device);
                        // 设备状态
                        DeviceState deviceState = new DeviceState();
                        deviceState.setIsAlive(Constants.DEVICE_STATE_CONNECTING);
                        deviceState.setDeviceId(device.getDevice_id());
                        deviceState.setTime(new Date(System.currentTimeMillis()));
                        List<DeviceState> deviceStateList = deviceAliveStateMap.get(device.getDevice_id());
                        if (deviceStateList == null) {
                            deviceStateList = new ArrayList<DeviceState>();
                            deviceAliveStateMap.put(device.getDevice_id(), deviceStateList);
                        }  
                        deviceStateList.add(deviceState);
                    }
                }
                log.info("end configure device param...");
                // 异步操作
                Thread thread = new Thread(new ConfigThread(configInfo));
                thread.setName("thread-device-config-" + threadNumber.getAndIncrement());
                thread.start();
                vo.setMessage(Constants.RETURN_MESSAGE_SUCCESS);
            } else {
                vo.setReturn_code(Constants.RETURN_CODE_BUSINESS_ERROR);
                vo.setMessage(Constants.RETURN_MESSAGE_FAIL);
            }
            return vo;
        }
    }

    private boolean checkDeviceRepeatConnected(ConfigInfo configInfo) {
        boolean flag = false;
        synchronized (deviceIdLock) {
        	log.info("start checkDeviceRepeatConnected...");
            Set<Integer> deviceIdSet = idObjectMap.keySet();
            for (Device device : configInfo.getDevice_list()) {
                if (deviceIdSet.contains(device.getDevice_id())) {
                    flag = true;
                    break;
                }
            }
        }
        log.info("end checkDeviceRepeatConnected...");
        return flag;
    }

    private class ConfigThread implements Runnable {

        private ConfigInfo configInfo;

        public ConfigThread(ConfigInfo configInfo) {
            this.configInfo = configInfo;
        }

        @Override
        public void run() {
        	log.info("Start ControllerServiceImpl:ConfigThread::run() ...");
        	long start = System.currentTimeMillis();
            for (Device device : configInfo.getDevice_list()) {
                DeviceConfigInfo deviceConfigInfo = new DeviceConfigInfo();
                deviceConfigInfo.setDevice_id(device.getDevice_id());
                deviceConfigInfo.setDevice_ip(device.getDevice_ip());
                deviceConfigInfo.setDevice_port(device.getDevice_port());
                deviceConfigInfo.setDevice_type(device.getDevice_type());
                deviceConfigInfo.setUserName(device.getUsername() != null ? device.getUsername() : Constants.DEFAULT_DEVICE_USER_NAME);
                deviceConfigInfo.setPassword(device.getPassword() != null ? device.getPassword() : Constants.DEFAULT_DEVICE_PASSWORD);
                deviceConfigInfo.setHeartbeat_interval(configInfo.getDevice_keep_alive_interval());
                deviceConfigInfo.setIpv4_threshold(configInfo.getIpv4_address_pool_usage_threshold());
                deviceConfigInfo.setIpv6_threshold(configInfo.getIpv6_address_pool_usage_threshold());
                deviceConfigInfo.setState_update_interval(configInfo.getState_update_interval());
                deviceConfigInfo.setDevice_sampling_interval(configInfo.getDevice_sampling_interval());
                log.info("build link with device[deviceId={}]", device.getDevice_id());
                boolean flag = netconfDeviceService.openConnection(deviceConfigInfo);
                if (flag) {
                    log.info("build link device[deviceId={}] success!", device.getDevice_id());
                    // 设置当前设备状态为“活着”状态
                	log.info("start set device alive...");
                	
                	// lock device in case that allocate address block while device state is not alive
                	synchronized(deviceIdLock) {
                        List<DeviceState> deviceStateList = deviceAliveStateMap.get(device.getDevice_id());
                        DeviceState deviceState = new DeviceState();
                        deviceState.setIsAlive(Constants.DEVICE_STATE_ALIVE);
                        deviceState.setDeviceId(device.getDevice_id());
                        deviceState.setTime(new Date(System.currentTimeMillis()));
                        deviceStateList.add(deviceState);
	                    log.info("end set device alive...");
                	}
            	} else {
                    log.error("can not build link with device[id={}]", device.getDevice_id());
                    // 表示设备连接失败，从列表中删除
                    synchronized(deviceIdLock) {
                    	log.info("device connect failed,start remove device...");
                        deviceAliveStateMap.remove(device.getDevice_id());
                        idObjectMap.remove(device.getDevice_id());
                    }
                    log.info("device connect failed,end remove device...");
                    boolean reportConnectionFailed = false;//是否上报设备id给app连接失败
                    if (reportConnectionFailed) {
                        int deviceId = deviceConfigInfo.getDevice_id();
                        try {
                            RequestAppClient.reportFailedDevice(deviceId);
                        } catch (Exception e) {
                            log.error("Device id with " + device.getDevice_id() + " connecting failed" + e);
                        }
                    }
                }
            }
            log.info("End ControllerServiceImpl:ConfigThread::run() with costed " + (System.currentTimeMillis()-start)	+ "millseconds");
        }

    }


    @Override
    public void onMessage(Message message) {
        log.info("收到一条消息：" + message);
        try {
            // 上报设备注册信息
			String xmlRegisterConfirmMessage = message
					.getStringProperty(Constants.Notification.MSG_ID_OF_AMA_REGISTER_TO_CONTROLLER);
            if (xmlRegisterConfirmMessage != null) {
                reportRegisterConfirm(xmlRegisterConfirmMessage);
            }

            // 上报地址池状态消息
			String xmlPoolStateMessage = message
					.getStringProperty(Constants.Notification.MSG_ID_OF_AMA_REPORT_ADDRESS_POOL_STATUS);
            if (xmlPoolStateMessage != null) {
                Thread thread = new ReportPoolStateThread(xmlPoolStateMessage);
                thread.start();
            }

            // 上报ipv4下发地址确认消息
			String xmlAddressBlockIpv4ConfigConfirm = message
					.getStringProperty(Constants.Notification.MSG_ID_OF_AMA_REPORT_ADDRESS_GOT_V4);
            if (xmlAddressBlockIpv4ConfigConfirm != null) {
                reportIpv4ConfigConfirm(xmlAddressBlockIpv4ConfigConfirm);
            }

            // 上报ipv4回收地址确认消息
			String xmlAddressBlockIpv4RecycleConfirm = message
					.getStringProperty(Constants.Notification.MSG_ID_OF_AMA_REPORT_ADDRESS_RECYCLED_V4);
            if (xmlAddressBlockIpv4RecycleConfirm != null) {
                reportIpv4RecycleConfirm(xmlAddressBlockIpv4RecycleConfirm);
            }

            // 上报ipv6下发地址确认消息
			String xmlAddressBlockIpv6ConfigConfirm = message
					.getStringProperty(Constants.Notification.MSG_ID_OF_AMA_REPORT_ADDRESS_GOT_V6);
            if (xmlAddressBlockIpv6ConfigConfirm != null) {
                reportIpv6ConfigConfirm(xmlAddressBlockIpv6ConfigConfirm);
            }

            // 上报ipv6回收地址确认消息
			String xmlAddressBlockIpv6RecycleConfirm = message
					.getStringProperty(Constants.Notification.MSG_ID_OF_AMA_REPORT_ADDRESS_RECYCLED_V6);
            if (xmlAddressBlockIpv6RecycleConfirm != null) {
                reportIpv6RecycleConfirm(xmlAddressBlockIpv6RecycleConfirm);
            }

            // 维护heart_beat消息
			String xmlHeartBeat = message
					.getStringProperty(Constants.Notification.MSG_ID_OF_AMA_REPORT_HEARTBEAT);
            if (xmlHeartBeat != null) {
                maintainDeviceAliveState(xmlHeartBeat);
            }
            
            // report block lacking notification to north side--app when notification from south side coming
			String xmlBlockLacking = message
					.getStringProperty(Constants.Notification.MSG_ID_OF_AMA_LACK_BLOCK);
            if (xmlBlockLacking != null) {
                reportBlockLacking(xmlBlockLacking);
            }
            
        } catch (JMSException e) {
            log.error("jms exception", e);
        }
    }

	private void reportRegisterConfirm(String xmlRegisterConfirmMessage) {
        RegisterConfirmNotifaction registerConfirmNotifaction = null;
        try {
            registerConfirmNotifaction = JaxbUtil.convertToJavaBean(xmlRegisterConfirmMessage, RegisterConfirmNotifaction.class);
        } catch (Exception e) {
            log.error("fail to convert xml string to java bean", e);
        }
        int deviceId = -1;
        try {
            if (registerConfirmNotifaction.getRegisterConfirm() != null) {
                deviceId = registerConfirmNotifaction.getRegisterConfirm().getDeviceId();
                RequestAppClient.reportNewDevice(deviceId);
            } else {
                log.error("message body of register confirm is empty!");
            }
        } catch (Exception e) {
            log.error("report device[id={}] to app failed", deviceId, e);
        }
    }

    private class ReportPoolStateThread extends Thread {
        private String xmlPoolStateMessage;

        public ReportPoolStateThread(String xmlPoolStateMessage) {
            this.xmlPoolStateMessage = xmlPoolStateMessage;
        }

        @Override
        public void run() {
            reportPoolState(xmlPoolStateMessage);
        }
    }

    private void reportPoolState(String xmlPoolStateMessage) {
        PoolStatusNotification poolStatusNotification = null;
        try {
            poolStatusNotification = JaxbUtil.convertToJavaBean(xmlPoolStateMessage, PoolStatusNotification.class);
        } catch (Exception e) {
            log.error("fail to convert xml string to java bean", e);
        }
        try {
            if (poolStatusNotification.getPoolStatus() != null) {
                ReportPoolStatus reportPoolStatus = new ReportPoolStatus();
                reportPoolStatus.convertFromDto(poolStatusNotification.getPoolStatus());
                ResponseVo responseVo = RequestAppClient.reportPoolState(reportPoolStatus);
                // 根据反馈信息判断下发地址块或者回收地址块
                if (responseVo != null && responseVo.getResult() != null) {
                    AllocateRecycleAddress allocateRecycleAddress = (AllocateRecycleAddress)responseVo.getResult();
                    allocateOrRecycleAddressBlock(allocateRecycleAddress);
                }
            } else {
                log.error("message body of pool status is empty!");
            }
        } catch (Exception e) {
            log.error("report pool state message to app failed", e);
        }
    }

    private void allocateOrRecycleAddressBlock(AllocateRecycleAddress allocateRecycleAddress) throws Exception {
        int deviceId = allocateRecycleAddress.getDevice_id();
        String deviceIp = null;
        synchronized(deviceIdLock) {
        	log.info("start allocateOrRecycleAddressBlock...");
            if (idObjectMap.get(deviceId) != null) {
                deviceIp = idObjectMap.get(deviceId).getDevice_ip();
                // 查看当前的session是否有效
                boolean flag = netconfDeviceService.validateSession(deviceId, deviceIp);
                if (flag) {
                    if (allocateRecycleAddress.getAddress_pools() == null || allocateRecycleAddress.getAddress_pools().isEmpty()) {
                        log.error("there is no any address block to allocate or recycle");
                    } else {
                        for (AllocateRecyclePool allocateRecyclePool : allocateRecycleAddress.getAddress_pools()) {
                            // 回收地址块
                            if (allocateRecyclePool.getLeasing_time() == 0) {
                                RecycleBlock recycleBlock = new RecycleBlock();
                                recycleBlock.setDevice_id(allocateRecycleAddress.getDevice_id());
                                recycleBlock.setProtocol_type(allocateRecyclePool.getProtocol_type());
                                recycleBlock.setAddress_pool_id(allocateRecyclePool.getAddress_pool_id());
                                recycleBlock.setAddress_pool_name(allocateRecyclePool.getAddress_pool_name());
                                recycleBlock.setAddress_block_id(allocateRecyclePool.getAddress_block_id());
                                recycleBlock.setAddress_block_name(allocateRecyclePool.getAddress_block_name());
                                netconfDeviceService.recycleAddress(recycleBlock, idObjectMap);
                            } else {
                                // 下发地址块
                                AllocateBlock allocateBlock = new AllocateBlock();
                                allocateBlock.setDevice_id(allocateRecycleAddress.getDevice_id());
                                allocateBlock.setTime(allocateRecyclePool.getTime());
                                allocateBlock.setDomain_name(allocateRecyclePool.getDomain_name());
                                allocateBlock.setProtocol_type(allocateRecyclePool.getProtocol_type());
                                allocateBlock.setAddress_pool_id(allocateRecyclePool.getAddress_pool_id());
                                allocateBlock.setAddress_pool_name(allocateRecyclePool.getAddress_pool_name());
                                allocateBlock.setAddress_block_id(allocateRecyclePool.getAddress_block_id());
                                allocateBlock.setAddress_block_name(allocateRecyclePool.getAddress_block_name());
                                allocateBlock.setIp_prefix(allocateRecyclePool.getIp_prefix());
                                allocateBlock.setIp_prefix_length(allocateRecyclePool.getIp_prefix_length());
                                allocateBlock.setUsergateway(allocateRecyclePool.getUsergateway());
                                allocateBlock.setGwnetmask(allocateRecyclePool.getGwnetmask());
                                // dns_server_list的切割
                                String dnsServerList = allocateRecyclePool.getDns_list();
                                if (dnsServerList != null) {
                                    if (dnsServerList.contains(Constants.DNS_SPLIT_FLAG)) {
                                        String[] dnsList = dnsServerList.split(Constants.DNS_SPLIT_FLAG);
                                        allocateBlock.setPrimary_dns(dnsList[0]);
                                        if (dnsList[1] != null) {
                                            allocateBlock.setSecondary_dns(dnsList[1]);
                                        }
                                    } else {
                                        allocateBlock.setPrimary_dns(dnsServerList);
                                    }
                                    
                                }
                                netconfDeviceService.allocateAddress(allocateBlock, idObjectMap);
                            }
                        }
                    }
                } else {
                    log.error("[deviceId={}] session is close.", deviceId);
                }
            } else {
                log.error("[deviceId={}] session is close.", deviceId);
            }
        }
        log.info("end allocateOrRecycleAddressBlock...");
    }

    private void reportIpv4ConfigConfirm(String xmlAddressBlockIpv4ConfigConfirm) {
        IPv4ConfigConfirmNotification ipv4ConfigConfirmNotification = null;
        try {
            ipv4ConfigConfirmNotification = JaxbUtil.convertToJavaBean(xmlAddressBlockIpv4ConfigConfirm, IPv4ConfigConfirmNotification.class);
        } catch (Exception e) {
            log.error("fail to convert xml string to java bean", e);
        }
        log.info("get ready to report ipv4 config confirm to app");
        ReportAddressBlockConfirm reportAddressBlockConfirm = null;
        try {
            if (ipv4ConfigConfirmNotification.getConfirmInfo() != null) {
                ConfirmInfo confirmInfo = ipv4ConfigConfirmNotification.getConfirmInfo();
                reportAddressBlockConfirm = new ReportAddressBlockConfirm();
                reportAddressBlockConfirm.setDevice_id(confirmInfo.getDeviceId());
                reportAddressBlockConfirm.setDomain_name(confirmInfo.getDomainName());
                reportAddressBlockConfirm.setAddress_pool_id(confirmInfo.getAddressPoolId());
                reportAddressBlockConfirm.setAddress_pool_name(confirmInfo.getAddressPoolName());
                reportAddressBlockConfirm.setAddress_block_id(confirmInfo.getAddressBlockId());
                reportAddressBlockConfirm.setAddress_block_name(confirmInfo.getAddressBlockName());
                reportAddressBlockConfirm.setResult_status(getResultStatusValue(false, confirmInfo.getResult()));
                reportAddressBlockConfirm.setProtocol_type(Constants.PROTOCOL_TYPE_IPV4);
                RequestAppClient.reportAddressBlockConfirm(reportAddressBlockConfirm);
            } else {
                log.error("message body of ipv4 config confirm is empty!");
            }
        } catch (Exception e) {
            log.error("report ipv4 config confirm [device_id={}, block_id={}] message to app failed", reportAddressBlockConfirm.getDevice_id(), reportAddressBlockConfirm.getAddress_block_id(), e);
        }
    }

    private int getResultStatusValue(boolean recycle, String status) {
        int result = -1;
        if (status != null) {
            if(recycle) {
                if (status.equalsIgnoreCase("success")) {
                    result = 1;
                } else if (status.equalsIgnoreCase("fail")) {
                    result = 3;
                }
            } else {
                if (status.equalsIgnoreCase("success")) {
                    result = 0;
                } else if (status.equalsIgnoreCase("fail")) {
                    result = 2;
                }
            }
        }
        return result;
    }

    private void reportIpv4RecycleConfirm(String xmlAddressBlockIpv4RecycleConfirm) {
        IPv4RecycleConfirmNotification ipv4RecycleConfirmNotification = null;
        try {
            ipv4RecycleConfirmNotification = JaxbUtil.convertToJavaBean(xmlAddressBlockIpv4RecycleConfirm, IPv4RecycleConfirmNotification.class);
        } catch (Exception e) {
            log.error("fail to convert xml string to java bean", e);
        }
        log.info("get ready to report ipv4 recycle confirm to app");
        ReportAddressBlockConfirm reportAddressBlockConfirm = null;
        try {
            if (ipv4RecycleConfirmNotification.getConfirmInfo() != null) {
                ConfirmInfo confirmInfo = ipv4RecycleConfirmNotification.getConfirmInfo();
                reportAddressBlockConfirm = new ReportAddressBlockConfirm();
                reportAddressBlockConfirm.setDevice_id(confirmInfo.getDeviceId());
                reportAddressBlockConfirm.setDomain_name(confirmInfo.getDomainName());
                reportAddressBlockConfirm.setAddress_pool_id(confirmInfo.getAddressPoolId());
                reportAddressBlockConfirm.setAddress_pool_name(confirmInfo.getAddressPoolName());
                reportAddressBlockConfirm.setAddress_block_id(confirmInfo.getAddressBlockId());
                reportAddressBlockConfirm.setAddress_block_name(confirmInfo.getAddressBlockName());
                reportAddressBlockConfirm.setResult_status(getResultStatusValue(true, confirmInfo.getResult()));
                reportAddressBlockConfirm.setProtocol_type(Constants.PROTOCOL_TYPE_IPV4);
                RequestAppClient.reportAddressBlockConfirm(reportAddressBlockConfirm);
            } else {
                log.error("message body of ipv4 recycle confirm is empty!");
            }
        } catch (Exception e) {
            log.error("report ipv4 recycle confirm [device_id={}, block_id={}] message to app failed", reportAddressBlockConfirm.getDevice_id(), reportAddressBlockConfirm.getAddress_block_id(), e);
        }
    }

    private void reportIpv6ConfigConfirm(String xmlAddressBlockIpv6ConfigConfirm) {
        IPv6ConfigConfirmNotification ipv6ConfigConfirmNotification = null;
        try {
            ipv6ConfigConfirmNotification = JaxbUtil.convertToJavaBean(xmlAddressBlockIpv6ConfigConfirm, IPv6ConfigConfirmNotification.class);
        } catch (Exception e) {
            log.error("fail to convert xml string to java bean", e);
        }
        log.info("get ready to report ipv6 config confirm to app");
        ReportAddressBlockConfirm reportAddressBlockConfirm = null;
        try {
            if (ipv6ConfigConfirmNotification.getConfirmInfo() != null) {
                ConfirmInfo confirmInfo = ipv6ConfigConfirmNotification.getConfirmInfo();
                reportAddressBlockConfirm = new ReportAddressBlockConfirm();
                reportAddressBlockConfirm.setDevice_id(confirmInfo.getDeviceId());
                reportAddressBlockConfirm.setDomain_name(confirmInfo.getDomainName());
                reportAddressBlockConfirm.setAddress_pool_id(confirmInfo.getAddressPoolId());
                reportAddressBlockConfirm.setAddress_pool_name(confirmInfo.getAddressPoolName());
                reportAddressBlockConfirm.setAddress_block_id(confirmInfo.getAddressBlockId());
                reportAddressBlockConfirm.setAddress_block_name(confirmInfo.getAddressBlockName());
                reportAddressBlockConfirm.setResult_status(getResultStatusValue(false, confirmInfo.getResult()));
                reportAddressBlockConfirm.setProtocol_type(Constants.PROTOCOL_TYPE_IPV6);
                RequestAppClient.reportAddressBlockConfirm(reportAddressBlockConfirm);
            } else {
                log.error("message body of ipv6 config confirm is empty!");
            }
        } catch (Exception e) {
            log.error("report ipv6 config confirm [device_id={}, block_id={}] message to app failed", reportAddressBlockConfirm.getDevice_id(), reportAddressBlockConfirm.getAddress_block_id(), e);
        }
    }

    private void reportIpv6RecycleConfirm(String xmlAddressBlockIpv6RecycleConfirm) {
        IPv6RecycleConfirmNotification ipv6RecycleConfirmNotification = null;
        try {
            ipv6RecycleConfirmNotification = JaxbUtil.convertToJavaBean(xmlAddressBlockIpv6RecycleConfirm, IPv6RecycleConfirmNotification.class);
        } catch (Exception e) {
            log.error("fail to convert xml string to java bean", e);
        }
        log.info("get ready to report ipv6 recycle confirm to app");
        ReportAddressBlockConfirm reportAddressBlockConfirm = null;
        try {
            if (ipv6RecycleConfirmNotification.getConfirmInfo() != null) {
                ConfirmInfo confirmInfo = ipv6RecycleConfirmNotification.getConfirmInfo();
                reportAddressBlockConfirm = new ReportAddressBlockConfirm();
                reportAddressBlockConfirm.setDevice_id(confirmInfo.getDeviceId());
                reportAddressBlockConfirm.setDomain_name(confirmInfo.getDomainName());
                reportAddressBlockConfirm.setAddress_pool_id(confirmInfo.getAddressPoolId());
                reportAddressBlockConfirm.setAddress_pool_name(confirmInfo.getAddressPoolName());
                reportAddressBlockConfirm.setAddress_block_id(confirmInfo.getAddressBlockId());
                reportAddressBlockConfirm.setAddress_block_name(confirmInfo.getAddressBlockName());
                reportAddressBlockConfirm.setResult_status(getResultStatusValue(true, confirmInfo.getResult()));
                reportAddressBlockConfirm.setProtocol_type(Constants.PROTOCOL_TYPE_IPV6);
                RequestAppClient.reportAddressBlockConfirm(reportAddressBlockConfirm);
            } else {
                log.error("message body of ipv6 recycle confirm is empty!");
            }
        } catch (Exception e) {
            log.error("report ipv6 recycle confirm [device_id={}, block_id={}] message to app failed", reportAddressBlockConfirm.getDevice_id(), reportAddressBlockConfirm.getAddress_block_id(), e);
        }
    }

    private void maintainDeviceAliveState(String xmlHeartBeat) {
        HeartBeatNotification heartBeatNotification = null;
        try {
            heartBeatNotification = JaxbUtil.convertToJavaBean(xmlHeartBeat, HeartBeatNotification.class);
        } catch (Exception e) {
            log.error("fail to convert xml string to java bean", e);
        }
        //Date eventTime = heartBeatNotification.getEventTime();
        Date eventTime = new Date(System.currentTimeMillis());
        String deviceId = heartBeatNotification.getHearBeat().getDeviceId();
        synchronized(deviceIdLock) {
        	log.info("start maintainDeviceAliveState....");
            List<DeviceState> deviceStateList = deviceAliveStateMap.get(Integer.parseInt(deviceId));
            if (deviceStateList == null) {
                log.error("device list = [{}], deviceId = [{}] not in device list", deviceAliveStateMap.keySet(), deviceId);
            } else {
                DeviceState deviceState = new DeviceState();
                deviceState.setIsAlive(Constants.DEVICE_STATE_ALIVE);
                deviceState.setDeviceId(Integer.parseInt(deviceId));
                deviceState.setTime(eventTime);
                deviceStateList.add(deviceState);
            }
        }
        log.info("end maintainDeviceAliveState....");
    }

    private void reportDeviceDown(String xmlDeviceDown) {
        DeviceDownNotification deviceDownNotification = null;
        try {
            deviceDownNotification = JaxbUtil.convertToJavaBean(xmlDeviceDown, DeviceDownNotification.class);
        } catch (Exception e) {
            log.error("fail to convert xml string to java bean", e);
        }
        try {
            String deviceId = deviceDownNotification.getDeviceDownMessage().getDeviceDown().getDeviceId();
            DeviceDown deviceDown = new DeviceDown();
            deviceDown.setDevice_id(deviceId);
            RequestAppClient.reportDeviceDown(deviceDown);
        } catch (AmCtrlException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error("report device down message to app failed", e);
        }
    }

    @Override
    public ResponseVo forceDeviceDown(int deviceId) {
        Device device = idObjectMap.get(deviceId);
        if (device != null) {
            String deviceIp = idObjectMap.get(deviceId).getDevice_ip();
            netconfDeviceService.disconnectDevice(deviceIp);
            synchronized (deviceIdLock) {
            	log.info("start forceDeviceDown...");
                idObjectMap.remove(deviceId);
                deviceAliveStateMap.remove(deviceId);
            }
            log.info("end forceDeviceDown...");
        } else {
            log.error("device [{}] not in device list", deviceId);
        }
        ResponseVo vo = new ResponseVo();
        vo.setMessage(Constants.RETURN_MESSAGE_SUCCESS);
        return vo;
    }

    @Override
    public ResponseVo getDeviceAliveState(int controllerId) {
        ResponseVo vo = new ResponseVo();
        ControllerState controllerState = new ControllerState();
        List<ReportDeviceState> reportDeviceStateList = new ArrayList<ReportDeviceState>();
        Set<Integer> removeDeviceIdList = new HashSet<Integer>();
        synchronized (deviceIdLock) {
        	log.info("start getDeviceAliveState....");
            Set<Integer> deviceIdSet = idObjectMap.keySet();// 获取所有的deviceId
            for (Integer deviceId : deviceIdSet) {
                ReportDeviceState reportDeviceState = new ReportDeviceState();
                reportDeviceState.setDevice_id(deviceId);
                List<DeviceState> deviceStateList = deviceAliveStateMap.get(deviceId);
                // 判断设备的状态
                int deviceState = estimateDeviceAliveState(deviceStateList);
                String reportState = convertDeviceStateFromNumberToString(deviceState);
                if (reportState == null) {
                    continue;
                } else {
                    reportDeviceState.setIs_alive(reportState);
                }
                if (deviceState == Constants.DEVICE_STATE_DEAD) {// 设备“死亡”
                    // 待删除的device_id
                    removeDeviceIdList.add(deviceId);
                }
                reportDeviceStateList.add(reportDeviceState);
            }
            controllerState.setController_id(controllerId);
            controllerState.setDevice_list(reportDeviceStateList);
            controllerState.setIs_alive(Constants.DEVICE_STATE_ALIVE_STRING);
            // 删除“死亡”的设备
            for (Integer deviceId : removeDeviceIdList) {
                String deviceIp = idObjectMap.get(deviceId).getDevice_ip();
                log.info("device[deviceId = {}, deviceIp = {}] dead", deviceId, deviceIp);
                // 强制与设备断开链接
                netconfDeviceService.disconnectDevice(deviceIp);
                idObjectMap.remove(deviceId);
                deviceAliveStateMap.remove(deviceId);
            }
        }
        log.info("end getDeviceAliveState....");
        vo.setResult(controllerState);
        return vo;
    }

    private String convertDeviceStateFromNumberToString(int deviceState) {
        if (deviceState == Constants.DEVICE_STATE_ALIVE) {
            return Constants.DEVICE_STATE_ALIVE_STRING;
        } else if (deviceState == Constants.DEVICE_STATE_DEAD) {
            return Constants.DEVICE_STATE_DEAD_STRING;
        } else {
            return null;
        }
    }

    private int estimateDeviceAliveState(List<DeviceState> deviceStateList) {
        int count = 3;// 默认配置
        String deviceStateDeadTimes = ConfigUtil.getValue("DEVICE_STATE_DEAD_TIMES");
        if (deviceStateDeadTimes == null || "".equals(deviceStateDeadTimes)) {
            log.error("config.properties lack config parameter {}", "DEVICE_STATE_DEAD_TIMES");
        } else {
            count = Integer.parseInt(deviceStateDeadTimes);
        }
        DeviceState lastDeviceState = deviceStateList.get(deviceStateList.size() - 1);
        int deviceHeartBeatInterval = idObjectMap.get(lastDeviceState.getDeviceId()).getDevice_keep_alive_interval();
        // 当前时间距离最后一次的心跳时间间隔超过时间间隔的N倍，判定设备下线(N:配置文件设置的连续死亡次数)
        if (System.currentTimeMillis() - lastDeviceState.getTime().getTime() <= deviceHeartBeatInterval * count * 1000) {
            return lastDeviceState.getIsAlive();
        }
        return Constants.DEVICE_STATE_DEAD;
    }


    @Override
    public ResponseVo recycleAddress(RecycleBlock recycleBlock) throws Exception {
        ResponseVo vo = new ResponseVo();
        if (recycleBlock != null) {
            int deviceId = recycleBlock.getDevice_id();
            log.info("begin to recycle address from device[deviceId={}]", deviceId);
            String deviceIp = null;
            synchronized(deviceIdLock) {
            	log.info("start recycleAddress...");
                if (idObjectMap.get(deviceId) != null) {
                    deviceIp = idObjectMap.get(deviceId).getDevice_ip();
                    // 查看当前的session是否有效
                    boolean flag = netconfDeviceService.validateSession(deviceId, deviceIp);
                    if (flag) {
                        // vo的return_code取值(0:正常,100:表示下发重复地址错误并且回收不存在地址错误,101:回收不存在的地址错误,102:下发地址块block-index错误,104:下发地址块名称重复)
                        JSONObject returnResult = netconfDeviceService.recycleAddress(recycleBlock, idObjectMap);
                        if (returnResult.getString(("return_code")).equals(String.valueOf(Constants.ALLOCATE_AND_RECYCLE_ADDRESS_ERROR))) {
                            JSONObject result = new JSONObject();
                            if (returnResult.get(String.valueOf(Constants.RECYCLE_ADDRESS_NOT_EXIST)) != null) {
                                vo.setReturn_code(Constants.RECYCLE_ADDRESS_NOT_EXIST);
                                result.put(String.valueOf(Constants.RECYCLE_ADDRESS_NOT_EXIST), returnResult.get(String.valueOf(Constants.RECYCLE_ADDRESS_NOT_EXIST)));
                            } 
                            vo.setMessage(Constants.RETURN_MESSAGE_FAIL);
                            vo.setResult(result);
                        } else {
                            vo.setMessage(Constants.RETURN_MESSAGE_SUCCESS);
                        }
                    } else {
                        log.error("[deviceId={}] session is closed.", deviceId);
                        vo.setReturn_code(Constants.RETURN_CODE_BUSINESS_ERROR);
                        vo.setMessage("session is closed");
                    }
                } else {
                    log.error("[deviceId={}] session is closed.", deviceId);
                    vo.setReturn_code(Constants.RETURN_CODE_BUSINESS_ERROR);
                    vo.setMessage("session is closed");
                }
            }
            log.info("end recycleAddress...");
        } else {
            log.info("recycle block is null");
        }
        return vo;
    }

    @Override
    public ResponseVo allocateAddress(AllocateBlock allocateBlock) throws Exception {
        ResponseVo vo = new ResponseVo();
        if (allocateBlock != null) {
            int deviceId = allocateBlock.getDevice_id();
            
            // check device live state in case that device still connecting when device is invoked to alive first time
            log.info("ControllerServiceImpl::allocateAddress() Checking device alive state for device " + deviceId);
            List<DeviceState> deviceStateList = deviceAliveStateMap.get(deviceId);
            DeviceState deviceState = deviceStateList.get(deviceStateList.size()-1);
            int count = 0;
            while(true) {
            	if(Constants.DEVICE_STATE_ALIVE == deviceState.getIsAlive()) {
            		log.info("Device with deviceId " + deviceId + " is already alive.");
            		break;
            	} else if(Constants.DEVICE_STATE_CONNECTING == deviceState.getIsAlive()) {
            		Thread.sleep(100);
            		count++;
            		log.info("Tryed " + count + " times for device " + deviceId);
            		if(count < 100) {
            			continue;            			
            		} else {
            			log.error("Tryed 100 times in totally 10s to wait for device state to alive, but shows device " + deviceId+ " is not alive.");
            			throw new Exception("Tryed 100 times in totally 10s to wait for device state to alive, but shows device " + deviceId+ " is not alive.");
            		}
            	} else {
            		log.error("DeviceState " + deviceState.getIsAlive() + " of device with deviceId " + deviceId+ " is not expected.");
            		throw new Exception("DeviceState " + deviceState.getIsAlive() + " of device with deviceId " + deviceId+ " is not expected.");
            	}
            }
            
            log.info("begin to allocate address to device[deviceId={}]", deviceId);
            String deviceIp = null;
            synchronized(deviceIdLock) {
            	log.info("start allocateAddress...");
                if (idObjectMap.get(deviceId) != null) {
                    deviceIp = idObjectMap.get(deviceId).getDevice_ip();
                    // 查看当前的session是否有效
                    boolean flag = netconfDeviceService.validateSession(deviceId, deviceIp);
                    if (flag) {
                        JSONObject returnResult = netconfDeviceService.allocateAddress(allocateBlock, idObjectMap);
                        // vo的return_code取值(0:正常,100:表示下发重复地址错误并且回收不存在地址错误,101:回收不存在的地址错误,102:下发地址块block-index错误,104:下发地址块名称重复)
                        if (returnResult.getString(("return_code")).equals(String.valueOf(Constants.ALLOCATE_AND_RECYCLE_ADDRESS_ERROR))) {
                            JSONObject result = new JSONObject();
                            if (returnResult.get(String.valueOf(Constants.ALLOCATE_ADDRESS_INDEX_REPEAT)) != null) {
                                vo.setReturn_code(Constants.ALLOCATE_ADDRESS_INDEX_REPEAT);
                                result.put(String.valueOf(Constants.ALLOCATE_ADDRESS_INDEX_REPEAT), returnResult.get(String.valueOf(Constants.ALLOCATE_ADDRESS_INDEX_REPEAT)));
                            }
                            if (returnResult.get(String.valueOf(Constants.ALLOCATE_ADDRESS_NAME_REPEAT)) != null) {
                                vo.setReturn_code(Constants.ALLOCATE_ADDRESS_NAME_REPEAT);
                                result.put(String.valueOf(Constants.ALLOCATE_ADDRESS_NAME_REPEAT), returnResult.get(String.valueOf(Constants.ALLOCATE_ADDRESS_NAME_REPEAT)));
                            }
                            vo.setMessage(Constants.RETURN_MESSAGE_FAIL);
                            vo.setResult(result);
                        } else {
                            vo.setMessage(Constants.RETURN_MESSAGE_SUCCESS);
                        }
                    } else {
                        log.error("[deviceId={}] session is closed.", deviceId);
                        vo.setReturn_code(Constants.RETURN_CODE_BUSINESS_ERROR);
                        vo.setMessage("session is closed");
                    }
                } else {
                    log.error("[deviceId={}] session is closed.", deviceId);
                    vo.setReturn_code(Constants.RETURN_CODE_BUSINESS_ERROR);
                    vo.setMessage("session is closed");
                }
            }
            log.info("end allocateAddress...");
        } else {
            log.info("recycle block is null");
        }
        return vo;
    }

    public void setNetconfDeviceService(NetconfDeviceService netconfDeviceService) {
        this.netconfDeviceService = netconfDeviceService;
    }

    @Override
    public void clean() {
    	synchronized (deviceIdLock) {
	        for(Integer deviceId : idObjectMap.keySet()) {
	            Device device = idObjectMap.get(deviceId);
	            if (device != null) {
	                String deviceIp = idObjectMap.get(deviceId).getDevice_ip();
	                netconfDeviceService.disconnectDevice(deviceIp);
                	log.info("start clean device...");
                    idObjectMap.remove(deviceId);
                    deviceAliveStateMap.remove(deviceId);
	                log.info("end clean device...");
	            } else {
	                log.error("device [{}] not in device list", deviceId);
	            }
	        }
	        log.info("cleaned session");
    	}
    }

    /**
     * @deprecated
     * @description 释放地址池 
     * @author zhangfh
     * @param releaseAddrPoolInfo
     * @return
     */
	public ResponseVo deleteAddressPool(ReleaseAddrPoolInfo releaseAddrPoolInfo) {
		ResponseVo vo = new ResponseVo();
		if (releaseAddrPoolInfo != null){
			int deviceId = releaseAddrPoolInfo.getDevice_id();
            log.info("begin to release address pool from device[deviceId={}]", deviceId);
            String deviceIp = null;
            synchronized(deviceIdLock) {
            	log.info("start releaseAddressPool...");
            	if (idObjectMap.get(deviceId) != null) {
            		deviceIp = idObjectMap.get(deviceId).getDevice_ip();
                    // 查看当前的session是否有效
                    boolean flag = netconfDeviceService.validateSession(deviceId, deviceIp);
                    if (flag) {
                    	// vo的return_code取值(0:正常,110:表示释放地址池发生错误,111:地址池不存在错误,112:释放地址池重复)
                    	JSONObject returnResult = netconfDeviceService.releaseAddressPool(releaseAddrPoolInfo, idObjectMap);
                    	
                        if (returnResult.getString(("return_code")).equals(String.valueOf(Constants.RELEASE_ADDRESS_POOL_ERROR))) {
                            if (returnResult.get(String.valueOf(Constants.RELEASE_ADDRESS_POOL_NOT_EXIST)) != null) {
                                vo.setReturn_code(Constants.RELEASE_ADDRESS_POOL_NOT_EXIST);
                            }
                            vo.setMessage(Constants.RETURN_MESSAGE_FAIL);
                        } else if (returnResult.getString(("return_code")).equals(String.valueOf(Constants.RELEASE_ADDRESS_POOL_SUCCESS))) {
                            vo.setMessage(Constants.RETURN_MESSAGE_SUCCESS);
                        }
                    	
                    } else {
                        log.error("[deviceId={}] session is closed.", deviceId);
                        vo.setReturn_code(Constants.RETURN_CODE_BUSINESS_ERROR);
                        vo.setMessage("session is closed");
                    }
            	} else {
                    log.error("[deviceId={}] session is closed.", deviceId);
                    vo.setReturn_code(Constants.RETURN_CODE_BUSINESS_ERROR);
                    vo.setMessage("session is closed");
                }
            }
            log.info("end releaseAddressPool...");
		}
		else{
			log.info("delete address pool is null");
		}
		return vo;
	}
    /**
     * This will be invoked when lack of block in south side, let app know and app decides whether allocate block or not
     * @param xmlBlockLacking
     */
    private void reportBlockLacking(String xmlBlockLacking) {
		// TODO Auto-generated method stub
    	log.info("ControllerServiceImpl::reportBlockLacking() Converting xml to json and report to app: " + xmlBlockLacking);

    	BlockLackingNotification blockLackingNotification = null;
        try {
        	blockLackingNotification = JaxbUtil.convertToJavaBean(xmlBlockLacking, BlockLackingNotification.class);
        } catch (Exception e) {
            log.error("fail to convert xml string to java bean", e);
        }
    	try {
    		RequestAppClient.reportBlockLacking(blockLackingNotification.getBlockLacking());
		} catch (Exception e) {
			log.error("ControllerServiceImpl::reportBlockLacking() error occurs when report block lacking to app ", e);
		}
	}

}
