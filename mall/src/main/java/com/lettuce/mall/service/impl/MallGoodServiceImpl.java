package com.lettuce.mall.service.impl;

import com.lettuce.common.utils.BeanUtil;
import com.lettuce.mall.dao.MallGoodDao;
import com.lettuce.mall.entity.*;
import com.lettuce.mall.service.MallGoodService;
import com.lettuce.mall.vo.GoodDetailVO;
import com.lettuce.mall.vo.GoodForIndexVO;
import com.lettuce.mall.vo.GoodInfoVO;
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
 * 获取商品实现层
 *
 * @author Hosmos
 * @date 2021年06月27日
 */
@Service("mallGoodService")
public class MallGoodServiceImpl implements MallGoodService {
    @Autowired
    private MallGoodDao mallGoodDao;

    /**
     * 特价商品实现层(首页调用)
     *
     * @param number 特价商品数量
     * @param appId  app id
     * @return List<GoodForIndexVO>
     * @author Hosmos
     * @date 2021-06-27
     */
    @Override
    public List<GoodForIndexVO> getSpecialPriceGoodsForIndex(int number, String appId) {
        List<GoodForIndexVO> goodForIndexVOs = new ArrayList<>();
        List<GoodBase> goodBaseInfos = mallGoodDao.getSpecialPriceGoodBaseInfo(number, appId);
        if (!CollectionUtils.isEmpty(goodBaseInfos)) {
            goodForIndexVOs = BeanUtil.copyList(goodBaseInfos, GoodForIndexVO.class);
            for (GoodForIndexVO goodForIndexVO : goodForIndexVOs) {
                shortenGoodTitle(goodForIndexVO);
            }
        }
        return goodForIndexVOs;
    }

    /**
     * 折扣商品实现层(首页调用)
     *
     * @param number 折扣商品数量
     * @param appId  app id
     * @return List<GoodForIndexVO>
     * @author Hosmos
     * @date 2021-07-06
     */
    @Override
    public List<GoodForIndexVO> getDiscountGoodsForIndex(int number, String appId) {
        List<GoodForIndexVO> goodForIndexVOs = new ArrayList<>();
        List<GoodBase> goodBaseInfos = mallGoodDao.getDiscountGoodBaseInfo(number, appId);
        if (!CollectionUtils.isEmpty(goodBaseInfos)) {
            goodForIndexVOs = BeanUtil.copyList(goodBaseInfos, GoodForIndexVO.class);
            for (GoodForIndexVO goodForIndexVO : goodForIndexVOs) {
                shortenGoodTitle(goodForIndexVO);
            }
        }
        return goodForIndexVOs;
    }

    /**
     * 热卖商品实现层(首页调用)
     *
     * @param number 热卖商品数量
     * @param appId  app id
     * @return List<GoodForIndexVO>
     * @author Hosmos
     * @date 2021-07-05
     */
    @Override
    public List<GoodForIndexVO> getHotGoodsForIndex(int number, String appId) {
        List<GoodForIndexVO> goodForIndexVOs = new ArrayList<>();
        List<GoodBase> goodBaseInfos = mallGoodDao.getHotGoodBaseInfo(number, appId);
        if (!CollectionUtils.isEmpty(goodBaseInfos)) {
            goodForIndexVOs = BeanUtil.copyList(goodBaseInfos, GoodForIndexVO.class);
            for (GoodForIndexVO goodForIndexVO : goodForIndexVOs) {
                shortenGoodTitle(goodForIndexVO);
            }
        }
        return goodForIndexVOs;
    }

    /**
     * 最新商品实现层(首页调用)
     *
     * @param number 最新商品数量
     * @param appId  app id
     * @return List<GoodForIndexVO>
     * @author Hosmos
     * @date 2021-07-05
     */
    @Override
    public List<GoodForIndexVO> getNewGoodsForIndex(int number, String appId) {
        List<GoodForIndexVO> goodForIndexVOs = new ArrayList<>();
        List<GoodBase> goodBaseInfos = mallGoodDao.getNewGoodBaseInfo(number, appId);
        if (!CollectionUtils.isEmpty(goodBaseInfos)) {
            goodForIndexVOs = BeanUtil.copyList(goodBaseInfos, GoodForIndexVO.class);
            for (GoodForIndexVO goodForIndexVO : goodForIndexVOs) {
                shortenGoodTitle(goodForIndexVO);
            }
        }
        return goodForIndexVOs;
    }

    /**
     * 商品信息实现层(商品页调用)
     *
     * @param goodId 商品 id
     * @param appId  app id
     * @return GoodDetailVO
     * @author Hosmos
     * @date 2021-07-05
     */
    @Override
    public GoodDetailVO getGoodDetailByGoodId(Long goodId, String appId) {
        GoodBase goodBase = mallGoodDao.getGoodBaseInfo(goodId, appId);
        if (goodBase == null) {
            return null;
        }
        GoodDetail goodDetail = mallGoodDao.getGoodDetailInfo(goodId, appId);
        List<GoodDeliverWay> goodDeliverWay = mallGoodDao.getGoodDeliverWay(goodId, appId);
        List<Long> goodDeliverWayList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(goodDeliverWay)) {
            for (GoodDeliverWay goodDeliverWayStr : goodDeliverWay) {
                goodDeliverWayList.add(goodDeliverWayStr.getDeliverWay());
            }
        }
        GoodDiscount goodDiscount = mallGoodDao.getGoodDiscount(goodId, appId);
        GoodDetailVO goodsDetailVO = new GoodDetailVO();
        BeanUtil.copyProperties(goodBase, goodsDetailVO);
        BeanUtil.copyProperties(goodDetail, goodsDetailVO);
        BeanUtil.copyProperties(goodDeliverWay, goodsDetailVO);
        BeanUtil.copyProperties(goodDiscount, goodsDetailVO);
        goodsDetailVO.setDeliverWay(goodDeliverWayList);
        return goodsDetailVO;
    }

    /**
     * 商品下拉详细信息实现层(商品页调用)
     *
     * @param goodId good id
     * @param appId  app id
     * @return GoodDetailVO
     * @author Hosmos
     * @date 2021-07-08
     */
    @Override
    public List<GoodInfoVO> getGoodInfoByGoodId(Long goodId, String appId) {
        List<GoodInfo> goodInfos = mallGoodDao.getGoodInfoList(goodId, appId);
        List<GoodInfoVO> goodInfoVOs = new ArrayList<>();
        if (!CollectionUtils.isEmpty(goodInfos)) {
            goodInfoVOs = BeanUtil.copyList(goodInfos, GoodInfoVO.class);
        }
        return goodInfoVOs;
    }

    /**
     * 商品标题字符串过长导致文字超出的问题
     *
     * @param goodForIndexVO 商品信息
     * @return GoodForIndexVO
     * @author Hosmos
     * @date 2021-07-05
     */
    private static GoodForIndexVO shortenGoodTitle(GoodForIndexVO goodForIndexVO) {
        String goodName = goodForIndexVO.getGoodName();
        int goodNameLength = 30;
        if (goodName.length() > goodNameLength) {
            goodName = goodName.substring(0, goodNameLength) + "...";
            goodForIndexVO.setGoodName(goodName);
        }
        return goodForIndexVO;
    }
}
