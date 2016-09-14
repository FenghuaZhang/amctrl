package com.chinatele.app.amctrl.rest.nc;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.chinatele.app.amctrl.rest.exception.AmCtrlException;
import com.chinatele.app.amctrl.rest.vo.ResponseVo;
import com.chinatele.app.amctrl.rest.vo.request.AllocateBlock;
import com.chinatele.app.amctrl.rest.vo.request.ConfigInfo;
import com.chinatele.app.amctrl.rest.vo.request.RecycleBlock;
import com.chinatele.app.amctrl.rest.vo.request.ReleaseAddrPoolInfo;
import com.chinatele.app.amctrl.service.ControllerService;
import com.chinatele.app.amctrl.util.Constants;
import com.chinatele.app.amctrl.util.JsonUtil;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Path("/")
public class NorthBound {

    private Logger log = LoggerFactory.getLogger(NorthBound.class);

    private ControllerService controllerService;

    @Context
    private ServletContext servletContext;

    private ControllerService getControllerService() {
        if (controllerService == null) {
            controllerService = WebApplicationContextUtils.getWebApplicationContext(servletContext).getBean(ControllerService.class);
        }
        return controllerService;
    }

    @Path("hello/{name}")
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response testHello(@PathParam("name") String name) {
        log.info("receive:" + name);
        ResponseVo vo = new ResponseVo();
        vo.setMessage("success");
        ObjectNode objectNode = JsonUtil.buildResponse(vo);
        return Response.ok(objectNode.toString()).build();
    }

    /**
     * 
     * @description app对控制器进行配置
     * @author zhoudr
     * @time 2015-12-1 下午4:52:36
     * @param requestJson
     * @return
     */
    @Path("app_configure_controller_msg")
    @POST
    @Produces(value = MediaType.APPLICATION_JSON)
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response configController(String requestJson) {
    	log.info("Start NorthBound::configController with inputted parameters: "+ requestJson);
        ResponseVo vo = null;
        if (!StringUtils.isBlank(requestJson)) {
            log.info(requestJson);
            try {
                ConfigInfo configInfo = JsonUtil.convertToJavaBean(requestJson, ConfigInfo.class);
                vo = getControllerService().config(configInfo);
            } catch (AmCtrlException e) {
                log.error("" + e);
                throw e;
            } catch (Exception e) {
                log.error("" + e);
                throw new AmCtrlException(Constants.RETURN_CODE_PARAMETER_NAME_PARSE_ERROR, Constants.PARAMETER_NAME_PARSE_ERROR_MESSAGE);
            }
        } else {
            throw new AmCtrlException(Constants.RETURN_CODE_LACK_KEY_PARAMETER_ERROR, Constants.LACK_KEY_PARAMETER_ERROR_MESSAGE);
        }
        ObjectNode objectNode = JsonUtil.buildResponse(vo);
        log.info("End NorthBound::configController with response "+ ReflectionToStringBuilder.toString(vo, ToStringStyle.MULTI_LINE_STYLE));
        return Response.ok(objectNode.toString()).build();
    }

    /**
     * 
     * @description app要求控制器回收指定设备的指定地址块
     * @author zhoudr
     * @time 2015-12-1 下午4:53:11
     * @param requestJson
     * @return
     */
    @Path("app_request_controller_recycle_msg")
    @POST
    @Produces(value = MediaType.APPLICATION_JSON)
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response recycleAddressBlock(String requestJson) {
    	log.info("Start NorthBound::recycleAddressBlock with inputted parameters: "+ requestJson);
        ResponseVo vo = null;
        if (!StringUtils.isBlank(requestJson)) {
            try {
                RecycleBlock recycleBlock = JsonUtil.convertToJavaBean(requestJson, RecycleBlock.class);
                vo = getControllerService().recycleAddress(recycleBlock);
            } catch (Exception e) {
                log.error("RecycleAddress fail exception occured. " + e);
                throw new AmCtrlException(Constants.RETURN_CODE_PARAMETER_NAME_PARSE_ERROR, Constants.PARAMETER_NAME_PARSE_ERROR_MESSAGE);
            }
        } else {
            throw new AmCtrlException(Constants.RETURN_CODE_LACK_KEY_PARAMETER_ERROR, Constants.LACK_KEY_PARAMETER_ERROR_MESSAGE);
        }
        ObjectNode objectNode = JsonUtil.buildResponse(vo);
        log.info("End NorthBound::recycleAddressBlock with response "+ ReflectionToStringBuilder.toString(vo, ToStringStyle.MULTI_LINE_STYLE));
        return Response.ok(objectNode.toString()).build();
    }

