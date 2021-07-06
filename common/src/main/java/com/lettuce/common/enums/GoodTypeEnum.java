package com.lettuce.common.enums;

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
 * @description 商品类型enum，0-普通商品，1-（首页）特价商品，2-（首页）热卖商品，3-（首页）最新商品
 * @date 2021年06月27日
 */
public enum GoodTypeEnum {
    DEFAULT(0, "DEFAULT"),
    INDEX_GOODS_Special_Price(1, "INDEX_GOODS_Special_Price"),
    INDEX_GOODS_HOT(2, "INDEX_GOODS_HOT"),
    INDEX_GOODS_NEW(3, "INDEX_GOODS_NEW");

    private int type;

    private String name;

    GoodTypeEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public static GoodTypeEnum getGoodTypeEnum(int type) {
        for (GoodTypeEnum goodTypeEnum : GoodTypeEnum.values()) {
            if (goodTypeEnum.getType() == type) {
                return goodTypeEnum;
            }
        }
        return DEFAULT;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
