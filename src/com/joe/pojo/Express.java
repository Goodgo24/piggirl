package com.joe.pojo;

public class Express {
    private Integer expressId;

    private Integer orderId;

    private Integer expressNum;

    private String address;

    private String name;

    private Integer tel;

    private String otherContact;

    private String expressTrail;

    private Short expressState;

    private String expressCompany;

    public Integer getExpressId() {
        return expressId;
    }

    public void setExpressId(Integer expressId) {
        this.expressId = expressId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getExpressNum() {
        return expressNum;
    }

    public void setExpressNum(Integer expressNum) {
        this.expressNum = expressNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getTel() {
        return tel;
    }

    public void setTel(Integer tel) {
        this.tel = tel;
    }

    public String getOtherContact() {
        return otherContact;
    }

    public void setOtherContact(String otherContact) {
        this.otherContact = otherContact == null ? null : otherContact.trim();
    }

    public String getExpressTrail() {
        return expressTrail;
    }

    public void setExpressTrail(String expressTrail) {
        this.expressTrail = expressTrail == null ? null : expressTrail.trim();
    }

    public Short getExpressState() {
        return expressState;
    }

    public void setExpressState(Short expressState) {
        this.expressState = expressState;
    }

    public String getExpressCompany() {
        return expressCompany;
    }

    public void setExpressCompany(String expressCompany) {
        this.expressCompany = expressCompany == null ? null : expressCompany.trim();
    }
}