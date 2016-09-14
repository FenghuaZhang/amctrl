package com.chinatele.app.amctrl.service.impl;

import gen.addressPool.ietfAddressPool.AddrPool;
import gen.addressPool.ietfAddressPool.AddressPools;
import gen.deviceInfo.flexbngDeviceInfo.ControllerNetconfRegistration;
import gen.deviceInfo.flexbngDeviceInfo.ControllerSendConfigurationToAma;
import gen.deviceInfo.flexbngDeviceInfo.FlexbngVbras;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.chinatele.app.amctrl.netconf.Client;
import com.chinatele.app.amctrl.rest.vo.request.AllocateBlock;
import com.chinatele.app.amctrl.rest.vo.request.DeviceConfigInfo;
import com.chinatele.app.amctrl.rest.vo.request.RecycleBlock;
import com.chinatele.app.amctrl.rest.vo.request.ReleaseAddrPoolInfo;
import com.chinatele.app.amctrl.service.NetconfDeviceService;
import com.chinatele.app.amctrl.util.Constants;
import com.tailf.jnc.Device;
import com.tailf.jnc.Element;
import com.tailf.jnc.ElementChildrenIterator;
import com.tailf.jnc.JNCException;
import com.tailf.jnc.NetconfSession;

public class NetconfDeviceServiceImpl implements NetconfDeviceService{

private static Logger logger = Logger.getLogger(NetconfDeviceServiceImpl.class);
	
  	private static Map<String,Thread> notificationThreadMap = new HashMap<String,Thread>();
	
	private Client client;

	public void setClient(Client client) {
		this.client = client;
	}

