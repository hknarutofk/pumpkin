package xingkong.tool.core.web.form;

import lombok.Data;
import xingkong.tool.core.StringTool;

/**
 * 通用排序Form
 *
 * @author EPAT
 */
@Data
public class BaseForm {

    /**
     * 排序列名
     */
    private String columnName;

    /**
     * 排序类型 desc asc
     */
    private Boolean isAsc;

    public void setColumnName(String columnName) {
        this.columnName = StringTool.toUnderline(columnName);
    }

}
