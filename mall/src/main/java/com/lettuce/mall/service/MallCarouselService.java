package com.lettuce.mall.service;

import com.lettuce.mall.vo.CarouselsForGoodVO;
import com.lettuce.mall.vo.CarouselsForIndexVO;

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
 * 轮换图业务层
 *
 * @author Hosmos
 * @date 2021年06月25日
 */
public interface MallCarouselService {
    /**
     * 返回轮播图对象(首页调用)
     *
     * @param appId app id
     * @return List<CarouselsForIndexVO>
     * @author Hosmos
     * @date 2021/6/25
     */
    List<CarouselsForIndexVO> getCarouselsForIndex(String appId);

    /**
     * 返回轮播图对象(商品页调用)
     *
     * @param goodId 商品 id
     * @param appId  app id
     * @return List<CarouselsForGoodVO>
     * @author Hosmos
     * @date 2021/7/05
     */
    List<CarouselsForGoodVO> getCarouselsForGood(Long goodId, String appId);
}
