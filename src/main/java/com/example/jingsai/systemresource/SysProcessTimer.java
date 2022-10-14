package com.example.jingsai.systemresource;

/**
 * @author: shigw
 * @Date:2022-10-13 13:38
 */
/*@Component
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
                            if (serviceInfo.getServiceStatus() > 0) {
                                CommandUtil.ExecReturn severPid = CommandUtil.exec(new String[]{"pgerp", "-f", serviceInfo.getServiceName()});
                                //TODO 有可能获取两个PID，此命令待确定，是否分割取出PID
                                if(severPid.exitCode != 0 || "".equals(severPid.stdout)){
                                    log.info("Error get PID is not null");
                                }
                                String[] command = new String[]{"/opt/jdwa/sync/etc/unisync.sh","get_process",severPid.stdout};
                                CommandUtil.ExecReturn exec = CommandUtil.exec(command);
                                if(exec.exitCode ==0 ||(exec.stdout != null || !"".equals(exec.stdout))){
                                    ProcessInfo proessMessage = entityUtils.getProessMessage(exec.stdout,serviceInfo.getServiceName());
                                    processInfoDao.addProcess(proessMessage);
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
}*/
