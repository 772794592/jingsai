package com.example.jingsai.systemresource.service;

import com.example.jingsai.systemresource.utils.BaseResponse;

/**
 * @author: shigw
 * @Date:2022-10-12 14:36
 */
public interface ProcessInfoService {




    /***
     * Llinux 查看进程的占用系统资源(未进行数据库交互)
     * @param serviceName
     * @return
     */
    BaseResponse queryByname(String serviceName);


    /***
     * 查询出所有进程的资源占用情况
     * @return
     */
    BaseResponse queryProcess();

    BaseResponse query(String beginTime, String endTime, String serviceName);

}
