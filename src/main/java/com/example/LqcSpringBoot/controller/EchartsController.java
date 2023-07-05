package com.example.LqcSpringBoot.controller;

import com.alibaba.fastjson.JSON;
import com.example.LqcSpringBoot.mapper.KuserMapper;
import com.example.LqcSpringBoot.model.Kuser;
import com.example.LqcSpringBoot.ut.GetPhoneAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/echarts")
public class EchartsController {
    @Autowired
    public KuserMapper kmapper;
    public int flag;

    /**
     * 会员分布
     *
     * @return
     */
    @RequestMapping("/fb")
    @ResponseBody
    public List<Map> fb() {
        List<Kuser> kusers = kmapper.selectListGroupByPhone();
        List<Map> list = new ArrayList();

        if (!kusers.isEmpty()) {
            for (Kuser u : kusers) {
                if (u.getPhone()!=null || u.getPhone()!="") {
                    Map map = new HashMap<>();
                    try {
                        Map maps = (Map) JSON.parse(GetPhoneAddress.getPhoneNumberInfo("+86" + u.getPhone()).toString());
                        if (list.size()==0) {
                            map.put("name", maps.get("description"));
                            map.put("value", Integer.parseInt(u.getId()));
                            list.add(map);
                        } else {
                            list.forEach(li->{
                                if (li.get("name").equals(maps.get("description"))) {
                                    li.put("value",(Integer)li.get("value")+1);
                                }else {
                                    map.put("name", maps.get("description"));
                                    map.put("value", Integer.parseInt(u.getId()));
                                }
                                list.add(map);
                            });
                        }

                    } catch (Exception e) {
                       // e.printStackTrace();
                    }
                }
            }
        }
        return list;
    }

    /**
     * 性别占比
     *
     * @return
     */
    @RequestMapping("/sexinit")
    @ResponseBody
    public List<Map> sexinit() {
        List<Kuser> kusers = kmapper.selectListGroupBySex();
        List<Map> list = new ArrayList();
        for (Kuser u : kusers) {
            Map map = new HashMap<>();
            try {
                map.put("name", u.getSex());
                map.put("value", Integer.parseInt(u.getId()));
                list.add(map);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    /**
     * 日注册数量检测
     *
     * @return
     */
    @RequestMapping("/registerinit")
    @ResponseBody
    public List<Kuser> registerinit() {
        return  kmapper.selectListGroupByDate();
    }

}
