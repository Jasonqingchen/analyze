package com.example.LqcSpringBoot.controller;

import com.example.LqcSpringBoot.ut.MainPartimportBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

/**
 * 对excel的操作
 */
@Controller
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    private MainPartimportBean mainPartimportBean;

    /**
     * 导入会员
     * @return
     */
    @RequestMapping("/dr")
    public String dr (HttpServletRequest request, @RequestParam(required = false) MultipartFile file ) throws IOException {
        InputStream fileInputStream = null;
        fileInputStream = file.getInputStream();
        mainPartimportBean.insertDB(fileInputStream);
        request.getSession().setAttribute("message", "导入成功");
        request.getSession().setAttribute("url", "kunyueye/index");
        return String.format("redirect:/message");
    }
}
