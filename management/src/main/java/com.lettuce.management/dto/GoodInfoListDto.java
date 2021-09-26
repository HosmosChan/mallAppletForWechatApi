package com.lettuce.management.dto;

import com.lettuce.management.entity.GoodInfo;

import java.util.List;

public class GoodInfoListDto {
    private String appId;
    private Long goodId;
    private String goodName;
    private Long categoryId;
    private List<GoodInfo> goodInfoList;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Long getGoodId() {
        return goodId;
    }

    public void setGoodId(Long goodId) {
        this.goodId = goodId;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public List<GoodInfo> getGoodInfoList() {
        return goodInfoList;
    }

    public void setGoodInfoList(List<GoodInfo> goodInfoList) {
        this.goodInfoList = goodInfoList;
    }
}
