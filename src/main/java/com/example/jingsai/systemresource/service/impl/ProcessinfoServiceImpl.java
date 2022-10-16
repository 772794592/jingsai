package com.example.jingsai.systemresource.service.impl;

import com.example.jingsai.enums.CodeEnum;
import com.example.jingsai.systemresource.dao.ProcessInfoDao;
import com.example.jingsai.systemresource.pojo.ProcessInfo;
import com.example.jingsai.systemresource.service.ProcessInfoService;
import com.example.jingsai.systemresource.utils.BaseResponse;
import com.example.jingsai.systemresource.utils.CommandUtil;
import com.example.jingsai.systemresource.utils.EntityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @author: shigw
 * @Date:2022-10-12 14:36
 */
@Service
public class ProcessinfoServiceImpl implements ProcessInfoService {

    @Resource
    private ProcessInfoDao processInfoDao;


    /***
     * 查看所有历史进程占用情况
     * @return
     */
    @Override
    public BaseResponse queryProcess() {
        return BaseResponse.createBySuccess(processInfoDao.queryProcess());
    }

    @Override
    public BaseResponse query(String beginTime, String endTime, String serviceName) {
        return BaseResponse.createBySuccess(processInfoDao.query(beginTime,endTime,serviceName));
    }

    /***
     * 查看进程的占用系统资源
     * @param serviceName
     * @return
     */
    @Override
    public BaseResponse queryByname(String serviceName) {
        try {
            if (serviceName != null || !"".equals(serviceName)) {
                ProcessInfo processInfo = processInfoDao.queryMaxData(serviceName);
                if(processInfo != null) {
                    return BaseResponse.createBySuccess(processInfo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BaseResponse.createByError(CodeEnum.EC_GEN_INTERNAL);
    }





}
