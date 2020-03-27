package com.geblob.cloud.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellType;
import org.javatuples.Pair;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExcelGenerator {
    /**
     * @param pojoList 数据列表,列表元素为javabean
     * @param header   二元组数组,数组元素为二元组,二元组的第一个元素为字段名,第二个元素为该字段名在excel表头中的表示,如  header[1] = Pair.with("name", "姓名");
     * @return 返回一个HSSFWorkbook文档
     */
    public static HSSFWorkbook generateFromPojo(List pojoList, Pair<String, String>[] header) {
        //声明一个工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();

        //生成一个表格，设置表格名称为"学生表"
        HSSFSheet sheet = workbook.createSheet("表1");

        //设置表格列宽度为10个字节
        sheet.setDefaultColumnWidth(10);

        //创建第一行表头
        HSSFRow headRow = sheet.createRow(0);

        //遍历添加表头(下面模拟遍历学生，也是同样的操作过程)
        for (int i = 0; i < header.length; i++) {
            //创建一个单元格
            HSSFCell cell = headRow.createCell(i);

            //创建一个内容对象
            HSSFRichTextString text = new HSSFRichTextString(header[i].getValue1());

            //将内容对象的文字内容写入到单元格中
            cell.setCellValue(text);
        }
        for (int i = 0; i < pojoList.size(); i++) {
            Object pojo = pojoList.get(i);
            HSSFRow row = sheet.createRow(i + 1);
            for (int j = 0; j < header.length; j++) {
                Field field = null;
                try {
                    field = pojo.getClass().getDeclaredField(header[j].getValue0());
                    // 设置访问权限为true
                    field.setAccessible(true);
                    // 获取属性
                    HSSFCell cell = row.createCell(j);
//                    HSSFRichTextString text = new HSSFRichTextString(field.get(student).toString());
                    Object fieldVal = field.get(pojo);
                    if (fieldVal instanceof Date) {
                        cell.setCellValue((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date) fieldVal)));
                    } else {
                        cell.setCellValue(fieldVal.toString());
                    }
                } catch (IllegalAccessException | NoSuchFieldException e) {
                    throw new RuntimeException(e);
                }

            }

        }
        setSizeColumn(sheet);
        return workbook;
    }

    // 自适应宽度(中文支持)
    private static void setSizeColumn(HSSFSheet sheet) {
        for (int columnNum = 0; columnNum <= 8; columnNum++) {
            int columnWidth = sheet.getColumnWidth(columnNum) / 256;
            for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
                HSSFRow currentRow;
                //当前行未被使用过
                if (sheet.getRow(rowNum) == null) {
                    currentRow = sheet.createRow(rowNum);
                } else {
                    currentRow = sheet.getRow(rowNum);
                }

                if (currentRow.getCell(columnNum) != null) {
                    HSSFCell currentCell = currentRow.getCell(columnNum);
                    if (currentCell.getCellType() == CellType.STRING) {
                        int length = currentCell.getStringCellValue().getBytes().length;
                        if (columnWidth < length) {
                            columnWidth = length;
                        }
                    }
                }
            }
            sheet.setColumnWidth(columnNum, columnWidth * 256);
        }
    }
}
