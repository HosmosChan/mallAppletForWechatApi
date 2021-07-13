package com.lettuce.management.dto;

import java.io.Serializable;
import java.util.List;

public class GenerateDetail implements Serializable {
    private static final long serialVersionUID = -4838063460681344769L;
    private String beanName;
    private List<BeanField> fields;

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public List<BeanField> getFields() {
        return fields;
    }

    public void setFields(List<BeanField> fields) {
        this.fields = fields;
    }
}