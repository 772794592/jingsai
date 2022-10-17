package com.example.jingsai.enums;

public enum CodeEnum {
    EC_SUCCESS(0x00101001, "成功"),
    EC_GEN_INTERNAL(0x00101002, "操作失败"),
    PROCESS_IS_NOT(0x00101003,"该进程不存在！"),
    DEL_SERVICE_ERROR(0x00101004,"删除失败！"),
    ADD_SERVICE_ERROR(0x00101004,"添加失败！"),
    ADD_SERVICE_ISNULL(0x00101004,"未找到该服务！"),
    ADD_SERVICE_NAME_EXIST(0x00101005,"服务名已存在！"),

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
