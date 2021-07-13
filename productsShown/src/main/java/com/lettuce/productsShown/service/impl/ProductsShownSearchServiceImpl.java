package com.lettuce.productsShown.service.impl;

import com.lettuce.common.utils.BeanUtil;
import com.lettuce.common.utils.PageQueryUtil;
import com.lettuce.common.utils.PageResult;
import com.lettuce.productsShown.dao.ProductsShownSearchDao;
import com.lettuce.productsShown.entity.GoodBase;
import com.lettuce.productsShown.service.ProductsShownSearchService;
import com.lettuce.productsShown.vo.SearchVO;
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
 * @description
 * @date 2021年07月06日
 */
@Service
public class ProductsShownSearchServiceImpl implements ProductsShownSearchService {
    @Autowired
    private ProductsShownSearchDao productsShownSearchDao;

    /**
     * @param pageUtil
     * @return PageResult
     * @description 商品搜索信息实现层
     * @author Hosmos
     * @date 2021-07-06
     */
    @Override
    public PageResult searchGoods(PageQueryUtil pageUtil) {
        List<GoodBase> goodBaseList = productsShownSearchDao.getGoodsListBySearch(pageUtil);
        int totalNo = productsShownSearchDao.getSearchNumber(pageUtil);
        List<SearchVO> productsShownSearchVOS = new ArrayList<>();
        if (!CollectionUtils.isEmpty(goodBaseList)) {
            productsShownSearchVOS = BeanUtil.copyList(goodBaseList, SearchVO.class);
            for (SearchVO productsShownSearchVO : productsShownSearchVOS) {
                String goodsName = productsShownSearchVO.getGoodName();
                // 字符串过长导致文字超出的问题
                if (goodsName.length() > 28) {
                    goodsName = goodsName.substring(0, 28) + "...";
                    productsShownSearchVO.setGoodName(goodsName);
                }
            }
        }
        PageResult pageResult = new PageResult(productsShownSearchVOS, totalNo, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }
}
