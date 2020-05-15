package xingkong.tool.core.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 通用返回结果
 * 
 * @author 星空
 */

@Data
@ApiModel(value = "PageResponseVo对象", description = "返回分页对象}")
public class PageResponseVo extends ResponseVo {

    public PageResponseVo(int code, String message) {
        super(code, message);
    }

    public PageResponseVo() {
        super(ResponseVo.OK, "OK", null);
    }

    public PageResponseVo(Object data) {
        super(ResponseVo.OK, "OK", data);
    }

    public PageResponseVo(int code, String message, Object data) {
        super(code, message, data);
    }

    /**
     * 每页展示数据条数
     */
    @ApiModelProperty(value = "每页数目")
    private long pageSize;

    /**
     * 当前显示的数据页编号
     */
    @ApiModelProperty(value = "当前页")
    private long pageNo;

    /**
     * 数据总条数
     */
    @ApiModelProperty(value = "总条数")
    private long totalNum;

    /**
     * 数据总页数
     */
    @ApiModelProperty(value = "总页数")
    private long totalPage;
}
