package com.example.jingsai.tcp.exception;

/**
 * 自定义业务异常类
 * <p>
 *
 * Created by liWen on 2022/10/13 12:35
 */
public class CustomException extends RuntimeException {

    private String errorCode;
    private String errorMsg;

    public CustomException(){
        super();
    }

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    // getter & setter

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
