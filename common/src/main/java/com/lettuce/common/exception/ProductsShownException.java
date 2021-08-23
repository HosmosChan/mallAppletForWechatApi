package com.lettuce.common.exception;

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
 * 异常工具类
 *
 * @author Hosmos
 * @date 2021年07月05日
 */
public class ProductsShownException extends RuntimeException {
    public ProductsShownException() {
    }

    public ProductsShownException(String message) {
        super(message);
    }

    /**
     * 丢出异常
     *
     * @param message 异常信息
     */
    public static void fail(String message) {
        throw new ProductsShownException(message);
    }
}
