package com.joe.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class ExtraPay {
    private Integer extraPayId;

    private String payFor;

    private BigDecimal money;

    private Integer payPepople;

    private Date date;

    public Integer getExtraPayId() {
        return extraPayId;
    }

    public void setExtraPayId(Integer extraPayId) {
        this.extraPayId = extraPayId;
    }

    public String getPayFor() {
        return payFor;
    }

    public void setPayFor(String payFor) {
        this.payFor = payFor == null ? null : payFor.trim();
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getPayPepople() {
        return payPepople;
    }

    public void setPayPepople(Integer payPepople) {
        this.payPepople = payPepople;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}