package xingkong.tool.core.web.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 通用分页Form
 * 
 * @author 星空
 */
@Data
@ApiModel(value = "分页对象", description = "分页对象}")
public class PageForm extends BaseForm {

    /**
     * 需要显示的分页编号
     */
    @ApiModelProperty(value = "当前页", required = true)
    private Integer pageNo;

    /**
     * 每页展示条数
     */
    @ApiModelProperty(value = "每页展示条数", required = true)
    private Integer pageSize;
}
