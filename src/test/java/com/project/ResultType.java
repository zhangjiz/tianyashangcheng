package com.project;

/**
 * @author yuan
 * @date 2018/11/15 0015
 */

public class ResultType {
    private String name;
    private String type;
    private String remark;
    private String column;

    public ResultType(String name, String type, String remark, String column) {
        this.name = name;
        this.type = type;
        this.remark = remark;
        this.column = column;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }
}
