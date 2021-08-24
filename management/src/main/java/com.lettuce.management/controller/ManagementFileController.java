package com.lettuce.management.controller;

import com.lettuce.common.utils.table.*;
import com.lettuce.management.annotation.LogAnnotation;
import com.lettuce.management.dao.ManagementFileDao;
import com.lettuce.management.dto.LayuiFile;
import com.lettuce.management.entity.FileInfo;
import com.lettuce.management.service.ManagementFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;
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
 * 文件controller层
 *
 * @author Hosmos
 * @date 2021年07月13日
 */
@Api(value = "file", tags = "文件接口")
@RestController
@RequestMapping("/file")
public class ManagementFileController {
    @Resource
    private ManagementFileService managementFileService;
    @Resource
    private ManagementFileDao managementFileDao;

    /**
     * 文件上传
     *
     * @param file  文件
     * @param appId app id
     * @return FileInfo
     * @author Hosmos
     * @date 2021-07-13
     */
    @LogAnnotation
    @PostMapping
    @ApiOperation(value = "文件上传")
    public FileInfo uploadFile(MultipartFile file, String appId) throws IOException {
        return managementFileService.save(file, appId);
    }

    /**
     * layui富文本文件自定义上传
     *
     * @param file   文件
     * @param domain 主域
     * @return LayuiFile
     * @throws IOException
     * @author Hosmos
     * @date 2021-07-13
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
        data.setSrc(domain + "/file" + fileInfo.getUrl());
        data.setTitle(file.getOriginalFilename());

        return layuiFile;
    }

    /**
     * 文件查询
     *
     * @param request 查询信息
     * @return PageTableResponse
     * @author Hosmos
     * @date 2021-07-13
     */
    @GetMapping
    @ApiOperation(value = "文件查询")
    @RequiresPermissions("management:file:query")
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

    /**
     * 文件删除
     *
     * @param id    file id
     * @param appId app id
     * @author Hosmos
     * @date 2021-07-13
     */
    @LogAnnotation
    @DeleteMapping("/{id}")
    @ApiOperation(value = "文件删除")
    @RequiresPermissions("management:file:del")
    public void delete(@PathVariable String id, String appId) {
        managementFileService.delete(id, appId);
    }
}
