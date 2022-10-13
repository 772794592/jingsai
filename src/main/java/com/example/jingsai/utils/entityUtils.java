package com.example.jingsai.utils;

import com.example.jingsai.systemresource.pojo.ProcessInfo;

import java.time.LocalDateTime;

/**
 * @author: shigw
 * @Date:2022-10-13 14:15
 */
public class entityUtils {

    public static ProcessInfo getProessMessage(String msg){
        ProcessInfo processInfo = new ProcessInfo();
        String[] messages = msg.split(" ");
        processInfo.setPid(Integer.valueOf(messages[0]));
        processInfo.setUser(messages[1]);
        processInfo.setPr(Integer.valueOf(messages[2]));
        processInfo.setNi(Integer.valueOf(messages[3]));
        processInfo.setVirt(Integer.valueOf(messages[4]));
        processInfo.setRes(Integer.valueOf(messages[5]));
        processInfo.setShr(Integer.valueOf(messages[6]));
        processInfo.setS(messages[7]);
        processInfo.setCpu(Double.valueOf(messages[8]));
        processInfo.setMem(Double.valueOf(messages[9]));
        processInfo.setTime(messages[10]);
        processInfo.setCommand(messages[11]);
        processInfo.setRecordTime(LocalDateTime.now());
        return processInfo;
    }
}
