package com.example.LqcSpringBoot.controller;

import com.alibaba.fastjson.JSON;
import com.example.LqcSpringBoot.mapper.ContainerMapper;
import com.example.LqcSpringBoot.model.Container;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping("/container")
public class ContainerController {

    @Autowired
    public ContainerMapper kmapper;


    /**
     * 待审核查询
     */
    @RequestMapping("/dshList")
    @ResponseBody
    public List<Container> dshList() {
        return kmapper.selectListByStatus();
    }
    /**
     * 跳转连接 (主页)
     */
    @RequestMapping("/shouye")
    public String shouyeUrl () {
        return "shouye";
    }

    /**
     * 清空列表
     * @param kuser
     * @return
     */
    @RequestMapping("/cleanTable")
    @ResponseBody
    public Integer cleanTable (Container kuser) {
        kmapper.cleanTable();
        return 1;
    }

    /**
     * 添加货柜信息初始化
     * @param container
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public Integer inser (Container container) {
        container.setId(UUID.randomUUID().toString().replace("-", "").toString());
        container.setStatus("未到港");
        int insert = kmapper.insert(container);
        return insert;
    }

    @RequestMapping("/updates")
    @ResponseBody
    public Integer updatescontainer (Container container) {
        int count = kmapper.updateById(container);
        return count;
    }


    /**
     * 条件搜索
     */
    @RequestMapping("/seach_ht")
    @ResponseBody
    public List<Container> seach (@RequestParam Map map) {
        List<Container> list = kmapper.selectKuserBysameting((String)map.get("gnumber"),(String)map.get("pod"),(String)map.get("phone"));
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
        List<Container> user = kmapper.selectKuserById(map.get("id").toString());
        user.get(0).setStatus("1");
        int i = kmapper.updateById(user.get(0));
        return i;
    }

    /**
     * 回显
     */
    @RequestMapping("/editselect")
    @ResponseBody
    public List<Container> editselect (@RequestParam Map map) {
        return kmapper.selectKuserById((String) map.get("id"));
    }


    /**
     * 列表查询（）
     */
    @RequestMapping("/lists")
    @ResponseBody
    public List<Container> lists (@RequestParam Map map) {
        return kmapper.selectList();
    }


    /**
     * 条件查询()
     * @param request
     * @param response
     * @param map
     * @return
     */
    @RequestMapping("/seach")
    @ResponseBody
    public List<Container> seach(HttpServletRequest request, HttpServletResponse response , @RequestParam Map map) {
        String str =(String) map.get("data");
        Map maps = (Map) JSON.parse(str);
        List<Container> list = kmapper.selectByName(maps.get("name").toString());
        if (list.isEmpty()) {
            return null;
        } else {
            return list;
        }
    }
}
