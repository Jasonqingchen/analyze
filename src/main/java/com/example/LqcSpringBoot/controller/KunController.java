package com.example.LqcSpringBoot.controller;

import com.alibaba.fastjson.JSON;
import com.example.LqcSpringBoot.mapper.KuserMapper;
import com.example.LqcSpringBoot.mapper.SkMapper;
import com.example.LqcSpringBoot.model.Kuser;
import com.example.LqcSpringBoot.model.Sk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping("/kunyueye")
public class KunController {

    @Autowired
    public SkMapper mapper;
    @Autowired
    public KuserMapper kmapper;

    /**
     * 待审核查询
     */
    @RequestMapping("/dshList")
    @ResponseBody
    public List<Kuser> dshList() {
         return kmapper.selectListByStatus();
    }
    /**
     * 跳转连接 (主页)
     */
    @RequestMapping("/shouye")
    public String shouyeUrl () {
        return "shouye";
    }

    @RequestMapping("/cleanTable")
    @ResponseBody
    public Integer cleanTable (Kuser kuser) {
        kmapper.cleanTable();
        return 1;
    }

    @RequestMapping("/add")
    @ResponseBody
    public Integer inser (Kuser kuser) {
        if (kuser.getCm().isEmpty()) {
            kuser.setCm("-");
        }
        if (kuser.getPhone().isEmpty()) {
            kuser.setPhone("-");
        }
        kuser.setId(UUID.randomUUID().toString().replace("-", "").toString());
        int insert = kmapper.insert(kuser);
        return insert;
    }

    @RequestMapping("/update")
    @ResponseBody
    public Integer update (Kuser kuser) {
        if (kuser.getCm().isEmpty()) {
            kuser.setCm("-");
        }
        if (kuser.getPhone().isEmpty()) {
            kuser.setPhone("-");
        }
        return kmapper.updateKuser((String)kuser.getCm(),(String)kuser.getDyname(),(String)kuser.getName(),(String)kuser.getNum(),(String)kuser.getId(),(String)kuser.getSex(),(String)kuser.getPhone());
    }


    /**
     * 条件搜索
     */
    @RequestMapping("/seach_ht")
    @ResponseBody
    public List<Kuser> seach (@RequestParam Map map) {
        List<Kuser> list = kmapper.selectKuserByNameAndNum((String)map.get("name"),(String)map.get("num"),(String)map.get("phone"));
        return list;
    }

    /**
     * 删除
     */
    @RequestMapping("/del")
    @ResponseBody
    public Integer del (@RequestParam Map map) {
        Integer i = kmapper.deleteKuserById((String) map.get("id"));
        return i;
    }
    /**
     * 审核通过
     */
    @RequestMapping("/yesexamine")
    @ResponseBody
    public Integer yesexamine (@RequestParam Map map) {
        List<Kuser> user = kmapper.selectKuserById(map.get("id").toString());
        user.get(0).setStatus("1");
        int i = kmapper.updateById(user.get(0));
        return i;
    }

    /**
     * 一键审核
     */
    @RequestMapping("/oneexamine")
    @ResponseBody
    public Integer oneexamine () {
        int i =  kmapper.updateAll();
        return i;
    }


    /**
     * 回显
     */
    @RequestMapping("/editselect")
    @ResponseBody
    public List<Kuser> editselect (@RequestParam Map map) {
        return kmapper.selectKuserById((String) map.get("id"));
    }


    /**
     * 以上为后台对数据库的操作 维护页面
     */

    /**
     * 列表查询（）
     */
    @RequestMapping("/lists")
    @ResponseBody
    public List<Kuser> lists (@RequestParam Map map) {
        return kmapper.selectListisStatus();
    }

    /**
     * 成绩查询（条件查询和初始查询所有）
     * @param request
     * @param response
     * @param map
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public  List test (HttpServletRequest request, HttpServletResponse response , @RequestParam Map map) {
       String str =(String) map.get("data");
       List<Sk> l = new ArrayList();
        Map maps = (Map) JSON.parse(str);
        List<Sk> list= null;
        if (maps.size()==0) {
            list = mapper.selectListByUserid();
        } else {
             list = mapper.selectByMap(maps.get("sex").toString(),maps.get("userName").toString(),maps.get("num").toString(),maps.get("zb").toString());
        }
        list.forEach(lis->{
            Map m = new HashMap();
            List dataList = mapper.selectByUserId(lis.getId());
            m.put("data",dataList);
            lis.setMap(m);
            l.add(lis);
        });
        return l;
    }

    /**
     * 储存数据
     * @param request
     * @param response
     * @param map
     * @return
     */
    @RequestMapping("/sbmit")
    @ResponseBody
    public List<Kuser> sbmit (HttpServletRequest request, HttpServletResponse response , @RequestParam Map map) {
        String str =(String) map.get("data");
        Map maps = (Map) JSON.parse(str);
        List<Kuser> list = kmapper.selectByNum(maps.get("num").toString());
        if (list.size()>0) {
            return list;
        } else {
            Kuser k = new Kuser();
            k.setId(UUID.randomUUID().toString().replace("-", ""));
            k.setName(maps.get("name").toString());
            k.setSex(maps.get("sex").toString());
            k.setNum(maps.get("num").toString());
            k.setPhone(maps.get("phone").toString());
            k.setDyname(maps.get("dyname").toString());
            kmapper.insert(k);
            return list;
        }
    }

    /**
     * 条件查询(会员)
     * @param request
     * @param response
     * @param map
     * @return
     */
    @RequestMapping("/seach")
    @ResponseBody
    public List<Kuser> seach(HttpServletRequest request, HttpServletResponse response , @RequestParam Map map) {
        String str =(String) map.get("data");
        Map maps = (Map) JSON.parse(str);
       List<Kuser> list = kmapper.selectByName(maps.get("name").toString());
       if (list.isEmpty()) {
           return null;
       } else {
           return list;
       }
    }
}
