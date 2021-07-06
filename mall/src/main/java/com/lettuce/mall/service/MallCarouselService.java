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
 *
 * @author Hosmos
 * @description 轮换图业务层
 * @date 2021年06月25日
 */
public interface MallCarouselService {
    /**
     * @param null
     * @return List<CarouselsForIndexVO>
     * @description 返回轮播图对象(首页调用)
     * @author Hosmos
     * @date 2021/6/25
     */
    List<CarouselsForIndexVO> getCarouselsForIndex();

    /**
     * @param null
     * @return List<CarouselsForGoodVO>
     * @description 返回轮播图对象(商品页调用)
     * @author Hosmos
     * @date 2021/7/05
     */
    List<CarouselsForGoodVO> getCarouselsForGood(Long goodId);
}
