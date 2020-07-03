package com.orange.admin.pojo.admin.vo;

import com.orange.admin.annotion.ValidateAnnotion;

public class UserVo {

    @ValidateAnnotion(
            required=true,
            requiredLength=true,
            minLength=4,
            maxLength=18,
            errorRequiredMsg="亲，用户名不能为空哦~~",
            errorMinlenthMsg="亲，用户名长度需大于4哦~~!",
            errorMaxlenthMsg="亲，用户名长度不能大于18哦~~!")
    private String username;
    @ValidateAnnotion(
            required=true,
            requiredLength=true,
            minLength=6,
            maxLength=18,
            errorRequiredMsg="亲，密码不能为空哦~~!",
            errorMinlenthMsg="亲，密码长度需大于6哦~~!",
            errorMaxlenthMsg="亲，密码长度不能大于18o~~!")
    private String password;

    @Override
    public String toString() {
        return "UserVo{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
