package com.lettuce.mall.service.impl;

import com.lettuce.common.utils.BeanUtil;
import com.lettuce.mall.dao.MallGoodDao;
import com.lettuce.mall.entity.GoodBase;
import com.lettuce.mall.entity.GoodDetail;
import com.lettuce.mall.entity.GoodDeliverWay;
import com.lettuce.mall.entity.GoodDiscount;
import com.lettuce.mall.service.MallGoodService;
import com.lettuce.mall.vo.GoodDetailVO;
import com.lettuce.mall.vo.GoodForIndexVO;
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
@Service("mallGoodService")
public class MallGoodServiceImpl implements MallGoodService {
    @Autowired
    private MallGoodDao mallGoodDao;

    /**
     * @param number
     * @return List<GoodForIndexVO>
     * @description 特价商品实现层(首页调用)
     * @author Hosmos
     * @date 2021-06-27
     */
    @Override
    public List<GoodForIndexVO> getSpecialPriceGoodsForIndex(int number) {
        List<GoodForIndexVO> goodForIndexVOs = new ArrayList<>();
        List<GoodBase> goodBaseInfos = mallGoodDao.getSpecialPriceGoodBaseInfo(number);
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
     * @return List<GoodForIndexVO>
     * @description 折扣商品实现层(首页调用)
     * @author Hosmos
     * @date 2021-07-06
     */
    @Override
    public List<GoodForIndexVO> getDiscountGoodsForIndex(int number) {
        List<GoodForIndexVO> goodForIndexVOs = new ArrayList<>();
        List<GoodBase> goodBaseInfos = mallGoodDao.getDiscountGoodBaseInfo(number);
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
     * @return List<GoodForIndexVO>
     * @description 热卖商品实现层(首页调用)
     * @author Hosmos
     * @date 2021-07-05
     */
    @Override
    public List<GoodForIndexVO> getHotGoodsForIndex(int number) {
        List<GoodForIndexVO> goodForIndexVOs = new ArrayList<>();
        List<GoodBase> goodBaseInfos = mallGoodDao.getHotGoodBaseInfo(number);
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
     * @return List<GoodForIndexVO>
     * @description 最新商品实现层(首页调用)
     * @author Hosmos
     * @date 2021-07-05
     */
    @Override
    public List<GoodForIndexVO> getNewGoodsForIndex(int number) {
        List<GoodForIndexVO> goodForIndexVOs = new ArrayList<>();
        List<GoodBase> goodBaseInfos = mallGoodDao.getNewGoodBaseInfo(number);
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
     * @return GoodDetailVO
     * @description 商品信息实现层(商品页调用)
     * @author Hosmos
     * @date 2021-07-05
     */
    @Override
    public GoodDetailVO getGoodDetailByGoodId(Long goodId) {
        GoodBase goodBase = mallGoodDao.getGoodBaseInfo(goodId);
        if (goodBase == null) {
            return null;
        }
        GoodDetail goodDetail = mallGoodDao.getGoodDetailInfo(goodId);
        List<GoodDeliverWay> goodDeliverWay = mallGoodDao.getGoodDeliverWay(goodId);
        List<String> goodDeliverWayList = null;
        if (!CollectionUtils.isEmpty(goodDeliverWay)) {
            for (GoodDeliverWay goodDeliverWayStr : goodDeliverWay) {
                goodDeliverWayList.add(goodDeliverWayStr.getDeliverWay());
            }
        }
        GoodDiscount goodDiscount = mallGoodDao.getGoodDiscount(goodId);
        GoodDetailVO goodsDetailVO = new GoodDetailVO();
        BeanUtil.copyProperties(goodBase, goodsDetailVO);
        BeanUtil.copyProperties(goodDetail, goodsDetailVO);
        BeanUtil.copyProperties(goodDeliverWay, goodsDetailVO);
        goodsDetailVO.setDeliverWay(goodDeliverWayList);
        return goodsDetailVO;
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
