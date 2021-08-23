package com.lettuce.mall.dao;

import com.lettuce.mall.entity.Carousel;

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
 * 轮换图dao层
 *
 * @author Hosmos
 * @date 2021年06月25日
 */
public interface MallCarouselDao {
    /**
     * 获取轮播图信息
     *
     * @param usePlaceId 轮播图使用地方 id
     * @param goodId     商品 id
     * @param appId      app id
     * @return List<Carousel>
     * @author Hosmos
     * @date 2021-08-23
     */
    List<Carousel> getCarousel(Byte usePlaceId, Long goodId, String appId);
}
