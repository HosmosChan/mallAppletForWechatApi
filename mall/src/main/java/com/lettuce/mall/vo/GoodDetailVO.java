package com.lettuce.mall.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

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
 * @description 商品信息VO
 * @date 2021年07月05日
 */
public class GoodDetailVO implements Serializable {
    public static final long serialVersionUID = 2173986850096865968L;
    private Long goodId;
    private Long parentId;
    private String goodName;
    private BigDecimal goodPrice;
    private Byte isSpecialPrice;
    private BigDecimal specialPrice;
    private Byte isDiscount;
    private Integer paymentNo;
    private Integer stock;
    private Integer markNo;
    private Integer commentNo;
    private Byte sellStatus;
    private String goodDescribe;
    private Double goodSize;
    private Long companyId;
    private Double discountOff;
    private BigDecimal fullPrice;
    private List<String> deliverWay;

    public Long getGoodId() {
        return goodId;
    }

    public void setGoodId(Long goodId) {
        this.goodId = goodId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
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

    public Byte getIsSpecialPrice() {
        return isSpecialPrice;
    }

    public void setIsSpecialPrice(Byte isSpecialPrice) {
        this.isSpecialPrice = isSpecialPrice;
    }

    public BigDecimal getSpecialPrice() {
        return specialPrice;
    }

    public void setSpecialPrice(BigDecimal specialPrice) {
        this.specialPrice = specialPrice;
    }

    public Byte getIsDiscount() {
        return isDiscount;
    }

    public void setIsDiscount(Byte isDiscount) {
        this.isDiscount = isDiscount;
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

    public Integer getMarkNo() {
        return markNo;
    }

    public void setMarkNo(Integer markNo) {
        this.markNo = markNo;
    }

    public Integer getCommentNo() {
        return commentNo;
    }

    public void setCommentNo(Integer commentNo) {
        this.commentNo = commentNo;
    }

    public Byte getSellStatus() {
        return sellStatus;
    }

    public void setSellStatus(Byte sellStatus) {
        this.sellStatus = sellStatus;
    }

    public String getGoodDescribe() {
        return goodDescribe;
    }

    public void setGoodDescribe(String goodDescribe) {
        this.goodDescribe = goodDescribe;
    }

    public Double getGoodSize() {
        return goodSize;
    }

    public void setGoodSize(Double goodSize) {
        this.goodSize = goodSize;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
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

    public List<String> getDeliverWay() {
        return deliverWay;
    }

    public void setDeliverWay(List<String> deliverWay) {
        this.deliverWay = deliverWay;
    }
}
