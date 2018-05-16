package com.joe.pojo;

public class SysParameter {
    private Integer sysParameterId;

    private String name;

    private String value;

    private Integer seq;

    private String desc;

    private Short deleted;

    public Integer getSysParameterId() {
        return sysParameterId;
    }

    public void setSysParameterId(Integer sysParameterId) {
        this.sysParameterId = sysParameterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    public Short getDeleted() {
        return deleted;
    }

    public void setDeleted(Short deleted) {
        this.deleted = deleted;
    }
}