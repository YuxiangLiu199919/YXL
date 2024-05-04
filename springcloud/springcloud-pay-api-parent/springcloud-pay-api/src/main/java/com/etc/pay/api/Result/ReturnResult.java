package com.etc.order.api.Result;


/**
 * 返回结构体
 * @author Yuxiang Liu
 * */
public class ReturnResult<T> {

    private String code;

    private String msg;

    private T date;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getDate() {
        return date;
    }

    public void setDate(T date) {
        this.date = date;
    }
}
