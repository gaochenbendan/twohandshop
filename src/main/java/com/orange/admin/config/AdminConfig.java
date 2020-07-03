package com.orange.admin.config;

import com.orange.admin.Interceptor.admin.AuthorityInterceptor;
import com.orange.admin.Interceptor.admin.loginInterceptor;
import com.orange.admin.commons.constant.RuntimeConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置拦截器的文件
 * @author 高晨
 */
@Configuration
public class AdminConfig implements WebMvcConfigurer {

    @Autowired
    private loginInterceptor interceptor;
    @Autowired
    private AuthorityInterceptor authorityInterceptor;



    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(RuntimeConstant.loginExcludePathPatterns);

        registry.addInterceptor(authorityInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(RuntimeConstant.authorityExcludePathPatterns);
    }
}
