package com.lettuce.productsShown.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Code is far away from bug with the animal protected
 * 　┏┓　　  ┏┓
 * ┏┻┻━━━┻┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┣┛
 * 　　┗┳┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 * @author Hosmos
 * @description
 * @date 2021年07月06日
 */
public class SearchVO implements Serializable {
    private static final long serialVersionUID = -4751582924299529726L;
    @ApiModelProperty("APPId")
    private String appId;
    @ApiModelProperty("商品Id")
    private Long goodId;
    @ApiModelProperty("商品名")
    private String goodName;
    @ApiModelProperty("商品最高原价")
    private BigDecimal goodMaxPrice;
    @ApiModelProperty("商品最低原价")
    private BigDecimal goodMinPrice;
    @ApiModelProperty("商品详情跳转url")
    private String goodUrl;
    @ApiModelProperty("商品封面")
    private String goodCoverImg;
    @ApiModelProperty("商品特价金额")
    private BigDecimal specialPrice;
    @ApiModelProperty("商品折扣数")
    private Double discountOff;
    @ApiModelProperty("满金额折扣")
    private BigDecimal fullPrice;
    @ApiModelProperty("购买人数")
    private Integer paymentNo;
    @ApiModelProperty("库存")
    private Integer stock;
    @ApiModelProperty("售卖时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date sellTime;

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

    public BigDecimal getGoodMaxPrice() {
        return goodMaxPrice;
    }

    public void setGoodMaxPrice(BigDecimal goodMaxPrice) {
        this.goodMaxPrice = goodMaxPrice;
    }

    public BigDecimal getGoodMinPrice() {
        return goodMinPrice;
    }

    public void setGoodMinPrice(BigDecimal goodMinPrice) {
        this.goodMinPrice = goodMinPrice;
    }

    public String getGoodUrl() {
        return goodUrl;
    }

    public void setGoodUrl(String goodUrl) {
        this.goodUrl = goodUrl;
    }

    public String getGoodCoverImg() {
        return goodCoverImg;
    }

    public void setGoodCoverImg(String goodCoverImg) {
        this.goodCoverImg = goodCoverImg;
    }

    public BigDecimal getSpecialPrice() {
        return specialPrice;
    }

    public void setSpecialPrice(BigDecimal specialPrice) {
        this.specialPrice = specialPrice;
    }

    public Double getDiscountOff() {
        return discountOff;
    }

    public void setDiscountOff(Double discountOff) {
        this.discountOff = discountOff;
    }

    public BigDecimal getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(BigDecimal fullPrice) {
        this.fullPrice = fullPrice;
    }

    public Integer getPaymentNo() {
        return paymentNo;
    }

    public void setPaymentNo(Integer paymentNo) {
        this.paymentNo = paymentNo;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Date getSellTime() {
        return sellTime;
    }

    public void setSellTime(Date sellTime) {
        this.sellTime = sellTime;
    }
}
