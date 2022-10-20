package com.example.jingsai.systemresource;

import com.example.jingsai.systemresource.dao.ProcessInfoDao;
import com.example.jingsai.systemresource.dao.ServiceInfoDao;
import com.example.jingsai.systemresource.pojo.ProcessInfo;
import com.example.jingsai.systemresource.pojo.ServiceInfo;
import com.example.jingsai.systemresource.utils.CommandUtil;
import com.example.jingsai.systemresource.utils.EntityUtils;
import com.example.jingsai.tcp.service.TcpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: shigw
 * @Date:2022-10-13 13:38
 */
@Component
@Order(value = 1)
public class SysProcessTimer implements CommandLineRunner {

    private final static Logger log= (Logger) LoggerFactory.getLogger(SysProcessTimer.class);

    @Resource
    private ProcessInfoDao processInfoDao;

    @Resource
    private ServiceInfoDao serviceInfoDao;


    @Override
    public void run(String... args) throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        List<ServiceInfo> serviceInfos = serviceInfoDao.query();
                        if(serviceInfos.isEmpty()){
                            return;
                        }
                        for (ServiceInfo serviceInfo : serviceInfos) {
                            if (serviceInfo.getServiceStatus() == 0) {
                                String[] pidCmd = {"sh", "-c", "systemctl status " + serviceInfo.getServiceName() + " |grep 'Main PID'|awk '{print $3}'"};
                                CommandUtil.ExecReturn severPid = CommandUtil.exec(pidCmd);
                                if(severPid.exitCode != 0 || "".equals(severPid.stdout)){
                                    log.info("Error get PID is not null");
                                }
                                String[] command = new String[]{"/opt/jdwa/etc/jingsai/sysporicess.sh","get_process",severPid.stdout};
                                CommandUtil.ExecReturn exec = CommandUtil.exec(command);
                                if(exec.exitCode ==0 && !"".equals(exec.stdout)){
                                    ProcessInfo proessMessage = EntityUtils.getProessMessage(exec.stdout,serviceInfo.getServiceName());
                                    processInfoDao.addProcess(proessMessage);
                                }else{
                                    log.info("process is stop or error");
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }
}
