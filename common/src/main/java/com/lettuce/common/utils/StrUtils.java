package com.lettuce.common.utils;

import com.google.common.collect.Lists;

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
 * 字符串转换工具类
 *
 * @author Hosmos
 * @date 2021年06月22日
 */
public class StrUtils {
    /**
     * 字符串转为驼峰
     *
     * @param str 字符串
     * @return String
     * @author Hosmos
     * @date 2021/6/22
     */
    public static String str2hump(String str) {
        StringBuilder builder = new StringBuilder();
        if (str != null && str.length() > 0) {
            if (str.contains("_")) {
                String[] chars = str.split("_");
                int size = chars.length;
                if (size > 0) {
                    List<String> list = Lists.newArrayList();
                    for (String s : chars) {
                        if (s != null && s.trim().length() > 0) {
                            list.add(s);
                        }
                    }
                    size = list.size();
                    if (size > 0) {
                        builder.append(list.get(0));
                        for (int i = 1; i < size; i++) {
                            String s = list.get(i);
                            builder.append(s.substring(0, 1).toUpperCase());
                            if (s.length() > 1) {
                                builder.append(s.substring(1));
                            }
                        }
                    }
                }
            } else {
                builder.append(str);
            }
        }
        return builder.toString();
    }
}