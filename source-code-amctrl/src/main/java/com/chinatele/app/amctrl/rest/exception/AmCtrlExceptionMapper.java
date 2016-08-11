package com.chinatele.app.amctrl.rest.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.chinatele.app.amctrl.rest.vo.ResponseVo;

@Provider
public class AmCtrlExceptionMapper implements ExceptionMapper<AmCtrlException> {

    @Override
    public Response toResponse(AmCtrlException exception) {
        ResponseVo vo = new ResponseVo(); 
        vo.setReturn_code(exception.getCode());
        vo.setMessage(exception.getMessage());
        ResponseBuilder responseBuilder = Response.status(Response.Status.OK);
        responseBuilder.entity(vo);
        responseBuilder.type(MediaType.APPLICATION_JSON);
        return responseBuilder.build();
    }

}
