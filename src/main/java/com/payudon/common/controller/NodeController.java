package com.payudon.common.controller;

import com.payudon.gui.HttpSendJFrame;
import com.payudon.util.FileUtil;
import com.payudon.util.TarUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;

/**
 * @ClassName NodeController
 * @Description TODO
 * @Author pay
 * @DATE 2019/5/14 10:29
 **/
@RestController
@RequestMapping("/node")
public class NodeController {
    Logger logger = LoggerFactory.getLogger (this.getClass ());
    @RequestMapping("/upload")
    public void upload(HttpServletRequest request, HttpServletResponse response){
        OutputStream out;
        InputStream in;
        try{
            MultipartHttpServletRequest mhsRequest = (MultipartHttpServletRequest) request;
            Iterator<String> iter = mhsRequest.getFileNames();
            String fileName = "";
            while(iter.hasNext()) {
                fileName = iter.next();
            }
            MultipartFile mfile= mhsRequest.getFile(fileName);
            String tarPath = FileUtil.copyFile(mfile.getInputStream(), "D:\\tar\\test\\get", mfile.getOriginalFilename());
            String result = "收到tar包,已保存至："+tarPath;
            HttpSendJFrame.showResult (result);
            String outPath = "D:\\tar\\test\\out";
            tarPath = TarUtil.getTarByPath(tarPath,outPath).get("tarPath").toString();
            out = response.getOutputStream();
            in = new FileInputStream (new File (tarPath));
            FileUtil.wirteFile(in, out);
            FileUtil.delete(tarPath);
        }catch (Exception e){
            HttpSendJFrame.showResult (e.getMessage ());
            logger.error (e.getMessage (),e);
        }
    }
}
