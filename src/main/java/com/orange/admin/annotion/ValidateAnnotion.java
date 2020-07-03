package com.orange.admin.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 验证实体
 * 自定义注解类
 * 根据自定义的注解去检查实体是否在规定的值内
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateAnnotion {
//  是否检验
    public boolean required() default false;
//  最大长度
    public int maxLength() default  -1;
//    最大值
    public long maxValue() default 0;
//  最小长度
    public int minLength() default  -1;
//    最小值
    public long minValue() default 0;
//    是否检验长度
    public boolean requiredLength() default false;
//    是否检验最大值
    public boolean requiredMaxValue() default false;
//    是否检验最小值
    public boolean requiredMinValue() default false;

    public String errot_msg() default "" ;
//  值为null时的错误信息
    public String errorRequiredMsg() default "";
//  最小长度不满足的错误信息
    public String errorMinlenthMsg() default "";
//  最大长度不满足的错误信息
    public String errorMaxlenthMsg() default "";
//  最大值不满足的错误信息
    public String errorMaxValueMsg() default "";
//  最小值不满足的错误信息
    public String errorMinValueMsg() default "";
}
