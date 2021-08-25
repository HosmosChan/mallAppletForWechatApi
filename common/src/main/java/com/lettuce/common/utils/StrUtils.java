package com.lettuce.common.utils;

import com.google.common.collect.Lists;

import java.text.SimpleDateFormat;
import java.util.Date;
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

    /**
     * 创建随机数（用于订单号、分类id、商品id等）
     *
     * @return createRamdomNo
     * @author Hosmos
     * @date 2021-08-25
     */
    public static synchronized Long createRamdomNo() {
        String today = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String code = createCode(3);
        String out_trade_no = today + code;
        return Long.parseLong(out_trade_no.trim());
    }

    /**
     * 生成n位随机数
     *
     * @param codeLength 位数
     * @return
     * @author Hosmos
     * @date 2021-08-25
     */
    public static String createCode(int codeLength) {
        String code = "";
        for (int i = 0; i < codeLength; i++) {
            code += (int) (Math.random() * 9);
        }
        return code;
    }
}