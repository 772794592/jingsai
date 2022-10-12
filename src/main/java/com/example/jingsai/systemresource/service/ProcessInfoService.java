package com.example.jingsai.systemresource.service;

import com.example.jingsai.systemresource.pojo.ProcessInfo;
/**
 * @author: shigw
 * @Date:2022-10-12 14:36
 */
public interface ProcessInfoService {

    /***
     * 查看进程的占用系统资源
     * @param service
     * @param pid
     * @return
     */
    ProcessInfo queryByname(String service,String pid);
}
