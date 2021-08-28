package com.lettuce.management.dto;

import com.lettuce.management.entity.GoodBase;

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
 * 商品 dto
 * @author Hosmos
 * @date 2021年08月27日
 */
public class GoodDto extends GoodBase {
    private static final long serialVersionUID = -3945849058875084283L;
    private String goodDescribe;
    private String goodSize;
    private Long companyId;
    private Double discountOff;
    private BigDecimal fullPrice;
    private List<String> deliverWay;

    public String getGoodDescribe() {
        return goodDescribe;
    }

    public void setGoodDescribe(String goodDescribe) {
        this.goodDescribe = goodDescribe;
    }

    public String getGoodSize() {
        return goodSize;
    }

    public void setGoodSize(String goodSize) {
        this.goodSize = goodSize;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public double getDiscountOff() {
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