	public boolean openConnection(DeviceConfigInfo connCfgInfo) {
		logger.info("enter method:openConnection");

		try {
			client.initConn(connCfgInfo.getDevice_ip(),connCfgInfo.getDevice_port(),connCfgInfo.getUserName(),connCfgInfo.getPassword());
			logger.info("starting register yang model");
			FlexbngVbras.enable();
			AddrPool.enable();
			
			//订阅notification
			logger.info("Starting getting msg session");
	        final NetconfSession msgsession = client.getSession(Client.devices.get(connCfgInfo.getDevice_ip()),"msg"); 
	        if(msgsession != null) {
				logger.info("Starting subscription address-pool creation for session: "
						+ ReflectionToStringBuilder.toString(msgsession,
								ToStringStyle.MULTI_LINE_STYLE));
	        	try {
					msgsession.createSubscription("address-pool");
				} catch (JNCException e) {
					// TODO Auto-generated catch block
					logger.error("Subscription creation failed due to exception "
							+ e.getCause() + ";"
							+ e.getMessage() + ";"
							+ e.getStackTrace());
				}        	
	        } else {
	        	logger.error("Cannot create subscription since msg session retrieved is null");
	        }
	        logger.info("Finish subscription address-pool creation");
	        
	        //一个连接对应一个接收notification的线程
	        if (notificationThreadMap.get(connCfgInfo.getDevice_ip()) == null){
	        	Thread thread = new Thread(){
					public void run() {
						while(true){
				        	if (msgsession.getCapabilities().hasNotification()){
				        		try {
				        			msgsession.receiveNotification();
								} catch (IOException e) {
									logger.error("controller receive notification failed:"+e.getMessage());
									break;
								} catch (JNCException e) {
								    logger.error("controller receive notification failed:"+e.getMessage());
                                                                    break;
								}
				        	}
				        }
					}
	        	};
	        	thread.start();
	        	notificationThreadMap.put(connCfgInfo.getDevice_ip(), thread);
	        }

	        logger.info("Starting getting cfg session");
	        NetconfSession session = client.getSession(Client.devices.get(connCfgInfo.getDevice_ip()),"cfg"); 
	        
	        logger.info("Starting ama netconf registration");
	        //用于Controller向AMA发起注册
			if (session.getConfig("controller-netconf-registration") == null
					|| session.getConfig("controller-netconf-registration").size() == 0) {
				logger.info("returned config controller-netconf-registration is empty");
	        	ControllerNetconfRegistration amaDeviceConfig = new ControllerNetconfRegistration();
	        	amaDeviceConfig.setDeviceIdValue(connCfgInfo.getDevice_id());
	        	session.editConfig(amaDeviceConfig);
	        }
	        else{
				Element deviceConfig = Client.getConfig(
						session.getConfig("controller-netconf-registration"),"controller-netconf-registration");
	        	setElementValue(deviceConfig,"device-id",connCfgInfo.getDevice_id());
		        session.editConfig(deviceConfig);
	        }
	        
	        logger.info("Starting sending configuration to ama");
	        //下发配置
			if (session.getConfig("controller-send-configuration-to-ama") == null
					|| session.getConfig("controller-send-configuration-to-ama").size() == 0) {
				logger.info("returned config controller-send-configuration-to-ama is empty");
				logger.info("connCfgInfo to be sent to ama is " + ReflectionToStringBuilder.toString(connCfgInfo, ToStringStyle.MULTI_LINE_STYLE));
	        	ControllerSendConfigurationToAma controllerSendConfigurationToAma = new ControllerSendConfigurationToAma();
	        	controllerSendConfigurationToAma.setDeviceKeepAliveIntervalValue(connCfgInfo.getHeartbeat_interval());
	        	controllerSendConfigurationToAma.setIpv4AddressPoolUsageThresholdValue(connCfgInfo.getIpv4_threshold()+"");
	        	controllerSendConfigurationToAma.setIpv6AddressPoolUsageThresholdValue(connCfgInfo.getIpv6_threshold()+"");
	        	controllerSendConfigurationToAma.setStateUpdateIntervalValue(connCfgInfo.getState_update_interval());
	        	controllerSendConfigurationToAma.setDeviceSamplingIntervalValue(connCfgInfo.getDevice_sampling_interval());
	        	if (session.getConfig().add(controllerSendConfigurationToAma)){
	        		try {
	        			logger.info("Start editting config controller-send-configuration-to-ama...");
						session.editConfig(controllerSendConfigurationToAma);
					} catch (JNCException e) {
						logger.error("Editting config controller-send-configuration-to-ama exception occurs: "+ e.getCause() + ";" + e.getMessage());
						disconnectDevice(connCfgInfo.getDevice_ip());
						return false;
					}
	        	} else {
					logger.info("Config controller-send-configuration-to-ama adding to session failed. ");
	        	}
	        }
	        else{
	        	Element configuration = Client.getConfig(session.getConfig("controller-send-configuration-to-ama"),"controller-send-configuration-to-ama");
		        setElementValue(configuration,"device-keep-alive-interval", connCfgInfo.getHeartbeat_interval());
		        setElementValue(configuration,"ipv4-address-pool-usage-threshold", connCfgInfo.getIpv4_threshold());
		        setElementValue(configuration,"ipv6-address-pool-usage-threshold", connCfgInfo.getIpv6_threshold());
		        setElementValue(configuration,"state-update-interval", connCfgInfo.getState_update_interval());
		        setElementValue(configuration,"device-sampling-interval", connCfgInfo.getDevice_sampling_interval());
		        session.editConfig(configuration);
	        }
	        
	        return true;
		} catch(JNCException e){
			logger.error("JNCException occurs: "+ e.getCause() + ";" + e.getMessage());
			disconnectDevice(connCfgInfo.getDevice_ip());
			return false;
		} catch (Exception ex) {
		    ex.printStackTrace();
			logger.error("service.openConnection: "+ ex.getCause() + ";" + ex.getMessage());
			disconnectDevice(connCfgInfo.getDevice_ip());
			return false;
		}	
				
	}
	
	private void setElementValue(Element e,String s,Object obj) throws JNCException{
		if (e.getValue(s) != null){
			e.setValue(s, obj);
		}
		else{
			e.createChild(s, obj);
		}
	}

	public void disconnectDevice(String ip) {
		if (Client.devices.containsKey(ip)){
			Client.devices.get(ip).close();
			Client.devices.remove(ip);
		}
		if (notificationThreadMap.containsKey(ip)){
			notificationThreadMap.remove(ip);
		}
		
		
	}

    @Override
    public boolean validateSession(int deviceId, String deviceIp) {
    	if (!Client.devices.containsKey(deviceIp)){
    		return false;
    	}
    	else{
    		Device d = Client.devices.get(deviceIp);
    		if (d.hasSession("cfg")){
    			try {
					d.getSession("cfg").getConfig();
				} catch (JNCException e) {
					logger.error("session.getConfig failed");
					disconnectDevice(deviceIp);
					return false;
				} catch (IOException e) {
					logger.error("connection with device failed");
					disconnectDevice(deviceIp);
					return false;
				}
    			return true;
    		}
    	}
        return false;
    }

