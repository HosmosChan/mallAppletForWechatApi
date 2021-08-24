package com.lettuce.productsShown.service;

import com.lettuce.common.utils.PageQueryUtil;
import com.lettuce.common.utils.PageResult;

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
 * 搜索业务层
 * @author Hosmos
 * @date 2021年07月06日
 */
public interface ProductsShownSearchService {
    /**
     * @param pageUtil
     * @return PageResult
     * @description 返回商品搜索信息对象
     * @author Hosmos
     * @date 2021-07-06
     */
    PageResult searchGoods(PageQueryUtil pageUtil);
}
