package com.lettuce.mall.vo;

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
 * @description 折扣商品VO
 * @date 2021年06月27日
 */
public class GoodForIndexVO implements Serializable {
    private static final long serialVersionUID = -2821148629411447420L;
    @ApiModelProperty("商品Id")
    private Long goodId;
    @ApiModelProperty("商品名")
    private String goodName;
    @ApiModelProperty("商品原价")
    private BigDecimal goodPrice;
    @ApiModelProperty("商品详情跳转url")
    private String goodUrl;
    @ApiModelProperty("商品封面")
    private String goodCoverImg;
    @ApiModelProperty("商品优惠金额")
    private BigDecimal discountPrice;
    @ApiModelProperty("购买人数")
    private Integer paymentNo;
    @ApiModelProperty("库存")
    private Integer stock;
    @ApiModelProperty("售卖时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date sellTime;

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

    public BigDecimal getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(BigDecimal goodPrice) {
        this.goodPrice = goodPrice;
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

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
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
