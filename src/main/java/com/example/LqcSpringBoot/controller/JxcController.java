package com.example.LqcSpringBoot.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.LqcSpringBoot.mapper.CcMapper;
import com.example.LqcSpringBoot.mapper.JxcMapper;
import com.example.LqcSpringBoot.mapper.JxctablebfMapper;
import com.example.LqcSpringBoot.mapper.RcMapper;
import com.example.LqcSpringBoot.model.Cctable;
import com.example.LqcSpringBoot.model.Jxctable;
import com.example.LqcSpringBoot.model.Jxctablebf;
import com.example.LqcSpringBoot.model.Rctable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Autowired
    public JxctablebfMapper jxctablebf;

    /**
     * 定时任务 跑表到备份表
     * 每天 23 点 59 分启动
     */
    @Scheduled(cron="0 59 23 * * ?")
    public void dsrw() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        List<Jxctable> jxcs = jxc.selectAll();
        jxcs.forEach(jxctables -> {
            Jxctablebf jbf = new Jxctablebf();
            jbf.setDate(sdf.format(new Date()));
            jbf.setBz(jxctables.getBz());
            jbf.setPnumber(jxctables.getPnumber());
            jbf.setPname(jxctables.getPname());
            jbf.setColor(jxctables.getColor());
            jbf.setType(jxctables.getType());
            jbf.setQcs(jxctables.getQcs());
            jbf.setRccount(jxctables.getRccount());
            jbf.setCccount(jxctables.getCccount());
            jbf.setJccount(jxctables.getJccount());
            jbf.setPdcount(jxctables.getPdcount());
            jbf.setCycount(jxctables.getCycount());
            jbf.setPdstatus(jxctables.getPdstatus());
            jbf.setId(cn.hutool.core.lang.UUID.randomUUID().toString().replace("-", ""));
            jxctablebf.insert(jbf);

        });
    }

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
        List<Jxctable> list = jxc.selectKuserBysameting((String) map.get("pnumber"), start, end);
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

    /**
     * 更新盘点条数与差异条数
     */
    @RequestMapping("/updatepds")
    @ResponseBody
    public String upd(Jxctable jxctable) {
      int sss =  Integer.valueOf(jxctable.getPdcount()) - Integer.valueOf(jxctable.getJccount());
        if (sss==0) {
            jxctable.setPdstatus("正常");
        } else if (sss<0) {
            jxctable.setPdstatus("盘亏");
        } else{
            jxctable.setPdstatus("盘盈");

        }
            jxctable.setCycount(String.valueOf(sss));
             String.valueOf(jxc.updateById(jxctable));
        return "1";
    }


}
