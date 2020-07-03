package com.orange.admin.api.home;

import com.orange.admin.pojo.admin.bo.Result;
import com.orange.admin.pojo.admin.sc.CodeMsg;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RequestMapping("/home/upload")
@Controller
public class HomeUploadController {

    @Value("${orange.upload.photo.sufix}")
    private String upload;
    @Value("${orange.upload.photo.maxsize}")
    private Long upload_maxsize;
    @Value("${orange.upload.photo.upload_path}")
    private String upload_path;
    @Value("${orange.upload.photo.upload_path_customer}")
    private String upload_path_customer;


    @RequestMapping(value="/upload_photo",method= RequestMethod.POST)
    @ResponseBody
    public Result<String> uploadPhoto(@RequestParam(name="photo",required=true) MultipartFile photo){

        //       获取文件原始名
        String originalFilename = photo.getOriginalFilename();

//        获取文件后缀
        assert originalFilename != null;
        String substring = originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());

        if(!upload.contains(substring))
        {
            return Result.errot(CodeMsg.PHOTO_ADD_ERROR);
        }
//        判断大小
        if(photo.getSize()/1024 > upload_maxsize)
        {
            CodeMsg photoAddErrorMax = CodeMsg.PHOTO_ADD_ERROR_MAX;
            photoAddErrorMax.setMsg("图片大小不能超过"+(upload_maxsize/1024)+"kb");
            return Result.errot(photoAddErrorMax);
        }
//        保存文件
        File filepath = new File(upload_path_customer);
//        判断文件是否存在
        if(!filepath.exists())
        {
            filepath.mkdir();
        }

        String fileName =System.currentTimeMillis() + substring;
//        爆存文件
        try {
            photo.transferTo(new File(upload_path_customer+"/"+fileName));
        }catch (Exception e)
        {
            System.out.println(e);
        }
        System.out.println(fileName);
        return Result.success(fileName);
    }


    }
