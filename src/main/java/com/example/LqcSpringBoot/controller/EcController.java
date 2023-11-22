package com.example.LqcSpringBoot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 数据分析 data
 */
@Controller
@RequestMapping("/ec")
public class EcController {

    /**
     * 跳转连接（数据分析页面）
     */
    @RequestMapping("/ec")
    public String ecUrl () {
        return "echatpage";
    }

}
