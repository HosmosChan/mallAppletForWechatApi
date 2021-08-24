package com.lettuce.management.service;

import com.lettuce.management.dto.BeanField;
import com.lettuce.management.dto.GenerateInput;

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
 * 代码生成业务层
 *
 * @author Hosmos
 * @date 2021年08月23日
 */
public interface ManagementGenerateService {
    /**
     * 获取数据库表信息
     *
     * @param tableName 数据库表名
     * @return List<BeanField> 数据库表信息
     * @author Hosmos
     * @date 2021-08-23
     */
    List<BeanField> listBeanField(String tableName);

    /**
     * 转成驼峰并大写第一个字母
     *
     * @param string 欲转换的字符串
     * @return String 转换后的字符串
     * @author Hosmos
     * @date 2021-08-23
     */
    String upperFirstChar(String string);

    /**
     * 根据输入内容生成代码
     *
     * @param input 输入的内容
     * @author Hosmos
     * @date 2021-08-23
     */
    void saveCode(GenerateInput input);
}