    @Override
    public JSONObject allocateAddress(AllocateBlock allocateBlock, Map<Integer, com.chinatele.app.amctrl.rest.vo.request.Device> map) {
    	logger.info("enter method:allocateAddress");
    	JSONObject successResult = new JSONObject();
        successResult.put("return_code", String.valueOf(Constants.ALLOCATE_AND_RECYCLE_ADDRESS_SUCCESS));
        
        JSONObject failedResult = new JSONObject();
        failedResult.put("return_code", String.valueOf(Constants.ALLOCATE_AND_RECYCLE_ADDRESS_ERROR));
        List<Integer> allocateIndexRepeatList = new ArrayList<Integer>();
        List<Integer> allocateNameRepeatList = new ArrayList<Integer>();
        com.chinatele.app.amctrl.rest.vo.request.Device device = map.get(allocateBlock.getDevice_id());
		
		try {
			logger.info("Connecting to device " + device.getDevice_ip()	+ "......");
			client.init(device.getDevice_ip(),device.getDevice_port(),device.getUsername(),device.getPassword());
			FlexbngVbras.enable();
			AddrPool.enable();
			logger.info("Getting cfg session for device: "
							+ ReflectionToStringBuilder.toString(Client.devices.get(device.getDevice_ip()),ToStringStyle.MULTI_LINE_STYLE));
			NetconfSession session = client.getSession(Client.devices.get(device.getDevice_ip()),"cfg");
			logger.info("After getting cfg session......");
			
			logger.info(ReflectionToStringBuilder.toString(session.getConfig("address-pools"), ToStringStyle.MULTI_LINE_STYLE));
			logger.info("After testing getConfig address-pools");
			if (session.getConfig("address-pools") == null || session.getConfig("address-pools").size() == 0){
				logger.info("After getting nulled config address-pools......");
				AddressPools pools = new AddressPools();
				pools.setDeviceIdValue(allocateBlock.getDevice_id());
				//pools.setDomainNameValue(allocateBlock.getDomain_name());
				//pools.setTimeValue(allocateBlock.getTime()+"");
				session.getConfig().add(pools);
				logger.info("After getting all config ......");
				gen.addressPool.ietfAddressPool.addressPools.AddressPool netconfPool = pools.addAddressPool();
				netconfPool.setAddressPoolNameValue(allocateBlock.getAddress_pool_name());
				netconfPool.setAddressPoolIdValue(allocateBlock.getAddress_pool_id());
				netconfPool.setDomainNameValue(allocateBlock.getDomain_name());
				if (allocateBlock.getProtocol_type() == 0){
					netconfPool.setIpv6UsergatewayValue(allocateBlock.getUsergateway());
				}
				else if (allocateBlock.getProtocol_type() == 1){
					netconfPool.setIpv4UsergatewayValue(allocateBlock.getUsergateway());
				}
				
				netconfPool.setGwnetmaskValue(allocateBlock.getGwnetmask());
				netconfPool.setPrimarydnsValue(allocateBlock.getPrimary_dns());
				if (!StringUtils.isEmpty(allocateBlock.getSecondary_dns())){
					netconfPool.setSecondarydnsValue(allocateBlock.getSecondary_dns());
				}
				netconfPool.setLeasingTimeValue(allocateBlock.getLeasing_time());
				netconfPool.addAddressPoolEntries();
				logger.info("Before editing config address-pools......");
				session.editConfig(pools);
				logger.info("After editing config address-pools......");
			}
			
			logger.info("Before getting config address-pools......");
			Element configuration = Client.getConfig(session.getConfig("address-pools"),"address-pools");
			logger.info("After getting config address-pools with returned configuration: "
					+ ReflectionToStringBuilder.toString(configuration,	ToStringStyle.MULTI_LINE_STYLE));
			
			boolean matchPool = false;
			boolean matchBlock = false;
			
			if (configuration.hasChildren()){
				for (Element e :configuration.getChildren("address-pool")){
					if (e.getValue("address-pool-name").toString().equals(allocateBlock.getAddress_pool_name())){
						matchPool = true;
						
						if (e.getChild("address-pool-entries")!=null){
							ElementChildrenIterator it = e.getChild("address-pool-entries").iterator();
							while (it.hasNext()){
								Element ite = it.next();
								String blockId = "";
								String blockName = "";
								if (allocateBlock.getProtocol_type() == 0){
									if (ite.getValue("ipv6-address-block-id") != null){
										blockId = ite.getValue("ipv6-address-block-id").toString();
									}
									if (ite.getValue("ipv6-address-block-name") != null){
										blockName = ite.getValue("ipv6-address-block-name").toString();
									}
								}
								else if (allocateBlock.getProtocol_type() == 1){
									if (ite.getValue("ipv4-address-block-id") != null){
										blockId = ite.getValue("ipv4-address-block-id").toString();
									}
									if (ite.getValue("ipv4-address-block-name") != null){
										blockName = ite.getValue("ipv4-address-block-name").toString();
									}
								}
								if (String.valueOf(allocateBlock.getAddress_block_id()).equals(blockId)){
									matchBlock = true;
									allocateIndexRepeatList.add(allocateBlock.getAddress_block_id());
									break;
								}
								if (allocateBlock.getAddress_block_name().equals(blockName)){
									matchBlock = true;
									allocateNameRepeatList.add(allocateBlock.getAddress_block_id());
									break;
								}
							}
						}
						
						
						if (!matchBlock){
							if (e.getChild("address-pool-entries") == null){
								e.createChild("address-pool-entries");
							}
							if (allocateBlock.getProtocol_type() == 0){
								Element newel = e.getChild("address-pool-entries").createChild("ipv6-address-block");
								setElementValue(newel,"ipv6-address-block-name",allocateBlock.getAddress_block_name());
								setElementValue(newel,"ipv6-address-block-id",allocateBlock.getAddress_block_id());
								setElementValue(newel,"ipv6-prefix",allocateBlock.getIp_prefix());
								setElementValue(newel,"ipv6-prefix-length",allocateBlock.getIp_prefix_length());
								setElementValue(newel,"time",allocateBlock.getTime());
							}
							else if (allocateBlock.getProtocol_type() == 1){
								Element newel = e.getChild("address-pool-entries").createChild("ipv4-address-block");
								setElementValue(newel,"ipv4-address-block-name",allocateBlock.getAddress_block_name());
								setElementValue(newel,"ipv4-address-block-id",allocateBlock.getAddress_block_id());
								setElementValue(newel,"ipv4-prefix",allocateBlock.getIp_prefix());
								setElementValue(newel,"ipv4-prefix-length",allocateBlock.getIp_prefix_length());
								setElementValue(newel,"time",allocateBlock.getTime());
							}
						}
						
					}
				}
			}
			if (!matchPool){
				Element newPool = configuration.createChild("address-pool");
				setElementValue(newPool,"address-pool-name",allocateBlock.getAddress_pool_name());
				setElementValue(newPool,"address-pool-id",allocateBlock.getAddress_pool_id());
				setElementValue(newPool,"gwnetmask",allocateBlock.getGwnetmask());
				setElementValue(newPool,"leasing-time",allocateBlock.getLeasing_time());
				setElementValue(newPool,"domain-name",allocateBlock.getDomain_name());
				
				if (!StringUtils.isEmpty(allocateBlock.getPrimary_dns())){
					setElementValue(newPool,"primarydns",allocateBlock.getPrimary_dns());
				}
				if (!StringUtils.isEmpty(allocateBlock.getSecondary_dns())){
					setElementValue(newPool,"secondarydns",allocateBlock.getSecondary_dns());
				}
				
				if (allocateBlock.getProtocol_type() == 0){
					setElementValue(newPool,"ipv6-usergateway",allocateBlock.getUsergateway());
					Element newel = newPool.createChild("address-pool-entries").createChild("ipv6-address-block");
					setElementValue(newel,"ipv6-address-block-name",allocateBlock.getAddress_block_name());
					setElementValue(newel,"ipv6-address-block-id",allocateBlock.getAddress_block_id());
					setElementValue(newel,"ipv6-prefix",allocateBlock.getIp_prefix());
					setElementValue(newel,"ipv6-prefix-length",allocateBlock.getIp_prefix_length());
					setElementValue(newel,"time",allocateBlock.getTime());
				}
				else if (allocateBlock.getProtocol_type() == 1){
					setElementValue(newPool,"ipv4-usergateway",allocateBlock.getUsergateway());
					Element newel = newPool.createChild("address-pool-entries").createChild("ipv4-address-block");
					setElementValue(newel,"ipv4-address-block-name",allocateBlock.getAddress_block_name());
					setElementValue(newel,"ipv4-address-block-id",allocateBlock.getAddress_block_id());
					setElementValue(newel,"ipv4-prefix",allocateBlock.getIp_prefix());
					setElementValue(newel,"ipv4-prefix-length",allocateBlock.getIp_prefix_length());
					setElementValue(newel,"time",allocateBlock.getTime());
				}
			}
			
			setElementValue(configuration,"device-id",allocateBlock.getDevice_id());
			//setElementValue(configuration,"domain-name",allocateBlock.getDomain_name());
			//setElementValue(configuration,"time",allocateBlock.getTime());
			session.editConfig(configuration);
		} catch (JNCException e) {
			e.printStackTrace();
			logger.error("service.allocateAddress failed,caused by JNCException:"+e.getMessage());
		} catch(IOException e) {
		    e.printStackTrace();
            logger.error("service.allocateAddress failed,caused by IOException:"+e.getMessage());
		}
		if (allocateIndexRepeatList.size() == 0 && allocateNameRepeatList.size() == 0){
			return successResult;
		}
		else{
			if (allocateIndexRepeatList.size() != 0) {
				failedResult.put(String.valueOf(Constants.ALLOCATE_ADDRESS_INDEX_REPEAT), allocateIndexRepeatList);
			}
			if (allocateNameRepeatList.size() != 0) {
				failedResult.put(String.valueOf(Constants.ALLOCATE_ADDRESS_NAME_REPEAT), allocateNameRepeatList);
			}
			return failedResult;
		}
		
    }

