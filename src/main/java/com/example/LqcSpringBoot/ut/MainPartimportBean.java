package com.example.LqcSpringBoot.ut;

import com.example.LqcSpringBoot.mapper.KuserMapper;
import com.example.LqcSpringBoot.model.Kuser;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import static java.lang.Integer.parseInt;

/**
 * @author：liuqingchen
 * @since：2022/09/08
 */
@Component
public class MainPartimportBean {

    @Autowired
    public KuserMapper kmapper;

    public  void insertDB(InputStream fileInputStream) {
        try {
            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);// 创建工作薄
            Sheet sheet = workbook.getSheetAt(0);// 得到工作表
            Row row = null;// 对应excel的行
            Cell cell = null;// 对应excel的列
            String Var="";
            row = sheet.getRow((short)0);


            int totalRow = sheet.getLastRowNum();// 得到excel的总记录条数
            String name = "";//第1列
            String sex = "";//第2列
            String num = "";//第3列
            String dyname = "";//4列
            String phone = "";//第5列
            String cm = "";//第7列
            int x =0;
            for (short i = 1; i <=totalRow; i++) {
                Kuser mp1 = new Kuser();
                cell = sheet.getRow(i).getCell((short)0);
                if(cell!=null){
                    sheet.getRow(i).getCell(0).setCellType(CellType.STRING);
                    name = sheet.getRow(i).getCell(0).getStringCellValue();
                    mp1.setName(name);
                }
                cell = sheet.getRow(i).getCell((short)1);
                if(cell!=null){
                    sheet.getRow(i).getCell(1).setCellType(CellType.STRING);
                    sex =sheet.getRow(i).getCell(1).getStringCellValue();
                    mp1.setSex(sex);

                }
                cell = sheet.getRow(i).getCell((short)2);
                if(cell!=null){
                    sheet.getRow(i).getCell(2).setCellType(CellType.STRING);
                    num =sheet.getRow(i).getCell(2).getStringCellValue();
                    if (parseInt(num) <10) {
                        num="0"+num;
                    }
                    mp1.setNum(num);
                }
                cell = sheet.getRow(i).getCell((short)3);
                if(cell!=null){
                    sheet.getRow(i).getCell(3).setCellType(CellType.STRING);
                    dyname =sheet.getRow(i).getCell(3).getStringCellValue();
                    mp1.setDyname(dyname);
                }
                cell = sheet.getRow(i).getCell((short)4);
                if(cell!=null){
                    sheet.getRow(i).getCell(4).setCellType(CellType.STRING);
                    phone =sheet.getRow(i).getCell(4).getStringCellValue();
                     mp1.setPhone(phone);
                }
                cell = sheet.getRow(i).getCell((short)5);
                if(cell!=null){
                    sheet.getRow(i).getCell(5).setCellType(CellType.STRING);
                    cm =sheet.getRow(i).getCell(5).getStringCellValue();
                     mp1.setCm(cm);
                }

                List<Kuser> list = kmapper.selectKuserByNum(num);
                if (list.size()==0) {
                    mp1.setId(UUID.randomUUID().toString());
                    kmapper.insert(mp1);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
