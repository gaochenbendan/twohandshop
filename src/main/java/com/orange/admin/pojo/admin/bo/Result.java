package com.orange.admin.pojo.admin.bo;

import com.orange.admin.pojo.admin.sc.CodeMsg;

/**
 * 输出模板类
 * @param <T>
 */
public class Result<T> {

    private int code;

    private String msg;

    private T data;

    private  Result() {
    }

    private Result(CodeMsg codeMsg){
        if(codeMsg != null)
        {
            this.code = codeMsg.getCode();
            this.msg = codeMsg.getMsg();
        }
    }

    private  Result(T data,CodeMsg codeMsg){
        if(codeMsg!= null)
        {
            this.code = codeMsg.getCode();
            this.msg = codeMsg.getMsg();

        }
        this.data = data;
    }

    /**
     * 成功返回
     * @param data
     * @param <T>
     * @return
     */
    public static <T>Result<T> success(T data)
    {
        return new Result<T>(data,CodeMsg.SUCCESS_LOGIN);
    }

    /**
     * 返回失败
     * @param codeMsg
     * @param <T>
     * @return
     */
    public static <T>Result<T> errot(CodeMsg codeMsg)
    {
        return new Result<T>(codeMsg);
    }

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
