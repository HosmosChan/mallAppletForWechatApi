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
 * @description
 * @date 2021年06月25日
 */
public class Constants {
    public final static String FILE_UPLOAD_DIC = "D:\\upload\\";//上传文件的默认url前缀，根据部署设置自行修改

    public final static Byte INDEX_CAROUSEL_INDEX = 0;//首页轮播图
    public final static Byte INDEX_CAROUSEL_GOOD = 1;//商品页轮播图

    public final static int INDEX_GOODS_SPECIAL_PRICE_NUMBER = 3;//首页特价商品数量(2.0用dict控制)
    public final static int INDEX_GOODS_DISCOUNT_NUMBER = 3;//首页折扣商品数量(2.0用dict控制)
    public final static int INDEX_GOODS_HOT_NUMBER = 3;//首页热卖商品数量(2.0用dict控制)
    public final static int INDEX_GOODS_NEW_NUMBER = 6;//首页最新商品数量(2.0用dict控制)

    public final static int SHOPPING_CART_ITEM_TOTAL_NUMBER = 20;//购物车中商品的最大数量(可根据自身需求修改)

    public final static int SHOPPING_CART_ITEM_LIMIT_NUMBER = 5;//购物车中单个商品的最大购买数量(可根据自身需求修改)

    public final static int GOODS_SEARCH_PAGE_LIMIT = 10;//搜索分页的默认条数(每页10条)

    public final static int SHOPPING_CART_PAGE_LIMIT = 5;//购物车分页的默认条数(每页5条)

    public final static int ORDER_SEARCH_PAGE_LIMIT = 5;//我的订单列表分页的默认条数(每页5条)

    public final static int SELL_STATUS_EXAMINE = 0;//商品审核状态
    public final static int SELL_STATUS_ON = 1;//商品上架状态
    public final static int SELL_STATUS_OFF = 2;//商品下架状态

    public final static int TOKEN_LENGTH = 32;//token字段长度

    public final static String USER_INTRO = "随新所欲，蜂富多彩";//默认简介
}