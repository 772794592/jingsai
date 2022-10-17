package com.example.jingsai.tcp.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.jingsai.tcp.common.Result;
import com.example.jingsai.tcp.pojo.ServiceInfo;
import com.example.jingsai.tcp.vo.ServiceVo;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 *
 * @author liwen <devinlive@163.com>
 * @version 1.0
 * @since 2022/10/17 11:05
 */
public interface ServiceInfoService {

    public void addService(ServiceVo serviceVo) throws IOException, InterruptedException;

    /**
     * 查询所有
     * @return
     */
    public List<ServiceInfo> queryService();

    // 分页查询用户，用户带有权限。所以用UserDto
    Page<ServiceInfo> findServiceInfoPage(int pageNum, int pageSize, String search);
}
