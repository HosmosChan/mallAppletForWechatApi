package com.lettuce.management.controller;

import com.lettuce.common.utils.table.*;
import com.lettuce.management.annotation.LogAnnotation;
import com.lettuce.management.dao.ManagementFileDao;
import com.lettuce.management.dto.LayuiFile;
import com.lettuce.management.entity.FileInfo;
import com.lettuce.management.service.ManagementFileService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
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
 * @description 文件controller层
 * @date 2021年07月13日
 */
public class ManagementFileController {
    @Resource
    private ManagementFileService managementFileService;
    @Resource
    private ManagementFileDao managementFileDao;

    @LogAnnotation
    @PostMapping
    @ApiOperation(value = "文件上传")
    public FileInfo uploadFile(MultipartFile file, String appId) throws IOException {
        return managementFileService.save(file, appId);
    }

    /**
     * layui富文本文件自定义上传
     *
     * @param file
     * @param domain
     * @return
     * @throws IOException
     */
    @LogAnnotation
    @PostMapping("/layui")
    @ApiOperation(value = "layui富文本文件自定义上传")
    public LayuiFile uploadLayuiFile(MultipartFile file, String domain, String appId) throws IOException {
        FileInfo fileInfo = managementFileService.save(file, appId);

        LayuiFile layuiFile = new LayuiFile();
        layuiFile.setCode(0);
        LayuiFile.LayuiFileData data = new LayuiFile.LayuiFileData();
        layuiFile.setData(data);
        data.setSrc(domain + "/files" + fileInfo.getUrl());
        data.setTitle(file.getOriginalFilename());

        return layuiFile;
    }

    @GetMapping
    @ApiOperation(value = "文件查询")
    @RequiresPermissions("sys:file:query")
    public PageTableResponse listFiles(PageTableRequest request) {
        return new PageTableHandler(new PageTableHandler.CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return managementFileDao.count(request.getParams());
            }
        }, new PageTableHandler.ListHandler() {

            @Override
            public List<FileInfo> list(PageTableRequest request) {
                List<FileInfo> list = managementFileDao.list(request.getParams(), request.getOffset(), request.getLimit());
                return list;
            }
        }).handle(request);
    }

    @LogAnnotation
    @DeleteMapping("/{fileId}")
    @ApiOperation(value = "文件删除")
    @RequiresPermissions("sys:file:del")
    public void delete(@PathVariable String fileId, String appId) {
        managementFileService.delete(fileId, appId);
    }
}
