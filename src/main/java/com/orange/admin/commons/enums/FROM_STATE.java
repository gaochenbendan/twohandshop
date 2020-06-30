package com.orange.admin.commons.enums;

/**
 * @author 33040
 */

public enum  FROM_STATE {

    ALREADY_EXIST("用户名存在", 6),
    NO_EXIST("非法数据", -1),
    NO_USERNAME("用户名不存在",2),
    PASSWORD_ERROR("密码错误",3),
    CPACHA_ERROR("验证码错误",4),
    FAIL_LOGIN("登录失败",5),
    USERNAME_EMPTY("用户名不能为空",-2000),
    SUCCESS_LOGIN("登录成功",0);
    // 成员变量
    private String msg;
    private int code;
    // 构造方法


    FROM_STATE(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code+"";
    }

    public void setCode(int code) {
        this.code = code;
    }
}
