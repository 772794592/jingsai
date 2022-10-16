package com.example.jingsai.enums;

public enum CodeEnum {
    EC_SUCCESS(0x00101001, "成功"),
    EC_GEN_INTERNAL(0x00101002, "操作失败"),

    SHELL_EXEC_FAIL(0x00201001, "shell执行异常");

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
