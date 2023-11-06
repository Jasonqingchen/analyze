package com.example.LqcSpringBoot.controller;

import com.example.LqcSpringBoot.mapper.JxctablebfMapper;
import com.example.LqcSpringBoot.model.Jxctable;
import com.example.LqcSpringBoot.model.Jxctablebf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/jxcbf")
public class JxctablebfController {

@Autowired
    public JxctablebfMapper jfmapper;
    /**
     * 条件搜索
     */
    @RequestMapping("/seach_ht")
    @ResponseBody
    public List<Jxctablebf> seach(@RequestParam Map map) {
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
        List<Jxctablebf> list = jfmapper.selectKuserBysameting(start, end);
        return list;
    }

    /**
     * 列表查询（）
     */
    @RequestMapping("/lists")
    @ResponseBody
    public List<Jxctablebf> lists(@RequestParam Map map) {
        return jfmapper.selectAll();
    }

}
