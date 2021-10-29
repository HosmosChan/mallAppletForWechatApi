package com.lettuce.management.service;

import com.lettuce.management.dto.GoodBaseDto;
import com.lettuce.management.dto.GoodDto;
import com.lettuce.management.dto.GoodInfoListDto;
import com.lettuce.management.entity.DeliverWay;
import com.lettuce.management.entity.GoodBase;
import com.lettuce.management.entity.GoodInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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
 * 产品展示商品业务层
 *
 * @author Hosmos
 * @date 2021年08月24日
 */
public interface ProductsShownGoodService {
    /**
     * 商品基础信息列表个数，list中页码计数
     *
     * @param params 搜索参数
     * @return int
     * @author Hosmos
     * @date 2021-08-24
     */
    int count(Map<String, Object> params);

    /**
     * 通过搜索参数获取商品基础信息列表
     *
     * @param params 搜索参数
     * @param offset 每页起始序列
     * @param limit  每页显示个数
     * @return List<GoodBase>
     * @author Hosmos
     * @date 2021-08-24
     */
    List<GoodBaseDto> list(Map<String, Object> params, Integer offset, Integer limit);

    /**
     * 保存商品信息
     *
     * @param goodDto 商品实体类
     * @author Hosmos
     * @date 2021-08-27
     */
    void save(GoodDto goodDto);

    /**
     * 获取所有配送方式列表
     *
     * @return List<DeliverWay>
     * @author Hosmos
     * @date 2021-08-28
     */
    List<DeliverWay> listAllDeliverWay();

    /**
     * 根據分類Id獲取商品
     *
     * @param categoryId 分類 Id
     * @return List<GoodBase>
     * @author Hosmos
     * @date 2021-08-30
     */
    List<GoodBase> getGoodByCategoryId(Long categoryId);

    /**
     * 商品詳情信息圖片列表个数，list中页码计数
     *
     * @param params 搜索参数
     * @return int
     * @author Hosmos
     * @date 2021-09-24
     */
    int goodInfoCount(Map<String, Object> params);

    /**
     * 通过搜索参数获取商品詳情信息圖片列表
     *
     * @param params 搜索参数
     * @param offset 每页起始序列
     * @param limit  每页显示个数
     * @return List<GoodInfoListDto>
     * @author Hosmos
     * @date 2021-09-24
     */
    List<GoodInfoListDto> goodInfoList(Map<String, Object> params, Integer offset, Integer limit);

    /**
     * 添加商品詳情信息圖片
     *
     * @param file    文件
     * @param request 请求信息
     * @return GoodInfo
     * @author Hosmos
     * @date 2021-09-26
     */
    GoodInfo addGoodInfo(MultipartFile file, HttpServletRequest request) throws IOException;

    /**
     * 通过相關參數获取商品基础信息
     *
     * @param goodId   商品 id
     * @param goodName 商品名
     * @param appId    app id
     * @return GoodBaseDto
     * @author Hosmos
     * @date 2021-08-25
     */
    GoodBaseDto getGoodBaseByParam(Long goodId, String goodName, String appId);

    /**
     * 删除商品詳情信息圖片
     *
     * @param goodId   商品 id
     * @param infoType 類型
     * @author Hosmos
     * @date 2021-10-18
     */
    void deleteGoodInfo(Long goodId, Byte infoType);

    /**
     * 通过相關參數获取商品信息
     *
     * @param goodId 商品 id
     * @return GoodDto
     * @author Hosmos
     * @date 2021-10-08
     */
    GoodDto getGoodByParam(Long goodId);

    /**
     * 根据商品id获取配送方式
     *
     * @param goodId 商品id
     * @return List<DeliverWay>
     * @author Hosmos
     * @date 2021-10-10
     */
    List<DeliverWay> getDeliverWayByGoodId(Long goodId);

    /**
     * 上传商品封面
     *
     * @param file    文件
     * @param request 请求参数
     * @return LayuiFile
     * @author Hosmos
     * @date 2021-10-11
     */
    GoodInfo uploadCover(MultipartFile file, HttpServletRequest request) throws IOException;
}
