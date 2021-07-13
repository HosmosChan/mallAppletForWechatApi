package com.lettuce.management.entity;

public class Dict extends BaseEntity<Long> {

    private static final long serialVersionUID = 4609570483531883147L;
    private String type;
    private String key;
    private String value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
