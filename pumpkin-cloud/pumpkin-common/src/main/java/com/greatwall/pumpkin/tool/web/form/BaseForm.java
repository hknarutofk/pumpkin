package com.greatwall.pumpkin.tool.web.form;

import com.greatwall.pumpkin.tool.StringTool;

import lombok.Data;

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
