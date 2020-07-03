package com.orange.admin.api.common;

import com.orange.admin.commons.utils.CpachaUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 系统验证公用
 */
@Controller
@RequestMapping("/cpacha")
public class CpachaControl {

    /**
     * 验证码
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/generate_cpacha",method = RequestMethod.GET)
    public void generateCpacha(@RequestParam(name = "vl",defaultValue = "4") Integer vcodelenth, // 验证码长度
                               @RequestParam(name = "fs",defaultValue = "21")Integer fontsize, // 验证码字体大小
                               @RequestParam(name = "w",defaultValue = "98")Integer width, // 图片宽度
                               @RequestParam(name = "h",defaultValue = "33")Integer heigh, // 图片高度
                               @RequestParam(name = "m",defaultValue = "admin_login")String method, // 用来调用此放方法的的名称，为健存入到SESSION中
                               HttpServletRequest request,
                               HttpServletResponse response) throws IOException {
        CpachaUtil cpachaUtil = new CpachaUtil(vcodelenth,fontsize,width,heigh);
        String generatorVCode = cpachaUtil.generatorVCode();

        System.out.println(generatorVCode);
        /**
         * 这个session一定要写到这个位置 否则发布出去
         */

        if(request.getSession().getAttribute(method)!= null)
        {
            request.getSession().setAttribute(method,null);

        }
            request.getSession().setAttribute(method,generatorVCode);

        ImageIO.write(
                cpachaUtil.generatorRotateVCodeImage(generatorVCode,true),
                "gif",response.getOutputStream()
        );
        // 生成的代码让入session


    }

}
