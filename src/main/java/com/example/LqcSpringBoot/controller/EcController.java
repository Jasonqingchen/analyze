package com.example.LqcSpringBoot.controller;

import com.example.LqcSpringBoot.mapper.CcMapper;
import com.example.LqcSpringBoot.mapper.ContainerMapper;
import com.example.LqcSpringBoot.model.Cctable;
import com.example.LqcSpringBoot.model.Container;
import com.example.LqcSpringBoot.model.Jxctable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据分析 data
 */
@Controller
@RequestMapping("/ec")
public class EcController {
    @Autowired
    public ContainerMapper kmapper;//货柜
    @Autowired
    public CcMapper cct;//订单

    /**
     * 跳转连接（数据分析页面）
     */
    @RequestMapping("/ec")
    public String ecUrl () {
        return "echatpage";
    }

    /**
     * search order and 货柜 count
     */
    @RequestMapping("/selectHgAndOrder")
    @ResponseBody
    public List selectHgAndOrder() {
        List list = new ArrayList<>();
       List hg = kmapper.selectListByYears();//货柜数据
       List order = cct.selectListByyears();//订单数据
        list.add(hg);
        list.add(order);
        return list;
    }

}
