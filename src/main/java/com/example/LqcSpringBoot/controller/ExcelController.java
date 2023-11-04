package com.example.LqcSpringBoot.controller;


import com.example.LqcSpringBoot.mapper.CcMapper;
import com.example.LqcSpringBoot.mapper.ContainerMapper;
import com.example.LqcSpringBoot.mapper.RcMapper;
import com.example.LqcSpringBoot.model.Cctable;
import com.example.LqcSpringBoot.model.Rctable;
import com.example.LqcSpringBoot.ut.ExportExcel;
import com.example.LqcSpringBoot.ut.MainPartimportBean;
import com.gaoice.easyexcel.spring.boot.autoconfigure.annotation.ResponseExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.example.LqcSpringBoot.model.Container;
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
    public void ccdc (HttpServletResponse response, Cctable cctable, Map map) throws Exception{
        String[] rowsName = new String[] { "出库时间", "订单编号","商品编码", "商品名称", "颜色", "规格", "出库数量" , "单价" , "总价" , "客户姓名" , "客户电话", "客户地址", "备注"};
        // 进行查新（导出所有）
        List<Cctable> c = cc.selectKuserBysameting(cctable.getOrderid(),cctable.getCustomerphone(),"","");
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
            objs[10] =  man.getCustomeradddress();
            objs[11] =  man.getBz();
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
    public void rcdc (HttpServletResponse response, Rctable rctable, Map map) throws Exception{
        String[] rowsName = new String[] { "入库时间", "商品编码", "商品名称", "颜色", "规格", "库存" , "成本价格" , "成本总价" , "所属货柜" , "备注"};
        // 进行查新（导出所有）
        List<Rctable> c = rc.selectKuserBysameting(rctable.getPnumber(),rctable.getSshg(),"","");
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
            objs[9] =  man.getBz();
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
    String[] rowsName = new String[] { "柜号", "打包方式", "运输方式", "联系方式", "付款银行", "银行账号" , "柜子尺寸" , "出港时间" , "到港时间" , "状态" , " 代理费是否支付" , "类别" , "数量" , "货值" ,"内容"};
                // 进行查新（导出所有）
        List<Container> c = cm.selectKuserBysameting(container.getGnumber(),container.getBanknumber(),container.getPhone());
            List<Object[]> dataList = new ArrayList<Object[]>();
            Object[] objs = null;
            for (int i = 0; i < c.size(); i++) {
                Container man = c.get(i);
            objs = new Object[rowsName.length];
            objs[0] =  man.getGnumber();
            objs[1] =  man.getGdbfs();
            objs[2] =  man.getGysfs();
            objs[3] =  man.getPhone();
            objs[4] =  man.getBankname();
            objs[5] =  man.getBanknumber();
            objs[6] =  man.getGsize();
            objs[7] =  man.getCgdate();
            objs[8] =  man.getDgdate();
            objs[9] =  man.getStatus();
            objs[10] =  man.getDls();
            objs[11] =  man.getType();
            objs[12] =  man.getCount();
            objs[13] =  man.getPrice();
            objs[14] =  man.getContent();
            dataList.add(objs);
            }
            ExportExcel ex = new ExportExcel("货柜信息列表", rowsName, dataList);
            ex.setResponse(response);
            ex.export();

}




}
