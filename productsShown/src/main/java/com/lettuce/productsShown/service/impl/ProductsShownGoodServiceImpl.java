package com.lettuce.productsShown.service.impl;

import com.lettuce.common.utils.BeanUtil;
import com.lettuce.productsShown.dao.ProductsShownGoodDao;
import com.lettuce.productsShown.entity.*;
import com.lettuce.productsShown.service.ProductsShownGoodService;
import com.lettuce.productsShown.vo.GoodDetailVO;
import com.lettuce.productsShown.vo.GoodForIndexVO;
import com.lettuce.productsShown.vo.GoodInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
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
 * @description 获取商品实现层
 * @date 2021年06月27日
 */
@Service("productsShownGoodService")
public class ProductsShownGoodServiceImpl implements ProductsShownGoodService {
    @Autowired
    private ProductsShownGoodDao productsShownGoodDao;

    /**
     * @param number
     * @param appId
     * @return List<GoodForIndexVO>
     * @description 特价商品实现层(首页调用)
     * @author Hosmos
     * @date 2021-06-27
     */
    @Override
    public List<GoodForIndexVO> getSpecialPriceGoodsForIndex(int number, String appId) {
        List<GoodForIndexVO> goodForIndexVOs = new ArrayList<>();
        List<GoodBase> goodBaseInfos = productsShownGoodDao.getSpecialPriceGoodBaseInfo(number, appId);
        if (!CollectionUtils.isEmpty(goodBaseInfos)) {
            goodForIndexVOs = BeanUtil.copyList(goodBaseInfos, GoodForIndexVO.class);
            for (GoodForIndexVO goodForIndexVO : goodForIndexVOs) {
                shortenGoodTitle(goodForIndexVO);
            }
        }
        return goodForIndexVOs;
    }

    /**
     * @param number
     * @param appId
     * @return List<GoodForIndexVO>
     * @description 折扣商品实现层(首页调用)
     * @author Hosmos
     * @date 2021-07-06
     */
    @Override
    public List<GoodForIndexVO> getDiscountGoodsForIndex(int number, String appId) {
        List<GoodForIndexVO> goodForIndexVOs = new ArrayList<>();
        List<GoodBase> goodBaseInfos = productsShownGoodDao.getDiscountGoodBaseInfo(number, appId);
        if (!CollectionUtils.isEmpty(goodBaseInfos)) {
            goodForIndexVOs = BeanUtil.copyList(goodBaseInfos, GoodForIndexVO.class);
            for (GoodForIndexVO goodForIndexVO : goodForIndexVOs) {
                shortenGoodTitle(goodForIndexVO);
            }
        }
        return goodForIndexVOs;
    }

    /**
     * @param number
     * @param appId
     * @return List<GoodForIndexVO>
     * @description 热卖商品实现层(首页调用)
     * @author Hosmos
     * @date 2021-07-05
     */
    @Override
    public List<GoodForIndexVO> getHotGoodsForIndex(int number, String appId) {
        List<GoodForIndexVO> goodForIndexVOs = new ArrayList<>();
        List<GoodBase> goodBaseInfos = productsShownGoodDao.getHotGoodBaseInfo(number, appId);
        if (!CollectionUtils.isEmpty(goodBaseInfos)) {
            goodForIndexVOs = BeanUtil.copyList(goodBaseInfos, GoodForIndexVO.class);
            for (GoodForIndexVO goodForIndexVO : goodForIndexVOs) {
                shortenGoodTitle(goodForIndexVO);
            }
        }
        return goodForIndexVOs;
    }

    /**
     * @param number
     * @param appId
     * @return List<GoodForIndexVO>
     * @description 最新商品实现层(首页调用)
     * @author Hosmos
     * @date 2021-07-05
     */
    @Override
    public List<GoodForIndexVO> getNewGoodsForIndex(int number, String appId) {
        List<GoodForIndexVO> goodForIndexVOs = new ArrayList<>();
        List<GoodBase> goodBaseInfos = productsShownGoodDao.getNewGoodBaseInfo(number, appId);
        if (!CollectionUtils.isEmpty(goodBaseInfos)) {
            goodForIndexVOs = BeanUtil.copyList(goodBaseInfos, GoodForIndexVO.class);
            for (GoodForIndexVO goodForIndexVO : goodForIndexVOs) {
                shortenGoodTitle(goodForIndexVO);
            }
        }
        return goodForIndexVOs;
    }

    /**
     * @param goodId
     * @param appId
     * @return GoodDetailVO
     * @description 商品信息实现层(商品页调用)
     * @author Hosmos
     * @date 2021-07-05
     */
    @Override
    public GoodDetailVO getGoodDetailByGoodId(Long goodId, String appId) {
        GoodBase goodBase = productsShownGoodDao.getGoodBaseInfo(goodId, appId);
        if (goodBase == null) {
            return null;
        }
        GoodDetail goodDetail = productsShownGoodDao.getGoodDetailInfo(goodId, appId);
        List<GoodDeliverWay> goodDeliverWay = productsShownGoodDao.getGoodDeliverWay(goodId, appId);
        List<Long> goodDeliverWayList = null;
        if (!CollectionUtils.isEmpty(goodDeliverWay)) {
            for (GoodDeliverWay goodDeliverWayStr : goodDeliverWay) {
                goodDeliverWayList.add(goodDeliverWayStr.getDeliverWay());
            }
        }
        GoodDiscount goodDiscount = productsShownGoodDao.getGoodDiscount(goodId, appId);
        GoodDetailVO goodsDetailVO = new GoodDetailVO();
        BeanUtil.copyProperties(goodBase, goodsDetailVO);
        BeanUtil.copyProperties(goodDetail, goodsDetailVO);
        BeanUtil.copyProperties(goodDeliverWay, goodsDetailVO);
        BeanUtil.copyProperties(goodDiscount, goodsDetailVO);
        goodsDetailVO.setDeliverWay(goodDeliverWayList);
        return goodsDetailVO;
    }

    /**
     * @param goodId
     * @param appId
     * @return GoodDetailVO
     * @description 商品下拉详细信息实现层(商品页调用)
     * @author Hosmos
     * @date 2021-07-08
     */
    @Override
    public List<GoodInfoVO> getGoodInfoByGoodId(Long goodId, String appId) {
        List<GoodInfo> goodInfos = productsShownGoodDao.getGoodInfoList(goodId, appId);
        List<GoodInfoVO> goodInfoVOs = new ArrayList<>();
        if (!CollectionUtils.isEmpty(goodInfos)) {
            goodInfoVOs = BeanUtil.copyList(goodInfos, GoodInfoVO.class);
        }
        return goodInfoVOs;
    }

    /**
     * @param goodForIndexVO
     * @return GoodForIndexVO
     * @description 商品标题字符串过长导致文字超出的问题
     * @author Hosmos
     * @date 2021-07-05
     */
    private static GoodForIndexVO shortenGoodTitle(GoodForIndexVO goodForIndexVO) {
        String goodName = goodForIndexVO.getGoodName();
        if (goodName.length() > 30) {
            goodName = goodName.substring(0, 30) + "...";
            goodForIndexVO.setGoodName(goodName);
        }
        return goodForIndexVO;
    }
}
