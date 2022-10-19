package com.example.jingsai.tcp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.jingsai.tcp.dao.ServiceLogMapper;
import com.example.jingsai.tcp.pojo.Message;
import com.example.jingsai.tcp.pojo.ServiceLog;
import com.example.jingsai.tcp.service.ServiceLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *
 * @author liwen <devinlive@163.com>
 * @version 1.0
 * @since 2022/10/18 12:06
 */
@Service
public class ServiceLogServiceImpl implements ServiceLogService {

    @Resource
    private ServiceLogMapper serviceLogMapper;

    @Override
    public int insertLog(ServiceLog log) {

        int row = serviceLogMapper.insert(log);
        return row;
    }

    @Override
    public Page<ServiceLog> queryServiceLogPageByPid(String pid, int pageNum, int pageSize, long beginTm, long endTm) {
        QueryWrapper<ServiceLog> wrapper = new QueryWrapper<>();
        if (!"".equals(pid)) {
            wrapper.eq("service_pid", pid);
        }



        return serviceLogMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
    }


}
