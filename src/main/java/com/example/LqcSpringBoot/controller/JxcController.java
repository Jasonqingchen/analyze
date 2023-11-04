package com.example.LqcSpringBoot.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.LqcSpringBoot.mapper.CcMapper;
import com.example.LqcSpringBoot.mapper.JxcMapper;
import com.example.LqcSpringBoot.mapper.RcMapper;
import com.example.LqcSpringBoot.model.Cctable;
import com.example.LqcSpringBoot.model.Jxctable;
import com.example.LqcSpringBoot.model.Rctable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * 进销存
 * liuqingchen 2023/10/31
 */
@Controller
@RequestMapping("/jxc")
public class JxcController {
    @Autowired
    public JxcMapper jxc;



    /**
     * 条件搜索
     */
    @RequestMapping("/seach_ht")
    @ResponseBody
    public List<Jxctable> seach(@RequestParam Map map) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String start = null;
        String end = null;
        if(map.get("rcval1").toString().length()>0 && map.get("rcval2").toString().length()>0) {
            try {
                start = sdf.format(sdf.parse(map.get("rcval1").toString()));
                end = sdf.format(sdf.parse(map.get("rcval2").toString()));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            };
        }
        List<Jxctable> list = jxc.selectKuserBysameting((String) map.get("pnumber"), (String) map.get("customerphone"), start, end);
        return list;
    }



    /**
     * 列表查询（）
     */
    @RequestMapping("/lists")
    @ResponseBody
    public List<Jxctable> lists(@RequestParam Map map) {
        return jxc.selectAll();
    }


}
