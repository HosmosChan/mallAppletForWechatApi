package com.lettuce.mall.service.impl;

import com.lettuce.common.utils.BeanUtil;
import com.lettuce.common.utils.PageQueryUtil;
import com.lettuce.common.utils.PageResult;
import com.lettuce.mall.dao.MallSearchDao;
import com.lettuce.mall.entity.GoodBase;
import com.lettuce.mall.service.MallSearchService;
import com.lettuce.mall.vo.SearchVO;
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
public class MallSearchServiceImpl implements MallSearchService {
    @Autowired
    private MallSearchDao mallSearchDao;

    /**
     * @param pageUtil
     * @return PageResult
     * @description 商品搜索信息实现层
     * @author Hosmos
     * @date 2021-07-06
     */
    @Override
    public PageResult searchGoods(PageQueryUtil pageUtil) {
        List<GoodBase> goodBaseList = mallSearchDao.getGoodsListBySearch(pageUtil);
        int totalNo = mallSearchDao.getSearchNumber(pageUtil);
        List<SearchVO> mallSearchVOS = new ArrayList<>();
        if (!CollectionUtils.isEmpty(goodBaseList)) {
            mallSearchVOS = BeanUtil.copyList(goodBaseList, SearchVO.class);
            for (SearchVO mallSearchVO : mallSearchVOS) {
                String goodsName = mallSearchVO.getGoodName();
                // 字符串过长导致文字超出的问题
                if (goodsName.length() > 28) {
                    goodsName = goodsName.substring(0, 28) + "...";
                    mallSearchVO.setGoodName(goodsName);
                }
            }
        }
        PageResult pageResult = new PageResult(mallSearchVOS, totalNo, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }
}
