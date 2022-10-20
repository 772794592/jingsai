package com.example.jingsai.systemresource.utils;

import com.example.jingsai.systemresource.pojo.ProcessInfo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author: shigw
 * @Date:2022-10-13 14:15
 */
public class EntityUtils {

    public static final String  CMDPARAM ="/opt/jdwa/etc/jingsai/sysporicess.sh";
    public static ProcessInfo getProessMessage(String msg,String serviceName){
        ProcessInfo processInfo = new ProcessInfo();
        String[] messages = msg.split(" ");
        processInfo.setPid(Integer.parseInt(messages[0].trim()));
        processInfo.setUser(messages[1]);
        processInfo.setPr(Integer.parseInt(messages[2].trim()));
        processInfo.setNi(Integer.parseInt(messages[3].trim()));
        processInfo.setVirt(Integer.parseInt(messages[4].trim()));
        String res = messages[5].trim();
        if(res.lastIndexOf("g") != -1){
            String trafficGb = res.substring(0, res.length() - 1);
            double trafficKb = Double.parseDouble(trafficGb) * 1024 * 1024;
            processInfo.setRes(String.valueOf(trafficKb));
        }else{
            processInfo.setRes(res);
        }
        processInfo.setShr(Integer.parseInt(messages[6].trim()));
        processInfo.setS(messages[7].trim());
        processInfo.setCpu(Double.parseDouble(messages[8].trim()));
        processInfo.setMem(Double.parseDouble(messages[9].trim()));
        processInfo.setTime(messages[10].trim());
        processInfo.setCommand(messages[11].trim());
        processInfo.setRecordTime(System.currentTimeMillis());
        processInfo.setServiceName(serviceName);
        return processInfo;
    }
}
