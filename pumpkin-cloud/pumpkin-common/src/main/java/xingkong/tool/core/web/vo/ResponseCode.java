/**
 * Copyright 2019-2029 geekidea(https://github.com/geekidea)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package xingkong.tool.core.web.vo;

/**
 * <p>
 * REST API 响应码
 * </p>
 *
 * @author geekidea
 * @since 2018-11-08
 */
public enum ResponseCode {

    OK(200, "操作成功"),

    ERROR(500, "操作失败"),

    SYSTEM_EXCEPTION(5000, "系统异常!"),

    PARAMETER_EXCEPTION(5001, "请求参数校验异常"),

    PARAMETER_PARSE_EXCEPTION(5002, "请求参数解析异常"),

    HTTP_MEDIA_TYPE_EXCEPTION(5003, "HTTP Media 类型异常"),

    SYSTEM_LOGIN_EXCEPTION(5005, "系统登录异常"),

    SYSTEM_LOGIN_EXPIRE_EXCEPTION(5006, "系统登录过期异常"),

    SYSTEM_AUTHENTICATION_EXCEPTION(5007, "暂无权限，请联系管理员!"),

    USER_NOT_FOUND_EXCEPTION(5008, "用户不存在"),

    USER_REAL_NAME_AUTHENTICATION_EXCEPTION(5009, "用户未进行实名认证"),

    TRANSFORM_COMMAND_ERROR(9001, "命令转换错误"),

    DATA_NOT_EXIST(8003, "数据不存在"),

    USER_CV_EXIST(8002, "简历已存在"),

    USER_CV_NOT_EXIST(8001, "没有简历"),

    CV_SENT_TIME_SHORT(8004, "该职位简历三天内不能重复投递"),

    CV_SEND_TIMES_LARGE(8005, "该职位简历投递不能超过三次"),

    RECRUIT_COMPANY_NOT_ENTERED(8006, "企业信息未录入");

    private final int code;
    private final String msg;

    ResponseCode(final int code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ResponseCode getResponseCode(int code) {
        ResponseCode[] ecs = ResponseCode.values();
        for (ResponseCode ec : ecs) {
            if (ec.getCode() == code) {
                return ec;
            }
        }
        return OK;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