    /**
     * 
     * @description app向控制器询问device存活情况(指定controller的所连接的所有设备)
     * @author zhoudr
     * @time 2015-12-1 下午4:53:54
     * @param requestJson
     * @return
     */
    @Path("app_send_heartbeat_to_controller_msg")
    @POST
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getDeviceState(String requestJson) {
    	log.info("Start NorthBound::getDeviceState with inputted parameters: " + requestJson);
        ResponseVo vo = null;
        if (!StringUtils.isBlank(requestJson)) {
            try{
                String requestControllerId = JsonUtil.getObjectMapper().readTree(requestJson).get("controller_id").toString();
                log.info("获取心跳信息[controller_id={}]", requestControllerId);
                vo = getControllerService().getDeviceAliveState(Integer.parseInt(requestControllerId));
            } catch(Exception e) {
                log.error("" + e);
                throw new AmCtrlException(Constants.RETURN_CODE_PARAMETER_NAME_PARSE_ERROR, Constants.PARAMETER_NAME_PARSE_ERROR_MESSAGE);
            }
        } else {
            throw new AmCtrlException(Constants.RETURN_CODE_LACK_KEY_PARAMETER_ERROR, Constants.LACK_KEY_PARAMETER_ERROR_MESSAGE);
        }
        ObjectNode objectNode = JsonUtil.buildResponse(vo);
        log.info("End NorthBound::getDeviceState with response: " + ReflectionToStringBuilder.toString(vo, ToStringStyle.MULTI_LINE_STYLE));
        return Response.ok(objectNode.toString()).build();
    }

    @Path("app_to_controller_manual_address_allocation_msg")
    @POST
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response allocateAddressBlock(String requestJson) {
    	log.info("Start NorthBound::allocateAddressBlock with inputted parameters: " + requestJson);
        ResponseVo vo = null;
        if (!StringUtils.isBlank(requestJson)) {
            log.info(requestJson);
            try {
                AllocateBlock allocateBlock = JsonUtil.convertToJavaBean(requestJson, AllocateBlock.class);
                // dns_server_list的切割
                String dnsServerList = allocateBlock.getDns_server_list();
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
                vo = getControllerService().allocateAddress(allocateBlock);
            } catch (Exception e) {
                log.error("" + e);
                throw new AmCtrlException(Constants.RETURN_CODE_PARAMETER_NAME_PARSE_ERROR, Constants.PARAMETER_NAME_PARSE_ERROR_MESSAGE);
            }
        } else {
            throw new AmCtrlException(Constants.RETURN_CODE_LACK_KEY_PARAMETER_ERROR, Constants.LACK_KEY_PARAMETER_ERROR_MESSAGE);
        }
        ObjectNode objectNode = JsonUtil.buildResponse(vo);
        log.info("End NorthBound::allocateAddressBlock with response: " + ReflectionToStringBuilder.toString(vo, ToStringStyle.MULTI_LINE_STYLE));
        return Response.ok(objectNode.toString()).build();
    }

    /**
     * 
     * @description app向控制器下发强制指定设备下线的指令
     * @author zhoudr
     * @time 2015-12-1 下午4:57:52
     * @param requestJson
     * @return
     */
    @Path("app_to_controller_manual_offline_device_msg")
    @POST
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response forceDeviceDown(String requestJson) {
    	log.info("Start NorthBound::forceDeviceDown with inputted parameters: " + requestJson);
        ResponseVo vo = null;
        if (!StringUtils.isBlank(requestJson)) {
            try{
                String deviceId = JsonUtil.getObjectMapper().readTree(requestJson).get("device_id").toString();
                log.info("强制设备[device_id={}]下线", deviceId);
                vo = getControllerService().forceDeviceDown(Integer.parseInt(deviceId));
            } catch(Exception e) {
                log.error("" + e);
                throw new AmCtrlException(Constants.RETURN_CODE_PARAMETER_NAME_PARSE_ERROR, Constants.PARAMETER_NAME_PARSE_ERROR_MESSAGE);
            }
        } else {
            throw new AmCtrlException(Constants.RETURN_CODE_LACK_KEY_PARAMETER_ERROR, Constants.LACK_KEY_PARAMETER_ERROR_MESSAGE);
        }
        ObjectNode objectNode = JsonUtil.buildResponse(vo);
        log.info("End NorthBound::forceDeviceDown with response: " + ReflectionToStringBuilder.toString(vo, ToStringStyle.MULTI_LINE_STYLE));
        return Response.ok(objectNode.toString()).build();
    }
    
    /**
     * @deprecated
     * @description APP要求控制器回收指定device的指定地址池
     * @author xiey
     * @param requestJson
     * @return
     */
    @Path("app_request_controller_recycle_pool_msg")
    @POST
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response releaseAddressPool(String requestJson) {
        ResponseVo vo = null;
        log.info(requestJson);
        if (!StringUtils.isBlank(requestJson)) {
            try{
            	ReleaseAddrPoolInfo releaseAddrPoolInfo = JsonUtil.convertToJavaBean(requestJson, ReleaseAddrPoolInfo.class);
            	log.info("释放地址池");
            	vo = getControllerService().deleteAddressPool(releaseAddrPoolInfo);
            } catch(Exception e) {
                log.error("" + e);
                throw new AmCtrlException(Constants.RETURN_CODE_PARAMETER_NAME_PARSE_ERROR, Constants.PARAMETER_NAME_PARSE_ERROR_MESSAGE);
            }
        } else {
            throw new AmCtrlException(Constants.RETURN_CODE_LACK_KEY_PARAMETER_ERROR, Constants.LACK_KEY_PARAMETER_ERROR_MESSAGE);
        }
        ObjectNode objectNode = JsonUtil.buildResponse(vo);
        return Response.ok(objectNode.toString()).build();
    }

}
