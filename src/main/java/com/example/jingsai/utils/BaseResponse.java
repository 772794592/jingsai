package com.example.jingsai.utils;

import com.example.jingsai.enums.CodeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class BaseResponse {

    private String respDesc;
    private int respCode;
    private Object respData;
    private Object stack;

    public BaseResponse(CodeEnum code, Object data) {
        this.respCode = code.getCode();
        this.respDesc = code.getDesc();
        this.respData = data;
    }

    public BaseResponse(CodeEnum code) {
        this.respCode = code.getCode();
        this.respDesc = code.getDesc();
    }

    public BaseResponse(CodeEnum code, String msg) {
        this.respCode = code.getCode();
        this.respDesc = msg;
    }

    public BaseResponse(CodeEnum code, Object data, Object stack) {
        this.respCode = code.getCode();
        this.respDesc = code.getDesc();
        this.respData = data;
        this.stack = stack;
    }

    @JsonIgnore
    //使之不在json序列化结果当中
    public boolean isSuccess() {
        return this.respCode == CodeEnum.EC_SUCCESS.getCode();
    }

    public String getRespDesc() {
        return respDesc;
    }

    public void setRespDesc(String respDesc) {
        this.respDesc = respDesc;
    }

    public int getRespCode() {
        return respCode;
    }

    public void setRespCode(int respCode) {
        this.respCode = respCode;
    }

    public Object getRespData() {
        return respData;
    }

    public void setRespData(Object respData) {
        this.respData = respData;
    }

    public Object getStack() {
        return stack;
    }

    public void setStack(Object stack) {
        this.stack = stack;
    }

    public static BaseResponse createByError(CodeEnum codeEnum) {
        return new BaseResponse(codeEnum);
    }

    public static BaseResponse createByError(String msg) {
        return new BaseResponse(CodeEnum.EC_GEN_INTERNAL, msg);
    }

    public static BaseResponse createBySuccess(Object respData) {
        return new BaseResponse(CodeEnum.EC_SUCCESS, respData);
    }

    public static BaseResponse createBySuccess() {
        return new BaseResponse(CodeEnum.EC_SUCCESS);
    }
}
