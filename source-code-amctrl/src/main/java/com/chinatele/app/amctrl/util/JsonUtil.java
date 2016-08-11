package com.chinatele.app.amctrl.util;

import java.io.IOException;

import net.sf.json.JSONObject;

import com.chinatele.app.amctrl.rest.vo.ResponseVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T convertToJavaBean(String json, Class<T> clazz) throws IOException {
        T t = objectMapper.readValue(json, clazz);
        return t;
    }

    public static ObjectNode wrapJavaBean(String key, Object value) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.putPOJO(key, value);
        return objectNode;
    }

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public static void setObjectMapper(ObjectMapper objectMapper) {
        JsonUtil.objectMapper = objectMapper;
    }

    public static ObjectNode buildResponse(ResponseVo vo) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("return_code", vo.getReturn_code());
        objectNode.put("message", vo.getMessage());
        JSONObject result = JSONObject.fromObject(vo.getResult());
        objectNode.put("result", result.toString());
        return objectNode;
    }
}
