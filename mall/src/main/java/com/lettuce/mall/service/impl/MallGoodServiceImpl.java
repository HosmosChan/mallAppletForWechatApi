package com.lettuce.mall.service.impl;

import com.lettuce.common.utils.BeanUtil;
import com.lettuce.mall.dao.MallGoodDao;
import com.lettuce.mall.entity.GoodBase;
import com.lettuce.mall.service.MallGoodService;
import com.lettuce.mall.vo.GoodForIndexVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
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
     * @param null
     * @return List<GoodForIndexVO>
     * @description 返回特价商品对象(首页调用)
     * @author Hosmos
     * @date 2021-06-27
     */
    @Override
    public List<GoodForIndexVO> getDiscountGoodsForIndex(int number) {
        List<GoodForIndexVO> goodForIndexVOs = new ArrayList<>();
        List<GoodBase> goodBaseInfos = mallGoodDao.getDiscountGoodBaseInfo(number);
        if (!CollectionUtils.isEmpty(goodBaseInfos)) {
            goodForIndexVOs = BeanUtil.copyList(goodBaseInfos, GoodForIndexVO.class);
            for (GoodForIndexVO goodForIndexVO : goodForIndexVOs) {
                String goodName = goodForIndexVO.getGoodName();
                // 字符串过长导致文字超出的问题
                if (goodName.length() > 30) {
                    goodName = goodName.substring(0, 30) + "...";
                    goodForIndexVO.setGoodName(goodName);
                }
                if (goodForIndexVO.getDiscountPrice().compareTo(BigDecimal.ZERO) != 0) {
                    goodForIndexVO.setDiscountPrice(goodForIndexVO.getGoodPrice().subtract(goodForIndexVO.getDiscountPrice()));
                }
            }
        }
        return goodForIndexVOs;
    }
}
