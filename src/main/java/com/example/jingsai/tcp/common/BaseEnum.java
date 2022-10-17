package com.example.jingsai.tcp.common;

import org.springframework.lang.Nullable;

import java.util.Objects;

public enum BaseEnum {
    /**
     * 每一个枚举变量都是枚举类 CommonEnum的实例化
     * CommonEnum.SUCCESS获得的对象相当于new CommonEnum("200", "成功!");获得的对象
     */

// 数据操作错误定义
    SUCCESS("200", "成功!"),

    BODY_NOT_MATCH("400", "请求的数据格式不符!"),

    INTERNAL_SERVER_ERROR("500", "服务器内部错误!"),

    NOT_FOUND("404", "未找到该资源!"),

    NOT_SUPPORTED("401", "请求方法不被支持"),

    SERVER_BUSY("503", "服务器正忙，请稍后再试!"),

    SIGNATURE_NOT_MATCH("401", "请求的数字签名不匹配!"),

    PID_ISNULL("402", "进程PID为空"),

    NOT_RUNNING("-1", "运行时异常"),

    TEST;

    private static final BaseEnum[] VALUES;

    static {
        // 枚举类里有两个默认的静态方法：values() ,valuesOf()
        VALUES = values();
    }

    @Nullable
    public static BaseEnum resolve(String statusCode) {
        for (BaseEnum status : VALUES) {
            if (Objects.equals(status.resultCode, statusCode)) {
                return status;
            }
        }
        return null;
    }

    //错误码
    private String resultCode;

    //错误描述
    private String resultMsg;


    BaseEnum() {
    }

    BaseEnum(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    public String getResultCode() {
        return resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

}