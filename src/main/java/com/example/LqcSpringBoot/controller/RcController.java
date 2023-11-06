package com.example.LqcSpringBoot.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.LqcSpringBoot.mapper.JxcMapper;
import com.example.LqcSpringBoot.mapper.RcMapper;
import com.example.LqcSpringBoot.model.Container;
import com.example.LqcSpringBoot.model.Jxctable;
import com.example.LqcSpringBoot.model.Rctable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 入仓
 * liuqingchen 2023/10/31
 */
@Controller
@RequestMapping("/rc")
public class RcController {
    @Autowired
    public RcMapper rc;

    @Autowired
    public JxcMapper jxc;

    /**
     * 关联查询
     */
    @RequestMapping("/gl")
    @ResponseBody
    public List<Rctable> glrclist(Container container) {
        return rc.selectListByGnumber(container.getGnumber());
    }
    /**
     * 列表查询（）
     */
    @RequestMapping("/lists")
    @ResponseBody
    public List<Rctable> lists (@RequestParam Map map) {
        return rc.selectList();
    }
    /**
     * 添加入仓商品清单（入仓单）
     * @param
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public String inser (@RequestParam Map map) {
        List<Rctable> list = JSONObject.parseArray(map.get("list").toString(), Rctable.class);
        list.forEach(rcs -> {
            String d = rcs.getRcdate().substring(8, 10);
            String m = rcs.getRcdate().substring(5, 7);
            String y = rcs.getRcdate().substring(0, 4);

            if ("01".equals(m) || "03".equals(m) || "05".equals(m) || "07".equals(m) || "08".equals(m) || "10".equals(m) && "31".equals(d)) {
                m =String.valueOf(Integer.valueOf(m)+1);
                d = "01";
            }  else if("04".equals(m) || "06".equals(m) || "09".equals(m) || "11".equals(m) && "30".equals(d)) {
                m =String.valueOf(Integer.valueOf(m)+1);
                d = "01";
            } else if ("12".equals(m) && "31".equals(d)){
                d = "01";
                m = "01";
                y = String.valueOf(Integer.valueOf(y)+1);
            } else {
                d =String.valueOf(Integer.valueOf(d)+1);
            }
            if((Integer.valueOf(y) % 4 == 0 && Integer.valueOf(y) % 100 !=0) || (Integer.valueOf(y) % 400==0)) {
                //闰年
                if ("02".equals(m) && "29".equals(d)) {
                    m =String.valueOf(Integer.valueOf(m)+1);
                    d = "01";
                }
            } else {
                //平年
                if ("02".equals(m) && "28".equals(d)) {
                    m = String.valueOf(Integer.valueOf(m) + 1);
                    d = "01";
                }
            }
            if (m.length()==1){
                m="0"+m;
            }
            if (d.length()==1){
                d="0"+d;
            }
            rcs.setRcdate(y+"-"+m+"-"+d);
            Rctable rctable = (Rctable) rc.selectBYPnumber(rcs.getPnumber());
            if(rctable!=null){
                //使用全平均
                rcs.setId(rctable.getId());
               int newcostcount = Integer.parseInt(rctable.getCostcount()) + Integer.parseInt(rcs.getCostcount());//成本总价旧的
               int newcount = Integer.parseInt(rctable.getRccount()) + Integer.parseInt(rcs.getRccount());
               int newcpostprice = newcostcount / newcount;
               int i = Integer.parseInt(rctable.getRccount()) + Integer.parseInt(rcs.getRccount());
                rcs.setRccount(String.valueOf(i));
                rcs.setCostprice(String.valueOf(newcpostprice));
                rcs.setCostcount(String.valueOf(newcostcount));
                //修改 加库存 和 加总价
                rc.updateById(rcs);
            } else {
                rcs.setCostcount(String.valueOf(Integer.parseInt(rcs.getRccount()) * Integer.parseInt(rcs.getCostprice())));
            //新增
                rcs.setId(cn.hutool.core.lang.UUID.randomUUID().toString().replace("-", ""));
                rc.insert(rcs);
            }
            //对核销表的操作
           // 根据产品编码查询是否存在 如果存在 只修改 入仓数 和 结存数 做运算 如果没有则新增
           Jxctable jxctable = jxc.selectByPnamber(rcs.getPnumber());
            if (jxctable!=null) {
                //修改
                if(rcs.getQcs()=="0"){
                int newcount =   Integer.valueOf((String)jxctable.getRccount()) + Integer.valueOf((String)rcs.getRccount());
                    jxctable.setRccount(String.valueOf(newcount));
                   int newjccount = newcount - Integer.valueOf((String)jxctable.getCccount());
                    jxctable.setJccount(String.valueOf(newjccount));
                    jxc.updateById(jxctable);
                }

            } else {
                //新增
                Jxctable jxct= new Jxctable();
                jxct.setId(cn.hutool.core.lang.UUID.randomUUID().toString().replace("-", ""));
                jxct.setType(rcs.getType().toString());
                jxct.setColor(rcs.getColor().toString());
                jxct.setPnumber(rcs.getPnumber().toString());
                jxct.setPname(rcs.getPname().toString());
                jxct.setRccount(rcs.getRccount().toString());
                jxct.setCccount("0");
                jxct.setJccount(rcs.getRccount());
                jxct.setPdcount("");
                jxct.setCycount("");
                if("".equals(rcs.getQcs())) {
                    jxct.setQcs("0");
                } else {
                    jxct.setQcs(rcs.getQcs().toString());
                }

                jxc.insert(jxct);

            }

        });
        return "1";
    }

    /**
     * 条件搜索
     */
    @RequestMapping("/seach_ht")
    @ResponseBody
    public List<Rctable> seach (@RequestParam Map map) {
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


        List<Rctable> list =rc.selectKuserBysameting((String)map.get("pnumber"),(String)map.get("sshg"),start,end);
        return list;
    }
    /**
     * 比较
     */
    @RequestMapping("/selectrccount")
    @ResponseBody
    public Integer selectrccount (@RequestParam Map map) {

        Rctable rctable = rc.selectBYPnumber(map.get("pnumber").toString());
        if(rctable==null) {
            return 3;
        }
        String cpunt = rctable.getRccount();

               Integer ints = Integer.parseInt(cpunt) - Integer.parseInt(map.get("cccount").toString().replace(" ", ""));
               if (ints>=0){
                   return 2;
               } else {
                   return 1;
               }
    }

    /**
     * 删除
     */
    @RequestMapping("/del")
    @ResponseBody
    public Integer del (@RequestParam Map map) {
        Integer i = rc.deleteKuserById((String) map.get("id"));
        return i;
    }
}
