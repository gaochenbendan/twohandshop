package com.orange.admin.config;

import com.orange.admin.Interceptor.home.HomeGlobeInterceptor;
import com.orange.admin.Interceptor.home.HomeLoginInterceptor;
import com.orange.admin.commons.constant.RuntimeConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 盖晨
 */
@Configuration
public class HomeConfig implements WebMvcConfigurer {

    @Autowired
    private HomeLoginInterceptor homeLoginInterceptor;

    @Autowired
    private HomeGlobeInterceptor globeInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(globeInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(RuntimeConstant.honeExcludePathPatterns);
        registry.addInterceptor(homeLoginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(RuntimeConstant.homeLoginExcludePathPatterns);

    }


}
