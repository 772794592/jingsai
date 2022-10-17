package com.example.jingsai.tcp.common;

import java.io.Serializable;

public class Result<T> implements Serializable {

    private String code;

    private String msg;

    private T data;

    public Result() {

    }

    public Result(T data) {
        this.data = data;
    }

    //请求成功不携带数据方法
    public static Result success() {
        Result result = new Result<>();
        result.setCode(BaseEnum.SUCCESS.getResultCode());
        result.setMsg(BaseEnum.SUCCESS.getResultMsg());
        return result;

    }

    //请求成功携带数据的泛型方法
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>(data);
        result.setCode(BaseEnum.SUCCESS.getResultCode());
        result.setMsg(BaseEnum.SUCCESS.getResultMsg());
        return result;

    }

    //失败 定义状态码和显示信息
    public static Result error(String code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}