package com.greatwall.pumpkin.tool;

import com.greatwall.pumpkin.tool.web.vo.PageResponseVo;
import com.greatwall.pumpkin.tool.web.vo.ResponseVo;

public class PageResponseTool {
    public static PageResponseVo error(String message) {
        return new PageResponseVo(ResponseVo.ERROR, message);
    }

    public static PageResponseVo error(String message, String log) {
        return new PageResponseVo(ResponseVo.ERROR, message, log);
    }

    public static PageResponseVo success() {
        return new PageResponseVo(ResponseVo.OK, "OK");
    }

    public static PageResponseVo success(Object data) {
        return new PageResponseVo(ResponseVo.OK, "OK", data);
    }

    public static PageResponseVo success(Object data, String message) {
        return new PageResponseVo(ResponseVo.OK, message, data);
    }
}
