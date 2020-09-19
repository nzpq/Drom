package com.nzpq.dorm.domain;

import java.io.Serializable;

//宿舍楼实体类
public class DormBuild implements Serializable {

    private Integer id;
    private String name;
    private String remark;//宿舍楼备注
    private Integer disabled;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getDisabled() {
        return disabled;
    }

    public void setDisabled(Integer disabled) {
        this.disabled = disabled;
    }

    @Override
    public String toString() {
        return "DormBuild{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                ", disabled=" + disabled +
                '}';
    }
}
