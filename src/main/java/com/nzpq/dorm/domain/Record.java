package com.nzpq.dorm.domain;

import java.io.Serializable;
import java.util.Date;

//缺勤记录表
public class Record implements Serializable {

    private Integer id;
    private Integer studentId;
    private Date date;//缺勤记录时间
    private String remark;//缺勤备注
    private Integer disabled;

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getDisabled() {
        return disabled;
    }

    public void setDisabled(Integer disabled) {
        this.disabled = disabled;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", date=" + date +
                ", remark='" + remark + '\'' +
                ", disabled=" + disabled +
                ", user=" + user +
                '}';
    }
}
