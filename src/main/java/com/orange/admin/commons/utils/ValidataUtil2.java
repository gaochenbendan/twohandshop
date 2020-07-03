package com.orange.admin.commons.utils;

import com.orange.admin.annotion.ValidateAnnotion;
import com.orange.admin.pojo.admin.sc.CodeMsg;

import java.lang.reflect.Field;


/**
 * 验证实体工具类
 */
public class ValidataUtil2 {

    public static CodeMsg validate(Object object){
        Field[] declaredFields = object.getClass().getDeclaredFields();
        for(Field field : declaredFields){
            ValidateAnnotion annotation = field.getAnnotation(ValidateAnnotion.class);
            if(annotation != null){
                if(annotation.required()){
                    //表示该字段是必填字段
                    field.setAccessible(true);
                    try {
                        Object o = field.get(object);
                        //首先判断是否为空
                        if(o == null){
                            CodeMsg codeMsg = CodeMsg.VALIDATE_ERROR;
                            codeMsg.setMsg(annotation.errorRequiredMsg());
                            return codeMsg;
                        }
                        //到这，说明该变量的值不是null
                        //首先判断是不是String类型
                        if(o instanceof String){
                            //若是字符串类型，则检查其长度
                            if(annotation.requiredLength()){
                                if(o.toString().length() < annotation.minLength()){
                                    CodeMsg codeMsg = CodeMsg.VALIDATE_ERROR;
                                    codeMsg.setMsg(annotation.errorMinlenthMsg());
                                    return codeMsg;
                                }
                                if(o.toString().length() > annotation.maxLength()){
                                    CodeMsg codeMsg = CodeMsg.VALIDATE_ERROR;
                                    codeMsg.setMsg(annotation.errorMaxlenthMsg());
                                    return codeMsg;
                                }
                            }
                        }
                        //其次来判断是否为数字
                        if(isNumberObject(o)){
                            //判断是否规定检查最小值
                            if(annotation.requiredMinValue()){
                                if(Double.parseDouble(o.toString()) < annotation.minValue()){
                                    CodeMsg codeMsg = CodeMsg.VALIDATE_ERROR;
                                    codeMsg.setMsg(annotation.errorMinValueMsg());
                                    return codeMsg;
                                }
                            }
                            //判断是否规定检查最大值
                            if(annotation.requiredMaxValue()){
                                if(Double.parseDouble(o.toString()) > annotation.maxValue()){
                                    CodeMsg codeMsg = CodeMsg.VALIDATE_ERROR;
                                    codeMsg.setMsg(annotation.errorMaxValueMsg());
                                    return codeMsg;
                                }
                            }
                        }
                    } catch (IllegalArgumentException | IllegalAccessException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }
        return CodeMsg.SUCCESS;
    }

    /**
     * 检查对象是否是数字类型
     * @param object
     * @return
     */
    public static boolean isNumberObject(Object object){
        if(object instanceof Integer)return true;
        if(object instanceof Long)return true;
        if(object instanceof Float)return true;
        if(object instanceof Double)return true;
        return false;
    }
}
