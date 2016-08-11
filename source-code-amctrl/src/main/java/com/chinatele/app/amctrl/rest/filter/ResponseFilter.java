package com.chinatele.app.amctrl.rest.filter;

import java.io.IOException;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.chinatele.app.amctrl.rest.vo.ResponseVo;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;

public class ResponseFilter implements ContainerResponseFilter {

    @Override
    public ContainerResponse filter(ContainerRequest request, ContainerResponse response) {
        JSONObject obj = JSONObject.fromObject(response.getEntity());
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ResponseVo vo = objectMapper.readValue(obj.toString(), ResponseVo.class);
            if (StringUtils.isBlank(vo.getMessage())) {
                response.setEntity(vo.getResult());
            } else {
                response.setEntity(vo.getMessage());
            }
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

}
