package com.orange.admin.api.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 高晨
 */
@Controller
@RequestMapping("/home/index")
public class IndexController {


    /**
     * 登录首页
     * @return
     */
    @RequestMapping("/index")
    public String index()
    {
        return "home/index/index";

    }




}
