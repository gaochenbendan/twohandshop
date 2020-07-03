package com.orange.admin.commons.utils;

import com.orange.admin.pojo.admin.UserMessage;
import com.orange.admin.pojo.common.Customer;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * session 工具类
 *
 *
 */
public class SessionUtil {

    private final static String loginUser = "user";
    private final static String loginCustomer = "CustomerL";

    /**
     * 获取请求request
     * @return
     */
    public static HttpServletRequest getRequest(){
        ServletRequestAttributes attributes =(ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        return attributes == null ? null : attributes.getRequest();
    }

    /**
     * 获取session
     * @return
     */
    public static HttpSession getSession(){
        HttpServletRequest request = getRequest();
        if(request != null){
            return request.getSession();
        }
        return null;
    }

    /**
     * 获取指定键的值
     * @param key
     * @return
     */
    public static Object get(String key){
        HttpSession session = getSession();
        if(session != null){
            return session.getAttribute(key);
        }
        return null;
    }

    /**
     * 设置session值
     * @param key
     * @param object
     */
    public static void set(String key,Object object){
        HttpSession session = getSession();
        if(session != null){
            session.setAttribute(key,object);
        }
    }

    /**
     * 消除指定的session
     * @param key
     */
    public static void remove(String key){
        HttpSession session = getSession();
        if(session != null){
            session.removeAttribute(key);
        }
    }

    /**
     * 消除全部的session
     */
    public static void removeAll(){
        HttpSession session = getSession();
        if(session != null){
            session.invalidate();
        }
    }


    /**
     * 获取当前登录的用户
     * @return
     */
    public static UserMessage getLoginedUser(){
        HttpSession session = getSession();
        if(session != null){
            Object attribute = session.getAttribute(loginUser);
            // 放入需要转换的类型
            return attribute == null ? null : (UserMessage)attribute;
        }
        return null;
    }
    public static Customer getLoginedCustomer(){
        HttpSession session = getSession();
        if(session != null){
            Object attribute = session.getAttribute(loginCustomer);
            // 放入需要转换的类型
            return attribute == null ? null : (Customer)attribute;
        }
        return null;
    }





}
