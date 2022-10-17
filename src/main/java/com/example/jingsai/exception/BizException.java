package com.example.jingsai.exception;

import com.example.jingsai.enums.CodeEnum;

public class BizException extends RuntimeException {

    public BizException(String msg) {
        super(msg);
    }

    public BizException(CodeEnum codeEnum) {
        super(codeEnum.getDesc());
    }

}
