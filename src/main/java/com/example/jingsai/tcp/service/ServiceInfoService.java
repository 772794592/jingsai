package com.example.jingsai.tcp.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.jingsai.tcp.pojo.ServiceInfo;
import com.example.jingsai.tcp.vo.ServiceDTO;

import java.io.IOException;
import java.util.List;

/**
 * 服务信息实现类
 * <p>
 *
 * @author liwen <devinlive@163.com>
 * @version 1.0
 * @since 2022/10/17 11:05
 */
public interface ServiceInfoService {
    /**
     * 添加服务
     */
    int addService(ServiceDTO serviceDTO) throws IOException, InterruptedException;

    String serviceState(String s) throws IOException, InterruptedException;

    /**
     * 删除服务
     */
    int delService(String id);

    /**
     * 查询所有
     */
    public List<ServiceInfo> queryService();

    /**
     * 通过id查服务
     */
    ServiceInfo selectOne(String id);

    /**
     * 分页查询服务
     */
    Page<ServiceInfo> findServiceInfoPage(int pageNum, int pageSize, String search);


}
