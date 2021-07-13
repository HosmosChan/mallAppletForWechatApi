package com.lettuce.management.utils;

import com.lettuce.common.utils.StrUtils;
import com.lettuce.management.dto.GenerateInput;
import com.lettuce.common.utils.FileUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class TemplateUtil {
    private static final Logger log = LoggerFactory.getLogger("adminLogger");

    public static String getTemplete(String fileName) {
        return FileUtil.getText(TemplateUtil.class.getClassLoader().getResourceAsStream("generate/" + fileName));
    }

    public static void saveJava(GenerateInput input) {
        String path = input.getPath();
        String beanPackageName = input.getBeanPackageName();
        String beanName = input.getBeanName();
        List<String> beanFieldName = input.getBeanFieldName();
        List<String> beanFieldType = input.getBeanFieldType();
        List<String> beanFieldValue = input.getBeanFieldValue();
        String text = getTemplete("java.txt");
        text = text.replace("{beanPackageName}", beanPackageName).replace("{beanName}", beanName);
        String imports = "";
        if (beanFieldType.contains(BigDecimal.class.getSimpleName())) {
            imports += "import " + BigDecimal.class.getName() + ";\n";
        }
        if (beanFieldType.contains(Date.class.getSimpleName())) {
            imports += "import " + Date.class.getName() + ";";
        }
        text = text.replace("{import}", imports);
        String filelds = getFields(beanFieldName, beanFieldType, beanFieldValue);
        text = text.replace("{filelds}", filelds);
        text = text.replace("{getset}", getset(beanFieldName, beanFieldType));
        FileUtil.saveTextFile(text, path + File.separator + getPackagePath(beanPackageName) + beanName + ".java");
        log.debug("生成java model：{}模板", beanName);
    }

    private static String getFields(List<String> beanFieldName, List<String> beanFieldType,
                                    List<String> beanFieldValue) {
        StringBuilder builder = new StringBuilder();
        int size = beanFieldName.size();
        for (int i = 0; i < size; i++) {
            String name = beanFieldName.get(i);
            if ("id".equals(name) || "createTime".equals(name) || "gmtTime".equals(name)) {
                continue;
            }
            String type = beanFieldType.get(i);
            builder.append("\tprivate ").append(type).append(" ").append(name);
            // 默认值
//			String value = beanFieldValue.get(i);
//			if (!StringUtils.isEmpty(value)) {
//				buffer.append(" = ");
//				if (type.equals(String.class.getSimpleName())) {
//					value = "\"" + value + "\"";
//				} else if (type.equals(Double.class.getSimpleName())) {
//					value = value + "D";
//				} else if (type.equals(Float.class.getSimpleName())) {
//					value = value + "F";
//				} else if (type.equals(BigDecimal.class.getSimpleName())) {
//					value = "new BigDecimal(" + value + ")";
//				}
//
//				buffer.append(value);
//			}
            builder.append(";\n");
        }

        return builder.toString();
    }

    private static String getset(List<String> beanFieldName, List<String> beanFieldType) {
        StringBuilder builder = new StringBuilder();
        int size = beanFieldName.size();
        for (int i = 0; i < size; i++) {
            String name = beanFieldName.get(i);
            if ("id".equals(name) || "createTime".equals(name) || "gmtTime".equals(name)) {
                continue;
            }
            String type = beanFieldType.get(i);
            builder.append("\tpublic ");
            builder.append(type);
            builder.append(" get");
            builder.append(StringUtils.substring(name, 0, 1).toUpperCase());
            builder.append(name.substring(1, name.length()));
            builder.append("() {\n\t\treturn ");
            builder.append(name);
            builder.append(";\n\t}\n\tpublic void set");
            builder.append(StringUtils.substring(name, 0, 1).toUpperCase());
            builder.append(name.substring(1, name.length()));
            builder.append("(");
            builder.append(type);
            builder.append(" ");
            builder.append(name);
            builder.append(") {\n\t\tthis.");
            builder.append(name);
            builder.append(" = ");
            builder.append(name);
            builder.append(";\n\t}\n");
        }
        return builder.toString();
    }

    public static void saveJavaDao(GenerateInput input) {
        String path = input.getPath();
        String tableName = input.getTableName();
        String beanPackageName = input.getBeanPackageName();
        String beanName = input.getBeanName();
        String daoPackageName = input.getDaoPackageName();
        String daoName = input.getDaoName();
        String text = getTemplete("dao.txt");
        text = text.replace("{daoPackageName}", daoPackageName);
        text = text.replace("{beanPackageName}", beanPackageName);
        text = text.replace("{daoName}", daoName);
        text = text.replace("{table_name}", tableName);
        text = text.replace("{beanName}", beanName);
        text = text.replace("{beanParamName}", lowerFirstChar(beanName));
        String insertColumns = getInsertColumns(input.getColumnNames());
        text = text.replace("{insert_columns}", insertColumns);
        String insertValues = getInsertValues(input.getColumnNames(), input.getBeanFieldName());
        text = text.replace("{insert_values}", insertValues);
        FileUtil.saveTextFile(text, path + File.separator + getPackagePath(daoPackageName) + daoName + ".java");
        log.debug("生成java dao：{}模板", beanName);
        text = getTemplete("mapper.xml");
        text = text.replace("{daoPackageName}", daoPackageName);
        text = text.replace("{daoName}", daoName);
        text = text.replace("{table_name}", tableName);
        text = text.replace("{beanName}", beanName);
        String sets = getUpdateSets(input.getColumnNames(), input.getBeanFieldName());
        text = text.replace("{update_sets}", sets);
        String where = getWhere(input.getColumnNames(), input.getBeanFieldName());
        text = text.replace("{where}", where);
        FileUtil.saveTextFile(text, path + File.separator + beanName + "Mapper.xml");
    }

    private static String getInsertValues(List<String> columnNames, List<String> beanFieldName) {
        StringBuilder builder = new StringBuilder();
        int size = columnNames.size();
        for (int i = 0; i < size; i++) {
            String column = columnNames.get(i);
            if (!"id".equals(column)) {
                builder.append("#{").append(beanFieldName.get(i)).append("}, ");
            }
        }
        return StringUtils.substringBeforeLast(builder.toString(), ",");
    }

    private static String getInsertColumns(List<String> columnNames) {
        StringBuilder builder = new StringBuilder();
        for (String column : columnNames) {
            if (!"id".equals(column)) {
                builder.append(column).append(", ");
            }
        }
        return StringUtils.substringBeforeLast(builder.toString(), ",");
    }

    private static String getUpdateSets(List<String> columnNames, List<String> beanFieldName) {
        StringBuilder builder = new StringBuilder();
        int size = columnNames.size();
        for (int i = 0; i < size; i++) {
            String column = columnNames.get(i);
            if (!"id".equals(column)) {
                builder.append("\t\t\t<if test=\"");
                builder.append(column);
                builder.append(" != null\">\n\t\t\t\t");
                builder.append(column);
                builder.append(" = #{");
                builder.append(beanFieldName.get(i));
                builder.append("}, \n\t\t\t</if>\n");
            }
        }
        return builder.toString();
    }

    private static String getWhere(List<String> columnNames, List<String> beanFieldName) {
        StringBuilder builder = new StringBuilder();
        int size = columnNames.size();
        for (int i = 0; i < size; i++) {
            String column = columnNames.get(i);
            builder.append("\t\t\t<if test=\"params.");
            builder.append(column);
            builder.append(" != null and params.");
            builder.append(column);
            builder.append(" != ''\">\n\t\t\t\tand ");
            builder.append(column);
            builder.append(" = #{params.");
            builder.append(beanFieldName.get(i));
            builder.append("} \n\t\t\t</if>\n");
        }
        return builder.toString();
    }

    /**
     * 变量名
     *
     * @param beanName
     * @return
     */
    public static String lowerFirstChar(String beanName) {
        String name = StrUtils.str2hump(beanName);
        String firstChar = name.substring(0, 1);
        name = name.replaceFirst(firstChar, firstChar.toLowerCase());
        return name;
    }

    private static String getPackagePath(String packageName) {
        String packagePath = packageName.replace(".", "/");
        if (!packagePath.endsWith("/")) {
            packagePath = packagePath + "/";
        }
        return packagePath;
    }

    public static void saveController(GenerateInput input) {
        String path = input.getPath();
        String beanPackageName = input.getBeanPackageName();
        String beanName = input.getBeanName();
        String daoPackageName = input.getDaoPackageName();
        String daoName = input.getDaoName();
        String text = getTemplete("controller.txt");
        text = text.replace("{daoPackageName}", daoPackageName);
        text = text.replace("{beanPackageName}", beanPackageName);
        text = text.replace("{daoName}", daoName);
        text = text.replace("{daoParamName}", lowerFirstChar(daoName));
        text = text.replace("{beanName}", beanName);
        text = text.replace("{beanParamName}", lowerFirstChar(beanName));
        text = text.replace("{controllerPkgName}", input.getControllerPkgName());
        text = text.replace("{controllerName}", input.getControllerName());
        FileUtil.saveTextFile(text, path + File.separator + getPackagePath(input.getControllerPkgName())
                + input.getControllerName() + ".java");
        log.debug("生成controller：{}模板", beanName);
    }

    public static void saveHtmlList(GenerateInput input) {
        String path = input.getPath();
        String beanName = input.getBeanName();
        String beanParamName = lowerFirstChar(beanName);
        String text = getTemplete("htmlList.txt");
        text = text.replace("{beanParamName}", beanParamName);
        text = text.replace("{beanName}", beanName);
        List<String> beanFieldNames = input.getBeanFieldName();
        text = text.replace("{columnsDatas}", getHtmlColumnsDatas(beanFieldNames));
        text = text.replace("{ths}", getHtmlThs(beanFieldNames));
        FileUtil.saveTextFile(text, path + File.separator + beanParamName + "List.html");
        log.debug("生成查询页面：{}模板", beanName);
        text = getTemplete("htmlAdd.txt");
        text = text.replace("{beanParamName}", beanParamName);
        text = text.replace("{addDivs}", getAddDivs(beanFieldNames));
        FileUtil.saveTextFile(text, path + File.separator + "add" + beanName + ".html");
        log.debug("生成添加页面：{}模板", beanName);
        text = getTemplete("htmlUpdate.txt");
        text = text.replace("{beanParamName}", beanParamName);
        text = text.replace("{addDivs}", getAddDivs(beanFieldNames));
        text = text.replace("{initData}", getInitData(beanFieldNames));
        FileUtil.saveTextFile(text, path + File.separator + "update" + beanName + ".html");
        log.debug("生成修改页面：{}模板", beanName);
    }

    private static CharSequence getInitData(List<String> beanFieldNames) {
        StringBuilder builder = new StringBuilder();
        beanFieldNames.forEach(b -> {
            builder.append("\t\t\t\t\t\t$('#");
            builder.append(b);
            builder.append("').val(data.");
            builder.append(b);
            builder.append(");\n");
        });
        return builder.toString();
    }

    private static String getAddDivs(List<String> beanFieldNames) {
        StringBuilder builder = new StringBuilder();
        beanFieldNames.forEach(b -> {
            if (!"id".equals(b) && !"createTime".equals(b) && !"gmtTime".equals(b)) {
                builder.append("\t\t\t<div class='form-group'>\n\t\t\t\t<label class='col-md-2 control-label'>");
                builder.append(b);
                builder.append("</label>\n\t\t\t\t<div class='col-md-10'>\n\t\t\t\t\t<input class='form-control' placeholder='");
                builder.append(b);
                builder.append("' type='text' name='");
                builder.append(b);
                builder.append("' id='");
                builder.append(b);
                builder.append("' data-bv-notempty='true' data-bv-notempty-message='");
                builder.append(b);
                builder.append(" 不能为空'>\n\t\t\t\t</div>\n\t\t\t</div>\n");
            }
        });
        return builder.toString();
    }

    private static String getHtmlThs(List<String> beanFieldNames) {
        StringBuilder builder = new StringBuilder();
        beanFieldNames.forEach(b -> {
            builder.append("\t\t\t\t\t\t\t\t\t<th>{beanFieldName}</th>\n".replace("{beanFieldName}", b));
        });
        return builder.toString();
    }

    private static String getHtmlColumnsDatas(List<String> beanFieldNames) {
        StringBuilder builder = new StringBuilder();
        beanFieldNames.forEach(b -> {
            builder.append("\t\t\t\t{\"data\" : \"{beanFieldName}\", \"defaultContent\" : \"\"},\n"
                    .replace("{beanFieldName}", b));
        });
        return builder.toString();
    }
}