package com.example.jingsai.tcp.timer;

import com.example.jingsai.tcp.dao.ServiceInfoMapper;
import com.example.jingsai.tcp.pojo.ServiceInfo;
import com.example.jingsai.tcp.service.ServiceInfoService;
import com.example.jingsai.tcp.service.TcpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
//    @Autowired
//    private ServiceLogMapper serviceLogMapper;
    @Autowired
    private TcpService tcpService;
//    @Resource
//    private NicInfoService nicInfoService;

//    @Autowired
//    private ThreadPoolTaskExecutor applicationTaskExecutor;


//    @Async
//    @Scheduled(fixedDelay = 3000)
//    public void second() throws InterruptedException {
//        System.out.println("第二个异步线程开始" + Thread.currentThread().getName() + "=====>>>>>" + LocalDateTime.now().toLocalTime());
//        System.out.println();
//        List<ServiceInfo> serviceInfos = serviceInfoService.queryService();
//        for (ServiceInfo serviceInfo : serviceInfos) {
//            try {
//                // 记录日志
//                ServiceLog serviceLog = new ServiceLog();
//                serviceLog.setServiceName(serviceInfo.getServiceName());
//
//                String pid = tcpService.getPidByService(serviceInfo.getServiceName());
//                int tcpEstablishedCount = tcpService.tcpEstablishedCount(pid);
//                serviceLog.setTcpCount(String.valueOf(tcpEstablishedCount));
//
//                List<String> port = tcpService.tcpPort(pid);
//                serviceLog.setTcpPorts(Integer.toString(port.size()));
//
//                ArrayList<String> list = new ArrayList<>();
//                list.add(serviceInfo.getNetName());
//                BaseResponse nicInfoData = nicInfoService.getNicInfoData(list);
//                if (nicInfoData.getRespData() != null&&!((List<?>) nicInfoData.getRespData()).isEmpty()) {
//                    List<NicInfo> nicInfo = (List<NicInfo>) nicInfoData.getRespData();
//                    NicInfo info = nicInfo.get(0);
////            for (NicInfo info : nicInfo) {
//                    long nicTraffic = info.getNicTraffic();
//                    long nicSpeed = info.getNicSpeed();
//                    //流量
//                    serviceLog.setNicTraffic(nicTraffic);
//                    //流速
//                    serviceLog.setNicSpeed(nicSpeed);
////            }
//                }
//                serviceLog.setInsertTime(new Date());
//
//                logger.info("定时更新ServiceLog==>{}", serviceLog);
//                serviceLogMapper.updateById(serviceLog);
//            } catch (IOException e) {
//                logger.info("定时更新ServiceLog异常==>{}", e.getMessage());
//                e.printStackTrace();
//            }
//        }
//
//    }


    /**
     * 默认是fixedDelay 上一次执行完毕时间后执行下一轮
     */
    @Scheduled(cron = "0/10 * * * * *")
    public void run() throws InterruptedException {
        List<ServiceInfo> serviceInfos = serviceInfoService.queryService();
        for (ServiceInfo serviceInfo : serviceInfos) {
            try {
                // 更新服务状态
                String state = serviceInfoService.serviceState(serviceInfo.getServiceName());
                serviceInfo.setServiceState(state);
                serviceInfoMapper.updateById(serviceInfo);
                // tcp
                String pid = tcpService.getPidByService(serviceInfo.getServiceName());
                tcpService.tcpList(pid, true);
            } catch (IOException e) {
                logger.info("定时获取服务状态异常==>{}", e.getMessage());
                e.printStackTrace();
            }
        }

    }


}