	@Override
	public JSONObject recycleAddress(RecycleBlock recycleBlock,Map<Integer, com.chinatele.app.amctrl.rest.vo.request.Device> map) {
		logger.info("enter method:recycleAddress");
		
		JSONObject successResult = new JSONObject();
        successResult.put("return_code", String.valueOf(Constants.ALLOCATE_AND_RECYCLE_ADDRESS_SUCCESS));
        
        JSONObject failedResult = new JSONObject();
        failedResult.put("return_code", String.valueOf(Constants.ALLOCATE_AND_RECYCLE_ADDRESS_ERROR));
        List<Integer> recyleNotExistList = new ArrayList<Integer>();
        
        com.chinatele.app.amctrl.rest.vo.request.Device device = map.get(recycleBlock.getDevice_id());
		
		try {
			client.init(device.getDevice_ip(),device.getDevice_port(),device.getUsername(),device.getPassword());
			FlexbngVbras.enable();
			AddrPool.enable();
			NetconfSession session = client.getSession(Client.devices.get(device.getDevice_ip()),"cfg");
			
			if (session.getConfig("address-pools") == null || session.getConfig("address-pools").size() == 0){
				recyleNotExistList.add(recycleBlock.getAddress_block_id());
				failedResult.put(String.valueOf(Constants.RECYCLE_ADDRESS_NOT_EXIST), recyleNotExistList);
				return failedResult;
			}
			
			Element configuration = Client.getConfig(session.getConfig("address-pools"),"address-pools");
			
			logger.info("configuration:"+(configuration==null?"empty":configuration.toXMLString()));
			
			boolean recycleFlag = false;
			if (configuration.hasChildren()){
				for (Element e :configuration.getChildren("address-pool")){
					if (e==null || e.getChild("address-pool-entries") == null){
						continue;
					}
					
					logger.info("poolName:"+e.getValue("address-pool-name"));
					logger.info("poolId:"+e.getValue("address-pool-id"));
					if (e.getValue("address-pool-id") != null){
						if (recycleBlock.getAddress_pool_id() != Integer.parseInt(e.getValue("address-pool-id").toString())){
							logger.info("not matched address pool");
							continue;
						} else {
							if(recycleBlock.getRecycleAddrPool() == Constants.RecycleAddressPool.YES) {
								e.markDelete();
								break;
							}
						}
					}
					
					if (e.getChild("address-pool-entries") == null){
						logger.info("address-pool-entries empty");
						continue;
					}
					
					ElementChildrenIterator it = e.getChild("address-pool-entries").iterator();
					while (it.hasNext()){
						Element ite = it.next();
						String blockId = "";
						if (recycleBlock.getProtocol_type() == 0){
							if (ite.getValue("ipv6-address-block-id") != null){
								blockId = ite.getValue("ipv6-address-block-id").toString();
							}
						}
						else if (recycleBlock.getProtocol_type() == 1){
							if (ite.getValue("ipv4-address-block-id") != null){
								blockId = ite.getValue("ipv4-address-block-id").toString();
							}
						}
						if (String.valueOf(recycleBlock.getAddress_block_id()).equals(blockId)){
							recycleFlag = true;
							ite.markDelete();							
							break;
						}
					}
				}
			}
			if (recycleBlock.getRecycleAddrPool() == Constants.RecycleAddressPool.NO && !recycleFlag){
				recyleNotExistList.add(recycleBlock.getAddress_block_id());
				failedResult.put(String.valueOf(Constants.RECYCLE_ADDRESS_NOT_EXIST), recyleNotExistList);
				return failedResult;
			}
			session.editConfig(configuration);
		}
		catch (JNCException e) {
			e.printStackTrace();
			logger.error("service.recycleAddress failed,caused by JNCException:"+e.getMessage());
		} catch(IOException e) {
		    e.printStackTrace();
            logger.error("service.recycleAddress failed,caused by IOException:"+e.getMessage());
		}
		return successResult;
	}

