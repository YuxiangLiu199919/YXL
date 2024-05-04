package com.etc.order.service.bo;


/**
 * @author Yuxiang Liu
 * 返回结构体
 * @param <T> 返回数据类型
 * */
public class ReturnResultBo<T> {


    private String code;

    private String msg;

    private T date;

    private static final String SUCCESS_CODE ="800";

    private static final String FAIL_CODE ="900";



    public static <T> ReturnResultBo<T> success(String msg, T data) {
        return new ReturnResultBo<>(SUCCESS_CODE, msg, data);
    }

    public static <T> ReturnResultBo<T> fail(String msg, T data) {
        return new ReturnResultBo<>(FAIL_CODE, msg, data);
    }

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

    public ReturnResultBo() {
    }

    public ReturnResultBo(String code, String msg, T date) {
        this.code = code;
        this.msg = msg;
        this.date = date;
    }
}
