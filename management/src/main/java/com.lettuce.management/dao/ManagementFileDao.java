package com.lettuce.management.dao;

import com.lettuce.management.entity.FileInfo;

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
 * 文件dao层
 *
 * @author Hosmos
 * @date 2021年07月13日
 */
public interface ManagementFileDao {
    /**
     * 根据文件id和app id获取文件信息
     *
     * @param id    文件 id
     * @param appId app id
     * @return FileInfo
     * @author Hosmos
     * @date 2021-08-24
     */
    FileInfo getByFileIdAndAppId(String id, String appId);

    /**
     * 保存文件
     *
     * @param fileInfo 文件信息
     * @author Hosmos
     * @date 2021-08-24
     */
    void save(FileInfo fileInfo);

    /**
     * 根据搜索参数获取文件个数
     *
     * @param params 搜索参数
     * @return int
     * @author Hosmos
     * @date 2021-08-24
     */
    int count(Map<String, Object> params);

    /**
     * 根据搜索参数获取文件列表
     *
     * @param params 搜索参数
     * @param offset 顺序
     * @param limit  页码
     * @return List<FileInfo>
     * @author Hosmos
     * @date 2021-08-24
     */
    List<FileInfo> list(Map<String, Object> params, Integer offset, Integer limit);

    /**
     * 删除文件
     *
     * @param id    文件 id
     * @param appId app id
     * @author Hosmos
     * @date 2021-08-24
     */
    void delete(String id, String appId);
}
