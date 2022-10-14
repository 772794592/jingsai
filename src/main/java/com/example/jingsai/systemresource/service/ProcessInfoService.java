package com.example.jingsai.systemresource.service;

import com.example.jingsai.systemresource.pojo.ProcessInfo;
import com.example.jingsai.utils.BaseResponse;

import java.util.Date;
import java.util.List;

/**
 * @author: shigw
 * @Date:2022-10-12 14:36
 */
public interface ProcessInfoService {




    /***
     * Llinux 查看进程的占用系统资源(未进行数据库交互)
     * @param service
     * @return
     */
    BaseResponse queryByname(String service);


    /***
     * 查询出所有进程的资源占用情况
     * @return
     */
    BaseResponse queryProcess();

    BaseResponse query(String beginTime, String endTime, String serviceName);

}
