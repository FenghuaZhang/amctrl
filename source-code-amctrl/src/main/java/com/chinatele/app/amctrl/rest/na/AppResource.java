package com.chinatele.app.amctrl.rest.na;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinatele.app.amctrl.rest.vo.ResponseVo;
import com.chinatele.app.amctrl.util.Constants;
import com.chinatele.app.amctrl.util.JsonUtil;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Path("/na")
public class AppResource {

    private static Logger log = LoggerFactory.getLogger(AppResource.class);

    @Path("controller_report_new_Device_to_controller_msg")
    @POST
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response reportNewDevice(String requestJson) {
        ResponseVo vo = new ResponseVo();
        if (!StringUtils.isBlank(requestJson)) {
            try {
                String deviceId = JsonUtil.getObjectMapper().readTree(requestJson).get("device_id").toString();
                log.info("device_id={}", deviceId);
                vo.setMessage(Constants.RETURN_MESSAGE_SUCCESS);
            } catch (Exception e) {
                log.error("" + e);
                vo.setMessage(Constants.RETURN_MESSAGE_FAIL);
            }
        } else {
            vo.setMessage(Constants.RETURN_MESSAGE_FAIL);
            vo.setReturn_code(Constants.RETURN_CODE_LACK_KEY_PARAMETER_ERROR);
        }
        ObjectNode objectNode = JsonUtil.buildResponse(vo);
        return Response.ok(objectNode.toString()).build();
    }

    @Path("controller_report_Device_status_to_app_msg")
    @POST
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response reportPoolState(String requestJson) {
        ResponseVo vo = new ResponseVo();
        if (!StringUtils.isBlank(requestJson)) {
            log.info("pool status={}", requestJson);
            vo.setMessage(Constants.RETURN_MESSAGE_SUCCESS);
        } else {
            vo.setMessage(Constants.RETURN_MESSAGE_FAIL);
            vo.setReturn_code(Constants.RETURN_CODE_LACK_KEY_PARAMETER_ERROR);
        }
        ObjectNode objectNode = JsonUtil.buildResponse(vo);
        return Response.ok(objectNode.toString()).build();
    }

    @Path("controller_report_address_processing_result_msg")
    @POST
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response reportAddressBlockConfirm(String requestJson) {
        ResponseVo vo = new ResponseVo();
        if (!StringUtils.isBlank(requestJson)) {
            log.info("address_block_confirm={}", requestJson);
            vo.setMessage(Constants.RETURN_MESSAGE_SUCCESS);
        } else {
            vo.setMessage(Constants.RETURN_MESSAGE_FAIL);
            vo.setReturn_code(Constants.RETURN_CODE_LACK_KEY_PARAMETER_ERROR);
        }
        ObjectNode objectNode = JsonUtil.buildResponse(vo);
        return Response.ok(objectNode.toString()).build();
    }

    @Path("controller-report-app-device-down")
    @POST
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response reportDeviceDown(String requestJson) {
        ResponseVo vo = new ResponseVo();
        JSONObject deviceDownNode = JSONObject.fromObject(requestJson).getJSONObject("device_down");
        if (deviceDownNode != null) {
            log.info("device_down={}", deviceDownNode.toString());
            vo.setMessage(Constants.RETURN_MESSAGE_SUCCESS);
        } else {
            vo.setMessage(Constants.RETURN_MESSAGE_FAIL);
            vo.setReturn_code(Constants.RETURN_CODE_LACK_KEY_PARAMETER_ERROR);
        }
        ObjectNode objectNode = JsonUtil.buildResponse(vo);
        return Response.ok(objectNode.toString()).build();
    }
}
