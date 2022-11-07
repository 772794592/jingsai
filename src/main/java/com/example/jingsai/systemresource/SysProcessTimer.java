package com.example.jingsai.systemresource;

import com.example.jingsai.systemresource.dao.ProcessInfoDao;
import com.example.jingsai.systemresource.dao.ServiceInfoDao;
import com.example.jingsai.systemresource.pojo.ProcessInfo;
import com.example.jingsai.systemresource.pojo.ServiceInfo;
import com.example.jingsai.systemresource.utils.CommandUtil;
import com.example.jingsai.systemresource.utils.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @author: shigw
 * @Date:2022-10-13 13:38
 */
@Component
@Order(value = 1)
public class SysProcessTimer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(SysProcessTimer.class);

    @Resource
    private ProcessInfoDao processInfoDao;

    @Resource
    private ServiceInfoDao serviceInfoDao;


    @Override
    public void run(String... args) throws Exception {
        new Thread(() -> {
            while (true) {
                try {
                    List<ServiceInfo> serviceInfos = serviceInfoDao.query();
                    if (serviceInfos.isEmpty()) {
                        return;
                    }
                    this.addProcess(serviceInfos);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                    log.info("add process --> exc");
                } finally {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                        log.info("sleep --> exc");
                    }
                }
            }
        }).start();
    }

    public void addProcess(List<ServiceInfo> serviceInfos) throws IOException, InterruptedException {
        for (ServiceInfo serviceInfo : serviceInfos) {
            if (serviceInfo.getServiceStatus() == 0) {
                String[] pidCmd = {"sh", "-c", "systemctl status " + serviceInfo.getServiceName() + " |grep 'Main PID'|awk '{print $3}'"};
                CommandUtil.ExecReturn severPid = CommandUtil.exec(pidCmd);
                if (severPid.exitCode != 0 || "".equals(severPid.stdout)) {
                    log.info("Error get PID is not null");
                }
                String[] command = new String[]{"/opt/jdwa/etc/jingsai/sysporicess.sh", "get_process", severPid.stdout};
                CommandUtil.ExecReturn exec = CommandUtil.exec(command);
                if (exec.exitCode == 0 && !"".equals(exec.stdout)) {
                    ProcessInfo proessMessage = EntityUtils.getProessMessage(exec.stdout, serviceInfo.getServiceName());
                    processInfoDao.addProcess(proessMessage);
                } else {
                    log.info("process is stop or error");
                }
            }
        }
    }

}
