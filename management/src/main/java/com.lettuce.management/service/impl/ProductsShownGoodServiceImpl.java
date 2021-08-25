package com.lettuce.management.service.impl;

import com.lettuce.management.dao.ManagementUserDao;
import com.lettuce.management.dao.ProductsShownCategoryDao;
import com.lettuce.management.dao.ProductsShownGoodDao;
import com.lettuce.management.dto.GoodBaseDto;
import com.lettuce.management.service.ProductsShownGoodService;
import com.lettuce.management.utils.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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
 * 产品展示商品实现层
 *
 * @author Hosmos
 * @date 2021年08月24日
 */
@Service
public class ProductsShownGoodServiceImpl implements ProductsShownGoodService {
    private static final Logger log = LoggerFactory.getLogger("adminLogger");
    @Autowired
    private ProductsShownGoodDao productsShownGoodDao;
    @Autowired
    private ProductsShownCategoryDao productsShownCategoryDao;
    @Autowired
    private ManagementUserDao managementUserDao;

    @Override
    public int count(Map<String, Object> params) {
        return productsShownGoodDao.count(params);
    }

    @Override
    public List<GoodBaseDto> list(Map<String, Object> params, Integer offset, Integer limit) {
        String appId = managementUserDao.getAppIdByUserId(UserUtil.getCurrentUser().getId());
        params.put("appId", appId);
        List<GoodBaseDto> goodBaseDtoList = productsShownGoodDao.list(params, offset, limit);
        for (GoodBaseDto goodBaseDto : goodBaseDtoList) {
            shortenGoodTitle(goodBaseDto);
            String goodPrice = goodBaseDto.getGoodMinPrice().setScale(2, BigDecimal.ROUND_HALF_UP).toString() + "-" + goodBaseDto.getGoodMaxPrice().setScale(2, BigDecimal.ROUND_HALF_UP).toString();
            goodBaseDto.setGoodPrice(goodPrice);
        }
        return goodBaseDtoList;
    }

    /**
     * 商品标题字符串过长导致文字超出的问题
     *
     * @param goodBaseDto 商品信息
     * @return GoodBaseDto
     * @author Hosmos
     * @date 2021-07-05
     */
    private static GoodBaseDto shortenGoodTitle(GoodBaseDto goodBaseDto) {
        String goodName = goodBaseDto.getGoodName();
        int goodNameLength = 10;
        if (goodName.length() > goodNameLength) {
            goodName = goodName.substring(0, goodNameLength) + "...";
            goodBaseDto.setGoodName(goodName);
        }
        return goodBaseDto;
    }
}
