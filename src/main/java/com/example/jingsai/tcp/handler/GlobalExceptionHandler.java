package com.example.jingsai.tcp.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 * <p>
 *
 * @author liwen <devinlive@163.com>
 * @version 1.0
 * @since 2022-10-12 22:55
 */
//@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    String handler(Exception e){
        return e.getMessage();
    }
}
