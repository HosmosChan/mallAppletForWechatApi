package com.lettuce.management.service;

import com.lettuce.management.entity.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
 * 文件业务层
 *
 * @author Hosmos
 * @date 2021年07月13日
 */
public interface ManagementFileService {
    /**
     * 保存文件
     *
     * @param file  文件
     * @param appId 应用app id
     * @return FileInfo
     * @author Hosmos
     * @date 2021-07-14
     */
    FileInfo save(MultipartFile file, String appId) throws IOException;

    /**
     * 删除文件
     *
     * @param tid   file id
     * @param appId 应用app id
     * @author Hosmos
     * @date 2021-07-14
     */
    void delete(String tid, String appId);
}
