package com.example.jingsai.systemresource.service.impl;

import com.example.jingsai.systemresource.dao.ServiceInfoDao;
import com.example.jingsai.systemresource.pojo.ServiceInfo;
import com.example.jingsai.systemresource.service.ServiceInfoService;
import com.example.jingsai.utils.BaseResponse;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: shigw
 * @Date:2022-10-13 14:43
 */
public class ServicecInfoServiceImpl  implements ServiceInfoService {

    @Resource
    private ServiceInfoDao serviceInfoDao;


    @Override
    public BaseResponse query() {
        return BaseResponse.createBySuccess(serviceInfoDao.query());
    }

    @Override
    public BaseResponse addService(ServiceInfo serviceInfo) {
        //判断该服务是否存在，并查看服务的状态 （service 服务名 status ）
        return BaseResponse.createBySuccess(serviceInfoDao.addService(serviceInfo));
    }

    @Override
    public BaseResponse delService(int id) {
        return BaseResponse.createBySuccess(serviceInfoDao.delService(id));
    }
}
