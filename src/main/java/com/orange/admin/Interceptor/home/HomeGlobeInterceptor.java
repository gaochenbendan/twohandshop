package com.orange.admin.Interceptor.home;

import com.orange.admin.commons.utils.StringUtil;
import com.orange.admin.service.adminservice.GoodsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 前台全局拦截器
 *
 * @author 高晨
 */
@Component
public class HomeGlobeInterceptor implements HandlerInterceptor {

    @Autowired
    private GoodsCategoryService goodsCategoryService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        StringBuffer requestURL = request.getRequestURL();


        if(!StringUtil.isAjax(request)){
            //若不是ajax请求，则将菜单信息放入页面模板变量
            request.setAttribute("goodsCategorys",goodsCategoryService.findAll());
        }



        return true;
    }
}
