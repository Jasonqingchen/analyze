package com.example.LqcSpringBoot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Controller
@RequestMapping("/excel")
public class DownloadExcelFile {
    /**
     * 下载excel模板
     */
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public static void remoteFileDownload(HttpServletResponse response) {
        String filePath="http://1.12.238.58:8089/excel/%E6%B5%8B%E8%AF%95%E6%A8%A1%E6%9D%BF.xlsx";
        OutputStream outputStream = null;
        InputStream inputStream = null;
        URL url;
        try {
            url = new URL(filePath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();//利用HttpURLConnection对象,我们可以从网络中获取网页数据.
            conn.setDoInput(true);
            conn.connect();
            int contentLength = conn.getContentLength();
            inputStream = conn.getInputStream();
            // 写明要下载的文件的大小
            response.setContentLength(contentLength);
            response.setHeader("Content-Disposition", "attachment;filename="+ filePath);// 设置在下载框默认显示的文件名
            response.setContentType("application/octet-stream");// 指明response的返回对象是文件流
            outputStream = response.getOutputStream();

            byte[] buf = new byte[1024];
            int len = 0;
            while((len = inputStream.read(buf))!=-1) {
                outputStream.write(buf,0,len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(outputStream !=null) {
                    outputStream.close();
                }
                if(inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
