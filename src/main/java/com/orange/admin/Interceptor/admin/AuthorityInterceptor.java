package com.orange.admin.Interceptor.admin;

import com.alibaba.fastjson.JSON;
import com.orange.admin.commons.utils.MenuUtil;
import com.orange.admin.commons.utils.StringUtil;
import com.orange.admin.pojo.admin.Menu;
import com.orange.admin.pojo.admin.UserMessage;
import com.orange.admin.pojo.admin.sc.CodeMsg;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * 权限统一管理拦截器
 * @author 高晨
 */
@Component
public class AuthorityInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        System.out.print(requestURI);
        HttpSession session = request.getSession();
        UserMessage user =(UserMessage) session.getAttribute("user");
        List<Menu> authorities = user.getRole().getAuthorities();
        if(!MenuUtil.isExistUrl(requestURI,authorities)){
            //进入这里，表示权限不存在，首先判断是否是ajax请求
            if(StringUtil.isAjax(request)){
                //表示是ajax请求
                try {
                    System.out.println(requestURI);
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(JSON.toJSONString(CodeMsg.ADMIN_NO_RIGHT));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return false;

            }

            //说明是普通的请求，可直接重定向到无权限提示页面
            try {
                System.out.println("该请求无权限，重定向到无权限提示页面，url=" + requestURI);
                response.sendRedirect("/system/noright");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
        if(requestURI.equals("/"))
        {
            response.sendRedirect("/system/index");
            return false;
        }

        System.out.println("该请求符合权限要求，放行" + requestURI);
        return true;
    }
}
