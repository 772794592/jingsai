package com.example.jingsai.enums;

public enum CodeEnum {
    EC_SUCCESS(0x00101001, "成功"),
    EC_GEN_INTERNAL(0x00101002, "操作失败"),
    PROCESS_IS_NOT(0x00101003,"该进程不存在！");


    private int code;
    private String desc;

    CodeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return "CodeEnum{" +
                "code=" + code +
                ", desc='" + desc + '\'' +
                '}';
    }
}
