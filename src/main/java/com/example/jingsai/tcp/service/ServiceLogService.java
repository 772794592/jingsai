package com.example.jingsai.tcp.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.jingsai.tcp.pojo.Message;
import com.example.jingsai.tcp.pojo.ServiceLog;

import java.util.List;

/**
 * <p>
 *
 * @author liwen <devinlive@163.com>
 * @version 1.0
 * @since 2022/10/18 12:06
 */
public interface ServiceLogService {

    Long insertLog(ServiceLog log);

    Page<ServiceLog> queryServiceLogPageByPid(String pid, int pageNum, int pageSize, String beginTm, String endTm);

    Page<ServiceLog> queryServiceLogPageByName(String serviceName, int pageNum, int pageSize);
}
