package com.lettuce.management.service.impl;

import com.lettuce.common.utils.StrUtils;
import com.lettuce.management.config.YmlConfig;
import com.lettuce.management.dao.ManagementAppletDao;
import com.lettuce.management.dao.ProductsShownGoodDao;
import com.lettuce.management.dto.GoodBaseDto;
import com.lettuce.management.dto.GoodDto;
import com.lettuce.management.entity.GoodBase;
import com.lettuce.management.service.ProductsShownGoodService;
import com.lettuce.management.utils.UserUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
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
    private ManagementAppletDao managementAppletDao;
    @Autowired
    private YmlConfig ymlConfig;

    @Override
    public int count(Map<String, Object> params) {
        return productsShownGoodDao.count(params);
    }

    @Override
    public List<GoodBaseDto> list(Map<String, Object> params, Integer offset, Integer limit) {
        String appId = managementAppletDao.getAppIdByUserId(UserUtil.getCurrentUser().getId());
        params.put("appId", appId);
        String orderBy = (String) params.get("orderBy");
        String orderByGoodPriceDesc = " order by goodPrice desc";
        String orderByGoodPriceAsc = " order by goodPrice asc";
        if(orderByGoodPriceDesc.equals(orderBy)) {
            orderBy = " order by goodMinPrice desc";
            params.put("orderBy", orderBy);
        } else if (orderByGoodPriceAsc.equals(orderBy)) {
            orderBy = " order by goodMinPrice asc";
            params.put("orderBy", orderBy);
        }
        List<GoodBaseDto> goodBaseDtoList = productsShownGoodDao.list(params, offset, limit);
        for (GoodBaseDto goodBaseDto : goodBaseDtoList) {
            shortenGoodTitle(goodBaseDto);
            String goodPrice = goodBaseDto.getGoodMinPrice().setScale(2, BigDecimal.ROUND_HALF_UP).toString() + "-" + goodBaseDto.getGoodMaxPrice().setScale(2, BigDecimal.ROUND_HALF_UP).toString();
            goodBaseDto.setGoodPrice(goodPrice);
        }
        return goodBaseDtoList;
    }

    @Override
    public void save(GoodDto goodDto) {
        GoodBase d = productsShownGoodDao.getGoodByName(goodDto.getGoodName(), goodDto.getAppId());
        if (d != null) {
            throw new IllegalArgumentException("商品已存在");
        }
        goodDto.setGoodId(StrUtils.createRamdomNo());
        goodDto.setCreateUserId(UserUtil.getCurrentUser().getId());
        String pro = "pro";
        if (pro.equals(ymlConfig.getStatus())) {
            goodDto.setGoodUrl(ymlConfig.getDns() + "://" + ymlConfig.getProductsShownType() + "." + ymlConfig.getDomain() + "/good/detail/" + goodDto.getGoodId());
        } else {
            goodDto.setGoodUrl(ymlConfig.getDomain() + ":" + ymlConfig.getProductsShownPort() + "/good/detail/" + goodDto.getGoodId());
        }
        /*String fileType = ymlConfig.getFile().getCarousel();
        if (goodDto.getGoodCoverImg() != null) {
            if (pro.equals(ymlConfig.getStatus())) {
                goodDto.setGoodCoverImg(ymlConfig.getDns() + "://" + ymlConfig.getProductsShownType() + "." + ymlConfig.getDomain() + ymlConfig.getFile().getFilePath() + ymlConfig.getProductsShownType() + fileType + "/" + goodDto.getGoodId());
            } else {
                goodDto.setGoodCoverImg(ymlConfig.getDomain() + ":" + ymlConfig.getProductsShownPort() + "/good/detail/" + goodDto.getGoodId());
            }
        }*/
        productsShownGoodDao.saveBase(goodDto);
        productsShownGoodDao.saveDetail(goodDto);
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
