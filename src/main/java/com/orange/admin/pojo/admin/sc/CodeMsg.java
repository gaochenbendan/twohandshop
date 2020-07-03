package com.orange.admin.pojo.admin.sc;

/**
 * 状态类
 */

public class CodeMsg {

    private int code;
    private String msg;

    private CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    //    登录成功
    public static CodeMsg SUCCESS = new CodeMsg(0, "success");
    public static CodeMsg SUCCESS_LOGIN = new CodeMsg(0, "登录成功");
    //    非法数据
    public static CodeMsg DATA_ERROR = new CodeMsg(-1, "亲，非法数据哦~");
    //   验证码为空
    public static CodeMsg CPACHA_EMPTY = new CodeMsg(-2, "亲，验证码不能为空哦~~");
    public static CodeMsg VALIDATE_ERROR = new CodeMsg(-3, "");
    public static CodeMsg CPACHA_TIMEOUT = new CodeMsg(-4, "验证码验证超时！请刷新");

    //   后台管理类错误码
    public static CodeMsg ADMIN_USERNAME_EMPTY = new CodeMsg(-2000, "亲,用户名为空~");
    public static CodeMsg ADMIN_PASSWORD_EMPTY = new CodeMsg(-2001, "亲，密码为空~");
    public static CodeMsg ADMIN_CPACHA_EMPTY = new CodeMsg(-2002, "亲，验证码错误哦~");

    //  登录类错误码
    public static CodeMsg USERNAME_NO_IS_EXIT = new CodeMsg(-3000, "亲,用户名不存在···");
    public static CodeMsg PASSWORD_ERROR = new CodeMsg(-3001, "亲,密码错误哦~~");
    public static CodeMsg USER_UNABLE = new CodeMsg(-3002, "亲,用户被冻结了哦~~");
    public static CodeMsg USER_ROLE_UNABLE = new CodeMsg(-3002, "亲,用户角色被冻结了哦~~");
    public static CodeMsg OLD_PWD_ERROR = new CodeMsg(-3003, "亲,旧密码不对哦~~");
    public static CodeMsg NEW_EMPTY = new CodeMsg(-3004, "亲,新密码不能为空~~");

    // 权限管理类
    public static CodeMsg ADMIN_NO_RIGHT = new CodeMsg(-18, "亲,没有权限~~");

    //  通用错误码
    public static CodeMsg USER_SESSION_EXPIRED = new CodeMsg(-6, "还未登录或者会话失效~~");
    //    后台菜单管理
    public static CodeMsg ADD_MENU_ERROR = new CodeMsg(-7, "menu创建失败");
    public static CodeMsg DELETE_MENU_ERROR = new CodeMsg(-8, "下面有子菜单不允许删除哦~~");

    public static CodeMsg SAVE_ROLE_ERROR = new CodeMsg(-9, "亲~添加失误~~");
    public static CodeMsg ADMIN_ROLE_NO_EXIST = new CodeMsg(-10, "亲~修改失误~~");
    public static CodeMsg ADMIN_ROLE_DELETE_ERROR = new CodeMsg(-11, "亲~删除失误~~用户下有角色~~");
    //  图片上传
    public static CodeMsg PHOTO_ADD_ERROR = new CodeMsg(-12, "图片上传失败！！");
    public static CodeMsg PHOTO_ADD_ERROR_MAX = new CodeMsg(-13, "图片过大！！");
    //  用户管理
    public static CodeMsg USER_ADD_ROLE_ERROR = new CodeMsg(-14, "添加角色管理错误！！");
    public static CodeMsg USER_NAME_HAVE_EXITS = new CodeMsg(-15, "用户名已经存在了~~");
    public static CodeMsg ADD_USER_ERROR = new CodeMsg(-16, "保存用户失败~~");
    public static CodeMsg DELETE_USER_ERROR = new CodeMsg(-17, "删除用户失败~~");
    //  日志管理
    public static CodeMsg LOG_DELETE_NULL = new CodeMsg(-18, "请选择删除日志~~");
    //  数据库备份
    public static CodeMsg DATABASE_BAK_ID_EMPTY = new CodeMsg(-19, "请选择要删除的备份文件~~");
    public static CodeMsg DATABASE_BAK_SAVE_ERROR = new CodeMsg(-20, "删除失败~~");
    public static CodeMsg RESTORE_FILE_NOt_FIND = new CodeMsg(-21, "恢复文件失效~~");
    //  商品菜单
    public static CodeMsg GOODERCATEGORY_ADD_NULL = new CodeMsg(-22, "数据为空~~");
    public static CodeMsg GOODERCATEGORY_ADD_ERROR = new CodeMsg(-23, "添加失败~~");
    public static CodeMsg GOODERCATEGORY_EDIT_ERROR = new CodeMsg(-24, "编辑失败~~");
    public static CodeMsg GOODERCATEGORY_DELETE_ERROR = new CodeMsg(-26, "删除失败~~");
    public static CodeMsg GOODERCATEGORY_DELETE_ERROR_CHILREN = new CodeMsg(-24, "菜单下有子分类~~");
    // 用户
    public static CodeMsg CUNSTOM_CN_ERROR = new CodeMsg(-25, "学号已存在~~");
    public static CodeMsg CUNSTOM_CN_ADD_ERROR = new CodeMsg(-26, "添加失败~~");
    public static CodeMsg HOME_STUDENT_REGISTER_SN_EXIST = new CodeMsg(-27, "用户名不存在~~");
    public static CodeMsg HOME_STUDENT_SN_NO_EXIST = new CodeMsg(-28, "添加失败~~");
    public static CodeMsg HOME_STUDENT_PASSWORD_ERROR = new CodeMsg(-28, "密码错误~~");
    public static CodeMsg HOME_STUDENT_UNABLE = new CodeMsg(-29, "用户被冻结~~");
    public static CodeMsg HOME_STUDENT_ADD_ERROR = new CodeMsg(-30, "用户保存失败~~");
    public static CodeMsg HOME_STUDENT_ADD_HEADERPIC_ERROR = new CodeMsg(-31, "头像保存失败~~");


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static CodeMsg getSUCCESS() {
        return SUCCESS;
    }

    public static CodeMsg getCpachaEmpty() {
        return CPACHA_EMPTY;
    }

    public static void setCpachaEmpty(CodeMsg cpachaEmpty) {
        CPACHA_EMPTY = cpachaEmpty;
    }

    public static CodeMsg getValidateError() {
        return VALIDATE_ERROR;
    }

    public static void setValidateError(CodeMsg validateError) {
        VALIDATE_ERROR = validateError;
    }

    public static CodeMsg getAdminPasswordEmpty() {
        return ADMIN_PASSWORD_EMPTY;
    }

    public static void setAdminPasswordEmpty(CodeMsg adminPasswordEmpty) {
        ADMIN_PASSWORD_EMPTY = adminPasswordEmpty;
    }

    public static void setSUCCESS(CodeMsg SUCCESS) {
        CodeMsg.SUCCESS = SUCCESS;
    }

    public static CodeMsg getDataError() {
        return DATA_ERROR;
    }

    public static void setDataError(CodeMsg dataError) {
        DATA_ERROR = dataError;
    }

    public static CodeMsg getAdminUsernameEmpty() {
        return ADMIN_USERNAME_EMPTY;
    }

    public static void setAdminUsernameEmpty(CodeMsg adminUsernameEmpty) {
        ADMIN_USERNAME_EMPTY = adminUsernameEmpty;
    }
}
