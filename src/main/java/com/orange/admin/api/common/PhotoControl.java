package com.orange.admin.api.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/photo")
public class PhotoControl {

    @Value("${orange.upload.photo.upload_path}")
    private String uploadPath;
    @Value("${orange.upload.photo.upload_path_icon}")
    private String uploadPathIcon;

    @Value("${orange.upload.photo.upload_path_customer}")
    private String upload_path_customer;
    @Autowired
    private ResourceLoader resourceLoader;

    /**
     * 图片查看
     * @param filename
     * @return
     */
    @RequestMapping(value = "/view")
    @ResponseBody
    public ResponseEntity<?> viewPhoto(@RequestParam(name = "filename",required = true) String filename) throws UnsupportedEncodingException {


//        文件绝对路径
        String filepath  = "file:"+ uploadPath +"\\"+ filename;

//          获取图片
        Resource resource = resourceLoader.getResource(filepath);
        try {
            ResponseEntity<Resource> ok = ResponseEntity.ok(resource);

            return ResponseEntity.ok(resource);
        }catch (Exception e)
        {
            return ResponseEntity.notFound().build();
        }

    };

    @RequestMapping(value = "/view_icon")
    @ResponseBody
    public ResponseEntity<?> viewIcon(@RequestParam(name = "filename",required = true) String filename) throws UnsupportedEncodingException {



//        文件绝对路径
        String filepath  = "file:"+ uploadPathIcon +"\\"+ filename;
//          获取图片
        Resource resource = resourceLoader.getResource(filepath);
        try {
            ResponseEntity<Resource> ok = ResponseEntity.ok(resource);

            return ResponseEntity.ok(resource);
        }catch (Exception e)
        {
            return ResponseEntity.notFound().build();
        }

    };

    @RequestMapping(value = "/view_cunstomer")
    @ResponseBody
    public ResponseEntity<?> view_cunstomer(@RequestParam(name = "filename",required = true) String filename) throws UnsupportedEncodingException {


//        文件绝对路径
        String filepath  = "file:"+ upload_path_customer +"\\"+ filename;

//          获取图片
        Resource resource = resourceLoader.getResource(filepath);
        try {
            ResponseEntity<Resource> ok = ResponseEntity.ok(resource);

            return ResponseEntity.ok(resource);
        }catch (Exception e)
        {
            return ResponseEntity.notFound().build();
        }

    };


}
