package com.lettuce.mall.service.impl;

import com.lettuce.common.enums.Constants;
import com.lettuce.common.utils.BeanUtil;
import com.lettuce.mall.dao.MallCarouselDao;
import com.lettuce.mall.entity.Carousel;
import com.lettuce.mall.service.MallCarouselService;
import com.lettuce.mall.vo.CarouselsForGoodVO;
import com.lettuce.mall.vo.CarouselsForIndexVO;
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
 * @description 轮换图实现层
 * @date 2021年06月25日
 */
@Service("mallCarouselService")
public class MallCarouselServiceImpl implements MallCarouselService {
    @Autowired
    private MallCarouselDao mallcarouselDao;

    /**
     * @param appId
     * @return List<CarouselsForIndexVO>
     * @description 轮播图实现层(首页调用)
     * @author Hosmos
     * @date 2021-06-27
     */
    @Override
    public List<CarouselsForIndexVO> getCarouselsForIndex(String appId) {
        List<CarouselsForIndexVO> carouselsForIndexVOs = new ArrayList<>();
        List<Carousel> carousels = mallcarouselDao.getCarousel(Constants.INDEX_CAROUSEL_INDEX, null, appId);
        if (!CollectionUtils.isEmpty(carousels)) {
            carouselsForIndexVOs = BeanUtil.copyList(carousels, CarouselsForIndexVO.class);
        }
        return carouselsForIndexVOs;
    }

    /**
     * @param goodId
     * @param appId
     * @return List<CarouselsForGoodVO>
     * @description 轮播图实现层(商品页调用)
     * @author Hosmos
     * @date 2021-07-05
     */
    @Override
    public List<CarouselsForGoodVO> getCarouselsForGood(Long goodId, String appId) {
        List<CarouselsForGoodVO> carouselsForGoodVOS = new ArrayList<>();
        List<Carousel> carousels = mallcarouselDao.getCarousel(Constants.INDEX_CAROUSEL_GOOD, goodId, appId);
        if (!CollectionUtils.isEmpty(carousels)) {
            carouselsForGoodVOS = BeanUtil.copyList(carousels, CarouselsForGoodVO.class);
        }
        return carouselsForGoodVOS;
    }
}