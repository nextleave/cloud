package com.geblob.cloud.controller;

import com.geblob.cloud.pojo.Student;
import com.geblob.cloud.util.ExcelGenerator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PoiController {
    @Autowired
    HttpServletResponse response;

    /**
     * Excel表格导出接口
     * http://localhost:8080/ExcelDownload
     *
     * @param response response对象
     * @throws IOException 抛IO异常
     */
    @RequestMapping("/excelDownload")
    public void excelDownload() throws IOException {
        Pair<String, String>[] header = new Pair[6];
        header[0] = Pair.with("id", "id");
        header[1] = Pair.with("name", "姓名");
        header[2] = Pair.with("sex", "性别");
        header[3] = Pair.with("age", "年龄");
        header[4] = Pair.with("address", "地址");
        header[5] = Pair.with("score", "分数");
        header[5] = Pair.with("createTime", "出生日期");

        Student student1 = new Student(1, "小红", "女", 23, "成都青羊区", new BigDecimal(96));
        Student student2 = new Student(2, "小强", "男", 26, "成都金牛区", new BigDecimal(91));
        Student student3 = new Student(3, "小明", "男", 28, "成都武侯区", new BigDecimal(90));
        List<Student> studentList = new ArrayList<>();
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);
        HSSFWorkbook workbook = ExcelGenerator.generateFromPojo(studentList, header);

        //准备将Excel的输出流通过response输出到页面下载
        //八进制输出流
        response.setContentType("application/octet-stream");

        //这后面可以设置导出Excel的名称，此例中名为student.xls
        response.setHeader("Content-disposition", "attachment;filename=student.xls");

        //刷新缓冲
        response.flushBuffer();

        //workbook将Excel写入到response的输出流中，供页面下载
        workbook.write(response.getOutputStream());
    }


}
