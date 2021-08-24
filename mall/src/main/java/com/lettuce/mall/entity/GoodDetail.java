package com.lettuce.mall.entity;

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
 * 商品详细信息实体类
 *
 * @author Hosmos
 * @date 2021年07月05日
 */
public class GoodDetail extends BaseEntity<Long> {
    private static final long serialVersionUID = 7178457830818503645L;
    private Long goodId;
    private String goodDescribe;
    private Double goodSize;
    private Long companyId;
    private Long createUserId;
    private Long gmtUserId;

    public Long getGoodId() {
        return goodId;
    }

    public void setGoodId(Long goodId) {
        this.goodId = goodId;
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

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Long getGmtUserId() {
        return gmtUserId;
    }

    public void setGmtUserId(Long gmtUserId) {
        this.gmtUserId = gmtUserId;
    }
}
