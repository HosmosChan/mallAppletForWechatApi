package com.lettuce.management.service.impl;

import com.lettuce.common.utils.FileUtil;
import com.lettuce.common.utils.StrUtils;
import com.lettuce.management.config.YmlConfig;
import com.lettuce.management.constants.ManagementUserConstants;
import com.lettuce.management.dao.ManagementAppletDao;
import com.lettuce.management.dao.ProductsShownGoodDao;
import com.lettuce.management.dto.GoodBaseDto;
import com.lettuce.management.dto.GoodDto;
import com.lettuce.management.dto.GoodInfoListDto;
import com.lettuce.management.entity.*;
import com.lettuce.management.service.ProductsShownGoodService;
import com.lettuce.management.utils.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
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
    @Value("${files.path}")
    private String filesPath;
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
        if (orderByGoodPriceDesc.equals(orderBy)) {
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
    @Transactional(rollbackFor = Exception.class)
    public void save(GoodDto goodDto) {
        GoodBase d = productsShownGoodDao.getGoodBaseByParam(null, goodDto.getGoodName(), goodDto.getAppId());
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
        if (goodDto.getIsSpecialPrice() == null) {
            goodDto.setIsSpecialPrice((byte) 0);
        }
        if (goodDto.getIsDiscount() == null) {
            goodDto.setIsDiscount((byte) 0);
        }
        productsShownGoodDao.saveBase(goodDto);
        productsShownGoodDao.saveDetail(goodDto);
        if (goodDto.getIsDiscount() != null) {
            if (goodDto.getIsDiscount() == 1) {
                productsShownGoodDao.saveDiscount(goodDto);
            }
        }
        List<Long> deliverWay = goodDto.getDeliverWay();
        List<GoodDeliverWay> goodDeliverWayList = new ArrayList<>();
        for (int i = 1; i < deliverWay.size(); i++) {
            GoodDeliverWay goodDeliverWay = new GoodDeliverWay();
            goodDeliverWay.setAppId(goodDto.getAppId());
            goodDeliverWay.setGoodId(goodDto.getGoodId());
            goodDeliverWay.setCreateUserId(goodDto.getCreateUserId());
            goodDeliverWay.setDeliverWay(deliverWay.get(i));
            goodDeliverWayList.add(goodDeliverWay);
        }
        productsShownGoodDao.saveDeliverWay(goodDeliverWayList);
    }

    @Override
    public List<DeliverWay> listAllDeliverWay() {
        return productsShownGoodDao.listAllDeliverWay();
    }

    @Override
    public List<GoodBase> getGoodByCategoryId(Long categoryId) {
        String appId = managementAppletDao.getAppIdByUserId(UserUtil.getCurrentUser().getId());
        return productsShownGoodDao.getGoodByCategoryId(appId, categoryId);
    }

    @Override
    public int goodInfoCount(Map<String, Object> params) {
        String appId = managementAppletDao.getAppIdByUserId(UserUtil.getCurrentUser().getId());
        params.put("appId", appId);
        return productsShownGoodDao.goodInfoCount(params);
    }

    @Override
    public List<GoodInfoListDto> goodInfoList(Map<String, Object> params, Integer offset, Integer limit) {
        String appId = managementAppletDao.getAppIdByUserId(UserUtil.getCurrentUser().getId());
        params.put("appId", appId);
        List<Long> goodInfoIdList = productsShownGoodDao.goodInfoIdList(params, offset, limit);
        List<GoodInfoListDto> goodInfoList = new ArrayList<>();
        List<GoodBaseDto> goodBaseDtoList = productsShownGoodDao.getGoodBaseByGoodId(goodInfoIdList);
        List<GoodInfo> allGoodInfoList = productsShownGoodDao.allGoodInfoList();
        for (Long goodId : goodInfoIdList
        ) {
            GoodInfoListDto goodInfoListDto = new GoodInfoListDto();
            goodInfoListDto.setGoodId(goodId);
            for (GoodBaseDto goodBaseDto : goodBaseDtoList
            ) {
                if (goodBaseDto.getGoodId().equals(goodId)) {
                    goodInfoListDto.setAppId(goodBaseDto.getAppId());
                    goodInfoListDto.setGoodName(goodBaseDto.getGoodName());
                    goodInfoListDto.setCategoryId(goodBaseDto.getCategoryId());
                }
            }
            List<GoodInfo> goodInfos = new ArrayList<>();
            for (GoodInfo goodInfo : allGoodInfoList
            ) {
                if (goodInfo.getGoodId().equals(goodId)) {
                    goodInfos.add(goodInfo);
                }
            }
            goodInfoListDto.setGoodInfoList(goodInfos);
            goodInfoList.add(goodInfoListDto);
        }
        return goodInfoList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public GoodInfo addGoodInfo(MultipartFile file, HttpServletRequest request) throws IOException {
        String appId = managementAppletDao.getAppIdByUserId(UserUtil.getCurrentUser().getId());
        Long goodId = Long.parseLong(request.getParameter("goodId"));
        String fileOriginalName = file.getOriginalFilename();
        Long id = StrUtils.createRamdomNo();
        String md5 = FileUtil.fileMd5(file.getInputStream());
        GoodInfo fileInfo = new GoodInfo();
        fileOriginalName = fileOriginalName.substring(fileOriginalName.lastIndexOf("."));
        String pathname = "/" + goodId + "/" + md5 + fileOriginalName;
        String fullPath = filesPath + ymlConfig.getFile().getGoodInfo() + pathname;
        List<GoodInfo> d = productsShownGoodDao.getGoodInfoById(goodId, appId, ManagementUserConstants.GOOD_INFO);
        for (GoodInfo goodInfo : d
        ) {
            if (goodInfo.getPictureId().equals(md5)) {
                return null;
            }
        }
        FileUtil.saveFile(file, fullPath);
        long size = file.getSize();
        String contentType = file.getContentType();
        fileInfo.setId(id);
        fileInfo.setPictureId(md5);
        fileInfo.setAppId(appId);
        fileInfo.setGoodId(goodId);
        fileInfo.setInfoType(ManagementUserConstants.GOOD_INFO);
        fileInfo.setContentType(contentType);
        fileInfo.setSize(size);
        fileInfo.setPath(fullPath);
        fileInfo.setUrl(pathname);
        fileInfo.setType(contentType.startsWith("image/") ? 1 : 0);
        fileInfo.setCreateUserId(UserUtil.getCurrentUser().getId());
        productsShownGoodDao.addGoodInfo(fileInfo);
        return fileInfo;
    }

    @Override
    public GoodBaseDto getGoodBaseByParam(Long goodId, String goodName, String appId) {
        return productsShownGoodDao.getGoodBaseByParam(goodId, goodName, appId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteGoodInfo(Long goodId, Byte infoType) {
        String appId = managementAppletDao.getAppIdByUserId(UserUtil.getCurrentUser().getId());
        List<GoodInfo> d = productsShownGoodDao.getGoodInfoById(goodId, appId, infoType);
        productsShownGoodDao.deleteGoodInfo(goodId, appId, infoType);
        for (GoodInfo goodInfo : d
        ) {
            FileUtil.deleteFile(goodInfo.getPath());
        }
    }

    @Override
    public GoodDto getGoodByParam(Long goodId) {
        String appId = managementAppletDao.getAppIdByUserId(UserUtil.getCurrentUser().getId());
        GoodDto result = productsShownGoodDao.getGoodByParam(goodId, appId);
        return result;
    }

    @Override
    public List<DeliverWay> getDeliverWayByGoodId(Long goodId) {
        String appId = managementAppletDao.getAppIdByUserId(UserUtil.getCurrentUser().getId());
        return productsShownGoodDao.getDeliverWayByGoodId(goodId, appId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public GoodInfo uploadCover(MultipartFile file, HttpServletRequest request) throws IOException {
        String appId = managementAppletDao.getAppIdByUserId(UserUtil.getCurrentUser().getId());
        Long goodId = Long.parseLong(request.getParameter("goodId"));
        List<GoodInfo> data = productsShownGoodDao.getGoodInfoById(goodId, appId, ManagementUserConstants.GOOD_COVER);
        GoodInfo fileInfo = new GoodInfo();
        Long id = StrUtils.createRamdomNo();
        fileInfo.setId(id);
        String md5 = FileUtil.fileMd5(file.getInputStream());
        fileInfo.setPictureId(md5);
        fileInfo.setAppId(appId);
        fileInfo.setGoodId(goodId);
        fileInfo.setInfoType(ManagementUserConstants.GOOD_COVER);
        String contentType = file.getContentType();
        fileInfo.setContentType(contentType);
        long size = file.getSize();
        fileInfo.setSize(size);
        String fileOriginalName = file.getOriginalFilename();
        fileOriginalName = fileOriginalName.substring(fileOriginalName.lastIndexOf("."));
        String pathname = "/" + goodId + "/" + md5 + fileOriginalName;
        String fullPath = filesPath + ymlConfig.getFile().getGoodCover() + pathname;
        fileInfo.setPath(fullPath);
        fileInfo.setUrl(pathname);
        fileInfo.setType(contentType.startsWith("image/") ? 1 : 0);
        Long userId = UserUtil.getCurrentUser().getId();
        fileInfo.setCreateUserId(userId);
        fileInfo.setGmtUserId(userId);
        productsShownGoodDao.updateGoodCoverImg(goodId, appId, fullPath, UserUtil.getCurrentUser().getId());
        if (data != null && data.size() > 0) {
            GoodInfo fileData = data.get(0);
            productsShownGoodDao.updateGoodCover(fileInfo);
            FileUtil.deleteFile(fileData.getPath());
        } else {
            productsShownGoodDao.addGoodInfo(fileInfo);
        }
        FileUtil.saveFile(file, fullPath);
        return fileInfo;
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
