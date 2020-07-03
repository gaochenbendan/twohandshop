package com.orange.admin.Interceptor.admin;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * session监听类 监听创建和销毁
 */
@WebListener
public class HttpSessionListion implements HttpSessionListener {

    public static Long onlineUserCoutent = 0L;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        ++onlineUserCoutent;
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

        if(onlineUserCoutent != 0){
            --onlineUserCoutent;
        }
    }
}
