package com.nzpq.dorm.domain;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {

    private Integer id;
    private String name;
    private String password;
    private String stuCode;//学号
    private String sex;
    private String tel;
    private Integer dormBuildId;//宿舍楼号
    private String dormCode;//宿舍号
    private Integer roleId;//角色id 2：学生 1：宿管 0：超级管理员
    private Integer createUserId;//创建宿管的id
    private Integer disabled;//是否被删除 0:未被删除  1：已被删除

    private List<DormBuild> builds;//宿管管理的宿舍楼
    private DormBuild dormBuild;//学生住的宿舍楼

    public DormBuild getDormBuild() {
        return dormBuild;
    }

    public void setDormBuild(DormBuild dormBuild) {
        this.dormBuild = dormBuild;
    }

    public Integer getDormBuildId() {
        return dormBuildId;
    }

    public void setDormBuildId(Integer dormBuildId) {
        this.dormBuildId = dormBuildId;
    }

    public List<DormBuild> getBuilds() {
        return builds;
    }

    public void setBuilds(List<DormBuild> builds) {
        this.builds = builds;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStuCode() {
        return stuCode;
    }

    public void setStuCode(String stuCode) {
        this.stuCode = stuCode;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getDrumBuildId() {
        return dormBuildId;
    }

    public void setDrumBuildId(Integer drumBuildId) {
        this.dormBuildId = drumBuildId;
    }

    public String getDormCode() {
        return dormCode;
    }

    public void setDormCode(String dormCode) {
        this.dormCode = dormCode;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Integer getDisabled() {
        return disabled;
    }

    public void setDisabled(Integer disabled) {
        this.disabled = disabled;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", stuCode='" + stuCode + '\'' +
                ", sex='" + sex + '\'' +
                ", tel='" + tel + '\'' +
                ", dormBuildId=" + dormBuildId +
                ", dormCode='" + dormCode + '\'' +
                ", roleId=" + roleId +
                ", createUserId=" + createUserId +
                ", disabled=" + disabled +
                ", builds=" + builds +
                ", dormBuild=" + dormBuild +
                '}';
    }

}
