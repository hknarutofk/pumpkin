package xingkong.tool.core;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;
import xingkong.tool.core.web.vo.PageResponseVo;
import xingkong.tool.core.web.vo.ResponseCode;
import xingkong.tool.core.web.vo.ResponseVo;

/**
 * 通用WEB项目返回响应Vo工具类
 *
 * @author 星空
 */
@Slf4j
public class ResponseTool {
    public static ResponseVo error(String message) {
        return new ResponseVo(ResponseCode.ERROR.getCode(), message);
    }

    public static ResponseVo error(String message, Object data) {
        if (data instanceof PageResponseVo) {
            ((PageResponseVo)data).setMessage(message);
            ((PageResponseVo)data).setCode(ResponseCode.ERROR.getCode());
            log.info("返回数据 message {} data {}", message, data);
            return ((PageResponseVo)data);
        } else {
            log.info("返回数据 message {} data {}", message, data);
            return new ResponseVo(ResponseCode.ERROR.getCode(), message, data);
        }
    }

    public static ResponseVo error(ResponseCode errorCode, Object data) {
        if (data instanceof PageResponseVo) {
            ((PageResponseVo)data).setMessage(errorCode.getMsg());
            ((PageResponseVo)data).setCode(errorCode.getCode());
            return ((PageResponseVo)data);
        } else {
            return new ResponseVo(errorCode.getCode(), errorCode.getMsg(), data);
        }
    }

    public static ResponseVo success() {
        return new ResponseVo(ResponseCode.OK.getCode(), "OK");
    }

    public static ResponseVo success(Object data) {
        if (data instanceof PageResponseVo) {
            ((PageResponseVo)data).setMessage(ResponseCode.OK.getMsg());
            ((PageResponseVo)data).setCode(ResponseCode.OK.getCode());
            return ((PageResponseVo)data);
        } else {
            return new ResponseVo(ResponseCode.OK.getCode(), "OK", data);
        }
    }

    public static ResponseVo success(Object data, String message) {
        if (data instanceof PageResponseVo) {
            ((PageResponseVo)data).setMessage(message);
            ((PageResponseVo)data).setCode(ResponseCode.OK.getCode());
            log.info("返回数据 message {} data {}", message, data);
            return ((PageResponseVo)data);
        } else {
            log.info("返回数据 message {} data {}", message, data);
            return new ResponseVo(ResponseCode.OK.getCode(), message, data);
        }
    }

    /**
     * 设置响应
     *
     * @param response
     *            HttpServletResponse
     * @param contentType
     *            content-type
     * @param status
     *            http状态码
     * @param value
     *            响应内容
     * @throws IOException
     *             IOException
     */
    public static void makeResponse(HttpServletResponse response, String contentType, int status, Object value)
        throws IOException {
        response.setContentType(contentType);
        response.setStatus(status);
        response.getOutputStream().write(JSONObject.toJSONString(value).getBytes());
    }
}
