package com.example.jingsai.systemresource.service.impl;

import com.example.jingsai.systemresource.dao.ProcessInfoDao;
import com.example.jingsai.systemresource.pojo.ProcessInfo;
import com.example.jingsai.systemresource.service.ProcessInfoService;
import javax.annotation.Resource;

/**
 * @author: shigw
 * @Date:2022-10-12 14:36
 */
public class ProcessinfoServiceImpl implements ProcessInfoService {

    @Resource
    private ProcessInfoDao processInfoDao;

    /***
     * 查看进程的占用系统资源
     * @param service
     * @param pid
     * @return
     */
    @Override
    public ProcessInfo queryByname(String service, String pid) {

        return null;
    }
}
