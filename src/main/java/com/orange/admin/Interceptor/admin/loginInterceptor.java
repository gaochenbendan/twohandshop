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
import java.util.List;

/**
 * 登录拦截器
 */
@Component
public class loginInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");
        if(user == null)
        {
            //首先判断是否是ajax请求 ---AJAX不能重定向
            if(StringUtil.isAjax(request)){
                //表示是ajax请求
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(JSON.toJSONString(CodeMsg.USER_SESSION_EXPIRED));
                return false;
            }

            response.sendRedirect("/system/login");
            return false;



        }
        if(!StringUtil.isAjax(request)){
            //若不是ajax请求，则将菜单信息放入页面模板变量
            UserMessage user2 = (UserMessage)user;

            List<Menu> authorities = user2.getRole().getAuthorities();

            request.setAttribute("userTopMenus", MenuUtil.getTopMenus(authorities));
            List<Menu> secondMenus = MenuUtil.getSecondMenus(user2.getRole().getAuthorities());
            request.setAttribute("userSecondMenus", secondMenus);
            request.setAttribute("userThirdMenus", MenuUtil.getChildren(MenuUtil.getMenuIdByUrl(requestURI,secondMenus),authorities));
//            request.setAttribute("siteName", siteConfig.getSiteName());
//            request.setAttribute("siteUrl", siteConfig.getSiteUrl());
        }
        return true;
    }
}
