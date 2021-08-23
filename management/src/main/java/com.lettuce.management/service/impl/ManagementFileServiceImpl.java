package com.lettuce.management.service.impl;

import com.lettuce.common.utils.FileUtil;
import com.lettuce.management.dao.ManagementFileDao;
import com.lettuce.management.entity.FileInfo;
import com.lettuce.management.service.ManagementFileService;
import com.lettuce.management.utils.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
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
 * 文件实现层
 *
 * @author Hosmos
 * @date 2021年07月13日
 */
@Service
public class ManagementFileServiceImpl implements ManagementFileService {
    private static final Logger log = LoggerFactory.getLogger("adminLogger");
    @Value("${files.path}")
    private String filesPath;
    @Autowired
    private ManagementFileDao managementFileDao;

    @Override
    public FileInfo save(MultipartFile file, String appId) throws IOException {
        String fileOrigName = file.getOriginalFilename();
        if (!fileOrigName.contains(".")) {
            throw new IllegalArgumentException("缺少后缀名");
        }

        String md5 = FileUtil.fileMd5(file.getInputStream());
        FileInfo fileInfo = new FileInfo();

        fileOrigName = fileOrigName.substring(fileOrigName.lastIndexOf("."));
        String pathname = FileUtil.getPath() + md5 + fileOrigName;
        String fullPath = filesPath + pathname;
        FileUtil.saveFile(file, fullPath);

        long size = file.getSize();
        String contentType = file.getContentType();
        fileInfo.setId(md5);
        fileInfo.setContentType(contentType);
        fileInfo.setSize(size);
        fileInfo.setPath(fullPath);
        fileInfo.setUrl(pathname);
        fileInfo.setType(contentType.startsWith("image/") ? 1 : 0);
        fileInfo.setCreateUserId(UserUtil.getCurrentUser().getId());
        managementFileDao.save(fileInfo);

        log.debug("上传文件{}", fullPath);

        return fileInfo;
    }

    @Override
    public void delete(String id, String appId) {
        FileInfo fileInfo = managementFileDao.getByFileIdAndAppId(id, appId);
        if (fileInfo != null) {
            String fullPath = fileInfo.getPath();
            FileUtil.deleteFile(fullPath);
            managementFileDao.delete(id, appId);
            log.debug("删除文件：{}", fileInfo.getPath());
        }
    }
}
