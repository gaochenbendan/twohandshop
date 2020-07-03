package com.orange.admin.config;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceLogAspect {

    private static Logger logger = LoggerFactory.getLogger(ServiceLogAspect.class);

    @Around("execution(* com.orange.admin.service.*.*(..)  )")
           public Object recordTimeLog(ProceedingJoinPoint point) throws Throwable {
            logger.info("==========开始{}.{}============",
                    point.getClass(),
                    point.getSignature().getName());
            long begin = System.currentTimeMillis();

            Object proceed = point.proceed();

        long end = System.currentTimeMillis();

        long takeTime = end-begin;
        if(takeTime > 3000)
        {
            logger.error("执行结束耗时:{}",takeTime);
        }else if(takeTime > 2000)
        {
            logger.warn("执行结束耗时:{}",takeTime);
        }else {
            logger.info("执行结束耗时:{}",takeTime);
        }

        return proceed;


    }
}