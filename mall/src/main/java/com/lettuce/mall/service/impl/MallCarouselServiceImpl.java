package com.lettuce.mall.service.impl;

import com.lettuce.common.utils.BeanUtil;
import com.lettuce.mall.dao.MallCarouselDao;
import com.lettuce.mall.entity.Carousel;
import com.lettuce.mall.service.MallCarouselService;
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
@Service
public class MallCarouselServiceImpl implements MallCarouselService {
    @Autowired
    private MallCarouselDao mallcarouselDao;

    @Override
    public List<CarouselsForIndexVO> getCarouselsForIndex() {
        List<CarouselsForIndexVO> carouselsForIndexVOs = new ArrayList<>();
        List<Carousel> carousels = mallcarouselDao.getCarousel();
        if (!CollectionUtils.isEmpty(carousels)) {
            carouselsForIndexVOs = BeanUtil.copyList(carousels, CarouselsForIndexVO.class);
        }
        return carouselsForIndexVOs;
    }
}