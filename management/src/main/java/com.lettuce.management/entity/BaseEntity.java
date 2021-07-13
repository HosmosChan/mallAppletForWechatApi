package com.lettuce.management.entity;

import java.io.Serializable;
import java.util.Date;

public abstract class BaseEntity<ID extends Serializable> implements Serializable {
    private static final long serialVersionUID = 5406929891162490190L;
    private ID tid;
    private Date createTime = new Date();
    private Date gmtTime = new Date();

    public ID getTid() {
        return tid;
    }

    public void setTid(ID tid) {
        this.tid = tid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getGmtTime() {
        return gmtTime;
    }

    public void setGmtTime(Date gmtTime) {
        this.gmtTime = gmtTime;
    }
}