	public JSONObject releaseAddressPool(
			ReleaseAddrPoolInfo releaseAddrPoolInfo,
			Map<Integer, com.chinatele.app.amctrl.rest.vo.request.Device> map) {
		logger.info("enter method:releaseAddressPool");
		
		JSONObject successResult = new JSONObject();
        successResult.put("return_code", String.valueOf(Constants.RELEASE_ADDRESS_POOL_SUCCESS));
        
        JSONObject failedResult = new JSONObject();
        failedResult.put("return_code", String.valueOf(Constants.RELEASE_ADDRESS_POOL_ERROR));
        
        com.chinatele.app.amctrl.rest.vo.request.Device device = map.get(releaseAddrPoolInfo.getDevice_id());
        
		try {
			client.init(device.getDevice_ip(),device.getDevice_port(),device.getUsername(),device.getPassword());
			FlexbngVbras.enable();
			AddrPool.enable();
			NetconfSession session = client.getSession(Client.devices.get(device.getDevice_ip()),"cfg");
			
			if (session.getConfig("address-pools") == null || session.getConfig("address-pools").size() == 0){
				failedResult.put(String.valueOf(Constants.RECYCLE_ADDRESS_NOT_EXIST), String.valueOf(releaseAddrPoolInfo.getAddress_pool_id()));
				return failedResult;
			}
			
            Element configuration = Client.getConfig(session.getConfig("address-pools"),"address-pools");
			logger.info("deleteAddressPool-configuration:"+(configuration==null?"empty":configuration.toXMLString()));
			
			boolean releaseAddrPoolFlag = false;
			if (configuration.hasChildren()){
				for (Element e :configuration.getChildren("address-pool")){
					if (e == null){
						continue;
					}
					
					logger.info("poolName:"+e.getValue("address-pool-name"));
					logger.info("poolId:"+e.getValue("address-pool-id"));
					if (e.getValue("address-pool-id") != null){
						if (releaseAddrPoolInfo.getAddress_pool_id() != Integer.parseInt(e.getValue("address-pool-id").toString())){
							logger.info("not matched address pool");
						}
						else{
							logger.info("matched address pool id");
							logger.info("find address pool to release");
							releaseAddrPoolFlag = true;
							e.markDelete();
							break;
						}
					}
					
				}
			}
			if (!releaseAddrPoolFlag){
				failedResult.put(String.valueOf(Constants.RECYCLE_ADDRESS_NOT_EXIST), String.valueOf(releaseAddrPoolInfo.getAddress_pool_id()));
				return failedResult;
			}
			session.editConfig(configuration);
			
		} catch (JNCException e) {
			e.printStackTrace();
			logger.error("service.releaseAddressPool failed,caused by JNCException:"+e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("service.releaseAddressPool failed,caused by IOException:"+e.getMessage());
		}
		return successResult;
	}

}
