package com.lettuce.mall.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

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
 * @description 商品基础实体类
 * @date 2021年06月28日
 */
public class GoodBase extends BaseEntity<Long> {
    private static final long serialVersionUID = 1494147529004393101L;
    private Long goodId;
    private Long parentId;
    private String goodName;
    private BigDecimal goodPrice;
    private String goodUrl;
    private String goodCoverImg;
    private Byte isDiscount;
    private BigDecimal discountPrice;
    private Integer paymentNo;
    private Integer stock;
    private Integer markNo;
    private Integer commentNo;
    private Byte sellStatus;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date sellTime;
    private Long creatUserId;
    private Long gmtUserId;

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

    public Byte getIsDiscount() {
        return isDiscount;
    }

    public void setIsDiscount(Byte isDiscount) {
        this.isDiscount = isDiscount;
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

    public Date getSellTime() {
        return sellTime;
    }

    public void setSellTime(Date sellTime) {
        this.sellTime = sellTime;
    }

    public Long getCreatUserId() {
        return creatUserId;
    }

    public void setCreatUserId(Long creatUserId) {
        this.creatUserId = creatUserId;
    }

    public Long getGmtUserId() {
        return gmtUserId;
    }

    public void setGmtUserId(Long gmtUserId) {
        this.gmtUserId = gmtUserId;
    }
}
