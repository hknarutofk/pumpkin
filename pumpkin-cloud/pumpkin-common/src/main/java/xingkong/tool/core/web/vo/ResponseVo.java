package xingkong.tool.core.web.vo;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 通用WEB项目返回响应Vo
 * 
 * @author 星空
 */
@Data
@ApiModel(value = "ResponseVo对象", description = "返回对象}")
public class ResponseVo<T> {

    public static final int OK = ResponseCode.OK.getCode();
    public static final int ERROR = ResponseCode.ERROR.getCode();

    /**
     * 响应的编码
     */
    @ApiModelProperty(value = "响应的编码，200成功，500失败")
    private int code;

    /**
     * 信息提示
     */
    @ApiModelProperty(value = "信息")
    private String message;

    /**
     * 返回结果对象
     */
    @ApiModelProperty(value = "结果")
    private T data;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date time;

    public ResponseVo() {
        this(ResponseVo.OK, "OK", null);
    }

    public ResponseVo(T data) {
        this(ResponseVo.OK, "OK", data);
    }

    public ResponseVo(String message) {
        this(ResponseVo.OK, message);
    }

    public ResponseVo(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseVo(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.time = new Date();
    }

}
