package com.example.LqcSpringBoot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 导航 menu
 */
@Controller
@RequestMapping("/menu")
public class MenuController {

    /**
     * 跳转连接（导航页面）
     */
    @RequestMapping("/menu")
    public String menuUrl () {
        return "menu";
    }

}
