package com.example.jingsai.tcp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.jingsai.tcp.dao.ServiceLogMapper;
import com.example.jingsai.tcp.pojo.Message;
import com.example.jingsai.tcp.pojo.ServiceLog;
import com.example.jingsai.tcp.service.ServiceLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

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
    public Long insertLog(ServiceLog log) {

        serviceLogMapper.save(log);
        return Long.valueOf(log.getId());
    }

    @Override
    public Page<ServiceLog> queryServiceLogPageByPid(String pid, int pageNum, int pageSize, String beginTm, String endTm) {
        QueryWrapper<ServiceLog> wrapper = new QueryWrapper<>();

        if ("".equals(beginTm) && "".equals(endTm)) {
            wrapper.eq("service_pid", pid);
        } else if ("".equals(endTm)) { // 有开始时间
            wrapper.ge("insert_time", beginTm).le("insert_time", System.currentTimeMillis());
        } else if ("".equals(beginTm)) { // 有结束时间
            wrapper.le("insert_time", endTm);
        }else {
            wrapper.ge("insert_time", beginTm).le("insert_time", endTm);
        }
        return serviceLogMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
    }

    @Override
    public Page<ServiceLog> queryServiceLogPageByName(String serviceName, int pageNum, int pageSize) {
        QueryWrapper<ServiceLog> wrapper = new QueryWrapper<>();
        wrapper.eq("service_name", serviceName);
        wrapper.orderByDesc("id");
        return serviceLogMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
    }


}
