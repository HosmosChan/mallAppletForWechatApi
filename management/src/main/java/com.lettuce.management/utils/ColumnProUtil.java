package com.lettuce.management.utils;

import com.lettuce.common.utils.table.PageTableRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ColumnProUtil {
    private static String DEFAULT_ID = "BaseResultMap";

    /**
     * 获取java类属性和表字段对应关系
     *
     * @param dao java类属性
     * @param id  表字段
     * @return Map<String, String>
     * @author Hosmos
     * @date 2021-07-08
     */
    public static Map<String, String> getColumnPro(Class<?> dao, String... id) {
        Map<String, String> map = new HashMap<>();
        SqlSessionFactory sessionFactory = SpringUtil.getBean(SqlSessionFactory.class);
        ResultMap resultMap = sessionFactory.getConfiguration()
                .getResultMap(dao.getName() + "." + (id.length == 0 ? DEFAULT_ID : id[0]));
        if (resultMap != null) {
            List<ResultMapping> list = resultMap.getResultMappings();
            list.parallelStream().forEach(rm -> {
                String column = rm.getColumn();
                String pro = rm.getProperty();
                if (StringUtils.isNoneBlank(column) && StringUtils.isNotBlank(pro)) {
                    map.put(pro, column);
                }
            });
        }
        return map;
    }

    /**
     * 将java类属性替换为表字段
     *
     * @param request
     * @param map
     * @return String
     * @author Hosmos
     * @date 2021-07-08
     */
    public static String pro2Column(PageTableRequest request, Map<String, String> map) {
        String orderBy = (String) request.getParams().get("orderBy");
        if (StringUtils.isNoneBlank(orderBy) && !CollectionUtils.isEmpty(map)) {
            for (String pro : map.keySet()) {
                String val = map.get(pro);
                if (StringUtils.isNoneBlank(val)) {
                    orderBy = orderBy.replace(pro, val);
                }
            }
            request.getParams().put("orderBy", orderBy);
        }
        return orderBy;
    }
}