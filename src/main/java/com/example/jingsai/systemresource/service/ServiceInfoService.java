package com.example.jingsai.systemresource.service;

import com.example.jingsai.systemresource.pojo.ServiceInfo;
import com.example.jingsai.utils.BaseResponse;

import java.util.List;

/**
 * @author: shigw
 * @Date:2022-10-13 14:35
 */
public interface ServiceInfoService {

    /***
     * 查询出所有服务信息
     * @return
     */
    BaseResponse query();

    /***
     * 添加服务
     * @param serviceInfo
     * @return
     */
    BaseResponse addService(ServiceInfo serviceInfo);

    /***
     * 删除服务
     * @param id
     * @return
     */
    BaseResponse  delService(int id);

}
