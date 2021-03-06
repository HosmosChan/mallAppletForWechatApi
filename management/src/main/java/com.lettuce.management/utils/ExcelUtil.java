package com.lettuce.management.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
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
 * excel工具类
 *
 * @author Hosmos
 * @date 2021年07月08日
 */
public class ExcelUtil {
    public static void excelLocal(String path, String fileName, String[] headers, List<Object[]> datas) {
        Workbook workbook = getWorkbook(headers, datas);
        if (workbook != null) {
            ByteArrayOutputStream byteArrayOutputStream = null;
            FileOutputStream fileOutputStream = null;
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                workbook.write(byteArrayOutputStream);
                String suffix = ".xls";
                File file = new File(path + File.separator + fileName + suffix);
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(byteArrayOutputStream.toByteArray());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 导出excel
     *
     * @param fileName 文件名
     * @param headers  列头
     * @param datas    数据
     * @param response 请求信息
     * @author Hosmos
     * @date 2021-07-08
     */
    public static void excelExport(String fileName, String[] headers, List<Object[]> datas, HttpServletResponse response) {
        Workbook workbook = getWorkbook(headers, datas);
        if (workbook != null) {
            ByteArrayOutputStream byteArrayOutputStream = null;
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                workbook.write(byteArrayOutputStream);
                String suffix = ".xls";
                response.setContentType("application/vnd.ms-excel;charset=utf-8");
                response.setHeader("Content-Disposition",
                        "attachment;filename=" + new String((fileName + suffix).getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
                OutputStream outputStream = response.getOutputStream();
                outputStream.write(byteArrayOutputStream.toByteArray());
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @param headers 列头
     * @param datas   数据
     * @return Workbook
     * @author Hosmos
     * @date 2021-07-08
     */
    public static Workbook getWorkbook(String[] headers, List<Object[]> datas) {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        Row row = null;
        Cell cell = null;
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        Font font = workbook.createFont();
        int line = 0, maxColumn = 0;
        // 设置列头
        if (headers != null && headers.length > 0) {
            row = sheet.createRow(line++);
            row.setHeightInPoints(23);
            font.setBold(true);
            font.setFontHeightInPoints((short) 13);
            style.setFont(font);
            maxColumn = headers.length;
            for (int i = 0; i < maxColumn; i++) {
                cell = row.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(style);
            }
        }
        // 渲染数据
        if (datas != null && datas.size() > 0) {
            for (Object[] data : datas) {
                if (data != null && data.length > 0) {
                    row = sheet.createRow(line++);
                    row.setHeightInPoints(20);
                    int length = data.length;
                    if (length > maxColumn) {
                        maxColumn = length;
                    }
                    for (int i = 0; i < length; i++) {
                        cell = row.createCell(i);
                        cell.setCellValue(data[i] == null ? null : data[i].toString());
                    }
                }
            }
        }
        for (int i = 0; i < maxColumn; i++) {
            sheet.autoSizeColumn(i);
        }
        return workbook;
    }
}