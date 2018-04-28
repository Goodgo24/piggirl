package com.joe.pojo;

import java.util.Date;

public class PiggirlUser {
    private Integer piggirlUserId;

    private String loginName;

    private String password;

    private Date creatTime;

    private Short state;

    private Short deleted;

    public Integer getPiggirlUserId() {
        return piggirlUserId;
    }

    public void setPiggirlUserId(Integer piggirlUserId) {
        this.piggirlUserId = piggirlUserId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

    public Short getDeleted() {
        return deleted;
    }

    public void setDeleted(Short deleted) {
        this.deleted = deleted;
    }
}