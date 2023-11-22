package com.example.LqcSpringBoot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 数据分析 data
 */
@Controller
@RequestMapping("/setting")
public class SettingController {

    /**
     * 跳转连接（数据分析页面）
     */
    @RequestMapping("/setting")
    public String settingUrl () {
        return "setting";
    }

}
