package com.example.jingsai.tcp.timer;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.jingsai.tcp.dao.ServiceInfoMapper;
import com.example.jingsai.tcp.dao.TcpMapper;
import com.example.jingsai.tcp.pojo.ServiceInfo;
import com.example.jingsai.tcp.pojo.ServiceLog;
import com.example.jingsai.tcp.service.ServiceInfoService;
import com.example.jingsai.tcp.service.ServiceLogService;
import com.example.jingsai.tcp.service.TcpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * 注解多线程 定时任务
 *
 * @author liwen <devinlive@163.com>
 * @version 1.0
 * @since 2022-10-17 15:27
 */

@Component
@EnableScheduling
@EnableAsync
public class MultiThreadScheduleTask {

    private static final Logger logger = LoggerFactory.getLogger(MultiThreadScheduleTask.class);

    @Autowired
    private ServiceInfoService serviceInfoService;
    @Autowired
    private ServiceInfoMapper serviceInfoMapper;
    @Autowired
    private ServiceLogService serviceLogService;
    @Autowired
    private TcpService tcpService;
    @Autowired
    private TcpMapper tcpMapper;

    @Async
    @Scheduled(fixedDelay = 5000)
    public void second() throws InterruptedException {

        List<ServiceInfo> serviceInfos = serviceInfoService.queryService();
        for (ServiceInfo serviceInfo : serviceInfos) {
            try {
                // 服务详情入库
                ServiceLog serviceLog = new ServiceLog();

                // pid
                String pid = tcpService.getPidByService(serviceInfo.getServiceName());
                serviceLog.setServiceName(serviceInfo.getServiceName());
                serviceLog.setServicePid(pid);
                // 连接数
                int tcpEstablishedCount = tcpService.tcpEstablishedCount(pid);
                serviceLog.setTcpCount(String.valueOf(tcpEstablishedCount));

                // 连接端口
                StringBuilder port = tcpService.tcpPort(pid);
                serviceLog.setTcpPort(port.toString());
                serviceLog.setInsertTime(System.currentTimeMillis());

                // 更新服务状态
                String state = serviceInfoService.serviceState(serviceInfo.getServiceName());
                serviceInfo.setServiceState(state);
                serviceInfoMapper.update(serviceInfo,new QueryWrapper<ServiceInfo>().eq("service_name",serviceInfo.getServiceName()));



                Long serviceLogId = serviceLogService.insertLog(serviceLog);
                tcpService.tcpList(pid, true, serviceLogId);

            } catch (IOException e) {
                e.printStackTrace();
                logger.info("定时更新ServiceLog异常==>{}", e.getMessage());
            }
        }

    }


}