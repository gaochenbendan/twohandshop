package com.orange.admin.Interceptor.home;

import com.alibaba.fastjson.JSON;
import com.orange.admin.commons.utils.StringUtil;
import com.orange.admin.pojo.admin.sc.CodeMsg;
import com.orange.admin.pojo.common.Customer;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 前台登录
 */
@Component
public class HomeLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        HttpSession session = request.getSession();
        Customer customer = (Customer)session.getAttribute("CustomerL");
        if(customer == null)
        {
            //首先判断是否是ajax请求 ---AJAX不能重定向
            if(StringUtil.isAjax(request)){
                //表示是ajax请求
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(JSON.toJSONString(CodeMsg.USER_SESSION_EXPIRED));
                return false;
            }

            response.sendRedirect("/home/index/login");
            return false;



        }

        System.out.println("符合要求URL:"+requestURI);
        return true;
    }
}
