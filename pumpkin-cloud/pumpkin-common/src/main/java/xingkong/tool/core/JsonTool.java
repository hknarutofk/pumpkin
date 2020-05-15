package xingkong.tool.core;

import java.io.IOException;
import java.util.ArrayList;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author 星空
 *
 */
@Slf4j
public class JsonTool {

    public static String toJSONString(Object object) {
        return JsonTool.toJSONString(object, true);
    }

    public static String toJSONString(Object object, boolean beauty) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonText = null;
        try {
            jsonText = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("JsonProcessingException", e);
        }
        return jsonText;
    }

    public static Object parseObject(String jsonText, Class classType) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(jsonText, classType);
        } catch (JsonParseException e) {
            log.error("JsonParseException", e);
        } catch (JsonMappingException e) {
            log.error("JsonMappingException", e);
        } catch (IOException e) {
            log.error("IOException", e);
        }
        return null;
    }

    /**
     * 将json字符串转换为json对象
     * 
     * @param jsonText
     * @return
     */
    public static JSONObject parseObject(String jsonText) {
        try {
            return JSON.parseObject(jsonText);
        } catch (Exception e) {
            log.error("Exception", e);
        }
        return null;
    }

    /**
     * 返回数组
     * 
     * @param jsonText
     * @param classType
     * @return
     */
    public static Object parseArray(String jsonText, Class classType) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, classType);
            return mapper.readValue(jsonText, javaType);

        } catch (JsonParseException e) {
            log.error("JsonParseException", e);
        } catch (JsonMappingException e) {
            log.error("JsonMappingException", e);
        } catch (IOException e) {
            log.error("IOException", e);
        }
        return null;
    }
}
