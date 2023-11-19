package com.example.LqcSpringBoot.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.LqcSpringBoot.mapper.*;
import com.example.LqcSpringBoot.model.*;
import com.example.LqcSpringBoot.model.Container;
import com.example.LqcSpringBoot.ut.ExportExcel;
import com.example.LqcSpringBoot.ut.MainPartimportBean;
import com.gaoice.easyexcel.spring.boot.autoconfigure.annotation.ResponseExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 对excel的操作
 */
@Controller
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    private MainPartimportBean mainPartimportBean;

    @Autowired
    public ContainerMapper cm;

    @Autowired
    public RcMapper rc;

    @Autowired
    public CcMapper cc;

    @Autowired
    public JxcMapper jxc;

    @Autowired
    public JxctablebfMapper jbf;

    /**
     * 导入
     * @return
     */
    @RequestMapping("/dr")
    public String dr (HttpServletRequest request, @RequestParam(required = false) MultipartFile file ) throws IOException {
        InputStream fileInputStream = null;
        fileInputStream = file.getInputStream();
        mainPartimportBean.insertDB(fileInputStream);
        request.getSession().setAttribute("message", "导入成功");
        request.getSession().setAttribute("url", "container/shouye");
        return String.format("redirect:/message");
    }

    /**
     * 出仓列表导出功能
     */
    @RequestMapping(value = "/ccdc", method = RequestMethod.POST)
    public void ccdc (HttpServletResponse response, Cctable cctable,@RequestParam  Map map) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String start = null;
        String end = null;
        if(map.get("start").toString().length()>0 && map.get("end").toString().length()>0) {
            try {
                start = sdf.format(sdf.parse(map.get("start").toString()));
                end = sdf.format(sdf.parse(map.get("end").toString()));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            };
        }
        String[] rowsName = new String[] { "出库时间", "订单编号","商品编码", "商品名称", "颜色", "规格", "出库数量" , "单价" , "总价" , "客户姓名" , "客户电话", "客户地址", "出库方式","备注"};
        // 进行查新（导出所有）
        List<Cctable> c = cc.selectKuserBysameting(cctable.getOrderid(),cctable.getCustomerphone(),start,end);
        List<Object[]> dataList = new ArrayList<Object[]>();
        Object[] objs = null;
        for (int i = 0; i < c.size(); i++) {
            Cctable man = c.get(i);
            objs = new Object[rowsName.length];
            objs[0] =  man.getCcdate();
            objs[1] =  man.getPnumber();
            objs[2] =  man.getPname();
            objs[3] =  man.getColor();
            objs[4] =  man.getType();
            objs[5] =  man.getCccount();
            objs[6] =  man.getCcprice();
            objs[7] =  man.getCcsumprice();
            objs[8] =  man.getCustomername();
            objs[9] =  man.getCustomerphone();
            objs[10] =  man.getCustomeraddress();
            objs[11] =  man.getCcfs();
            objs[12] =  man.getBz();
            dataList.add(objs);
        }
        ExportExcel ex = new ExportExcel("出库信息列表", rowsName, dataList);
        ex.setResponse(response);
        ex.export();

    }


    /**
     * 入仓列表导出功能
     */
    @RequestMapping(value = "/rcdc", method = RequestMethod.POST)
    public void rcdc (HttpServletResponse response, Rctable rctable, @RequestParam Map map) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String start = null;
        String end = null;
        if(map.get("start").toString().length()>0 && map.get("end").toString().length()>0) {
            try {
                start = sdf.format(sdf.parse(map.get("start").toString()));
                end = sdf.format(sdf.parse(map.get("end").toString()));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            };
        }
        String[] rowsName = new String[] { "入库时间", "商品编码", "商品名称", "颜色", "规格", "库存" , "成本价格" , "成本总价" , "所属货柜" , "打包方式","入库方式","备注"};
        // 进行查新（导出所有）
        List<Rctable> c = rc.selectKuserBysameting(rctable.getPnumber(),rctable.getSshg(),start,end);
        List<Object[]> dataList = new ArrayList<Object[]>();
        Object[] objs = null;
        for (int i = 0; i < c.size(); i++) {
            Rctable man = c.get(i);
            objs = new Object[rowsName.length];
            objs[0] =  man.getRcdate();
            objs[1] =  man.getPnumber();
            objs[2] =  man.getPname();
            objs[3] =  man.getColor();
            objs[4] =  man.getType();
            objs[5] =  man.getRccount();
            objs[6] =  man.getCostprice();
            objs[7] =  man.getCostcount();
            objs[8] =  man.getSshg();
            objs[9] =  man.getDbfs();
            objs[10] =  man.getRcfs();
            objs[11] =  man.getBz();
            dataList.add(objs);
        }
        ExportExcel ex = new ExportExcel("入库信息列表", rowsName, dataList);
        ex.setResponse(response);
        ex.export();

    }

    /**
     * 导出功能
     * @throws Exception
     */
    @RequestMapping(value = "/dc", method = RequestMethod.POST)
    public void aochuExcel(HttpServletResponse response,Container container) throws Exception {
    String[] rowsName = new String[] { "提单号", "运输方式", "联系方式", "POL", "POD" , "柜子尺寸"  , "代理费是否支付" , "类别" , "数量" , "货值" ,"船公司","出港时间","到港时间","代理公司","状态","补充说明"};
                // 进行查新（导出所有）
        List<Container> c = cm.selectKuserBysameting(container.getGnumber(),container.getBanknumber(),container.getPhone());
            List<Object[]> dataList = new ArrayList<Object[]>();
            Object[] objs = null;
            for (int i = 0; i < c.size(); i++) {
                Container man = c.get(i);
            objs = new Object[rowsName.length];
            objs[0] =  man.getGnumber();
            objs[1] =  man.getGysfs();
            objs[2] =  man.getPhone();
            objs[3] =  man.getPol();
            objs[4] =  man.getPod();
            objs[5] =  man.getGsize();
            objs[6] =  man.getDls();
            objs[7] =  man.getType();
            objs[8] =  man.getCount();
            objs[9] =  man.getPrice();
            objs[10] =  man.getChuanname();
            objs[11] =  man.getCgdate();
            objs[12] =  man.getDgdate();
            objs[13] =  man.getDlgs();
            objs[14] =  man.getStatus();
            objs[15] =  man.getContent();
            dataList.add(objs);
            }
            ExportExcel ex = new ExportExcel("货柜信息列表", rowsName, dataList);
            ex.setResponse(response);
            ex.export();

}



    /**
     * 进销存导出
     */
    @RequestMapping(value = "/jxcbfdc", method = RequestMethod.POST)
    public void jxcbfdc (HttpServletResponse response, Jxctable jxctable,@RequestParam  Map map) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String start = null;
        String end = null;
        if(map.get("start").toString().length()>0 && map.get("end").toString().length()>0) {
            try {
                start = sdf.format(sdf.parse(map.get("start").toString()));
                end = sdf.format(sdf.parse(map.get("end").toString()));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            };
        }
        String[] rowsName = new String[] {"商品编码", "商品名称", "规格", "颜色" , "期初数" , "入仓数量" , "出仓数量" , "结存条数","盘点条数","差异条数","盘点状态","时间"};
        // 进行查新（导出所有）
        List<Jxctablebf> c = jbf.selectKuserBysameting(start,end);
        List<Object[]> dataList = new ArrayList<Object[]>();
        Object[] objs = null;
        for (int i = 0; i < c.size(); i++) {
            Jxctablebf man = c.get(i);
            objs = new Object[rowsName.length];
            objs[0] =  man.getPnumber();
            objs[1] =  man.getPname();
            objs[2] =  man.getType();
            objs[3] =  man.getColor();
            objs[4] =  man.getQcs();
            objs[5] =  man.getRccount();
            objs[6] =  man.getCccount();
            objs[7] =  man.getJccount();
            objs[8] =  man.getPdcount();
            objs[9] =  man.getCycount();
            objs[10] =  man.getPdstatus();
            objs[11] =  man.getDate();
            dataList.add(objs);
        }
        ExportExcel ex = new ExportExcel("盘点信息列表", rowsName, dataList);
        ex.setResponse(response);
        ex.export();

    }
    /**
     * 进销存导出
     */
    @RequestMapping(value = "/jxcdc", method = RequestMethod.POST)
    public void jxcdc (HttpServletResponse response, Jxctable jxctable,@RequestParam  Map map) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String start = null;
        String end = null;
        if(map.get("start").toString().length()>0 && map.get("end").toString().length()>0) {
            try {
                start = sdf.format(sdf.parse(map.get("start").toString()));
                end = sdf.format(sdf.parse(map.get("end").toString()));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            };
        }
        String[] rowsName = new String[] {"商品编码", "商品名称", "规格", "颜色" , "期初数" , "入仓数量" , "出仓数量" , "结存条数","盘点条数","差异条数","盘点状态"};
        // 进行查新（导出所有）
        List<Jxctable> c = jxc.selectKuserBysameting(jxctable.getPnumber(),start,end);
        List<Object[]> dataList = new ArrayList<Object[]>();
        Object[] objs = null;
        for (int i = 0; i < c.size(); i++) {
            Jxctable man = c.get(i);
            objs = new Object[rowsName.length];
            objs[0] =  man.getPnumber();
            objs[1] =  man.getPname();
            objs[2] =  man.getType();
            objs[3] =  man.getColor();
            objs[4] =  man.getQcs();
            objs[5] =  man.getRccount();
            objs[6] =  man.getCccount();
            objs[7] =  man.getJccount();
            objs[8] =  man.getPdcount();
            objs[9] =  man.getCycount();
            objs[10] =  man.getPdstatus();
            dataList.add(objs);
        }
        ExportExcel ex = new ExportExcel("盘点信息列表", rowsName, dataList);
        ex.setResponse(response);
        ex.export();

    }



}
