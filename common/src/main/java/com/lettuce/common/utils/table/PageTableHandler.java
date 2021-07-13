package com.lettuce.common.utils.table;

import java.util.ArrayList;
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
 * @description 分页查询处理器
 * @date 2021年07月08日
 */
public class PageTableHandler {
    private CountHandler countHandler;
    private ListHandler listHandler;
    private OrderHandler orderHandler;

    public PageTableHandler(CountHandler countHandler, ListHandler listHandler) {
        super();
        this.countHandler = countHandler;
        this.listHandler = listHandler;
    }

    public PageTableHandler(CountHandler countHandler, ListHandler listHandler, OrderHandler orderHandler) {
        this(countHandler, listHandler);
        this.orderHandler = orderHandler;
    }

    public PageTableResponse handle(PageTableRequest dtRequest) {
        int count = 0;
        List<?> list = null;
        count = this.countHandler.count(dtRequest);
        if (count > 0) {
            if (orderHandler != null) {
                dtRequest = orderHandler.order(dtRequest);
            }
            list = this.listHandler.list(dtRequest);
        }
        if (list == null) {
            list = new ArrayList<>();
        }
        return new PageTableResponse(count, count, list);
    }

    public interface ListHandler {
        List<?> list(PageTableRequest request);
    }

    public interface CountHandler {
        int count(PageTableRequest request);
    }

    public interface OrderHandler {
        PageTableRequest order(PageTableRequest request);
    }
}