package com.example.jingsai.systemresource.service.impl;

import com.example.jingsai.enums.CodeEnum;
import com.example.jingsai.systemresource.dao.ServiceInfoDao;
import com.example.jingsai.systemresource.pojo.ServiceInfo;
import com.example.jingsai.systemresource.service.ServiceInfoService;
import com.example.jingsai.systemresource.utils.BaseResponse;
import com.example.jingsai.systemresource.utils.CommandUtil;
import com.example.jingsai.systemresource.utils.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author: shigw
 * @Date:2022-10-13 14:43
 */
@Service
public class ServicecInfoServiceImpl implements ServiceInfoService {


    private final static Logger log = LoggerFactory.getLogger(ServicecInfoServiceImpl.class);
    @Resource
    private ServiceInfoDao serviceInfoDao;


    @Override
    public List<ServiceInfo> query() {
        updateStatus();
        return serviceInfoDao.query();
    }

    @Override
    public BaseResponse addService(ServiceInfo serviceInfo) {
        try {
            ServiceInfo serviceName = serviceInfoDao.queryByName(serviceInfo.getService_name());
            if(serviceName == null){
                return BaseResponse.createByError(CodeEnum.ADD_SERVICE_NAME_EXIST);
            }
            String[] command = new String[]{EntityUtils.CMDPARAM, "get_service_name", serviceInfo.getService_name()};
            CommandUtil.ExecReturn exec = CommandUtil.exec(command);
            if (exec.exitCode == 0 && !"".equals(exec.stdout)) {
                if (exec.equals("failed")) {
                    serviceInfo.setService_status(1);
                }else{
                    serviceInfo.setService_status(0);
                }
                serviceInfo.setRecord_time(new Date().getTime());
                serviceInfoDao.addService(serviceInfo);
                return BaseResponse.createBySuccess();
            }
            return BaseResponse.createByError(CodeEnum.ADD_SERVICE_ISNULL);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("add service error {}", e.getMessage());
        }
        return BaseResponse.createByError(CodeEnum.ADD_SERVICE_ERROR);
    }

    @Override
    public BaseResponse delService(int id) {
        try {
            int count = serviceInfoDao.delService(id);
            if (count > 0) {
                return BaseResponse.createBySuccess();
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.info("del service data error {}", e.getMessage());
        }
        return BaseResponse.createByError(CodeEnum.DEL_SERVICE_ERROR);
    }


    /***
     * 刷新监控服务状态是否改变
     */
    public void updateStatus() {
        try {
            List<ServiceInfo> serviceInfos = serviceInfoDao.query();
            for (ServiceInfo serviceInfo : serviceInfos) {
                String[] command = new String[]{EntityUtils.CMDPARAM, "get_service_name", serviceInfo.getService_name()};
                CommandUtil.ExecReturn exec = CommandUtil.exec(command);
                if (exec.exitCode == 0 && !"".equals(exec.stdout)) {
                    if (exec.equals("failed")) {
                        serviceInfo.setService_status(1);
                    } else {
                        serviceInfo.setService_status(0);
                    }
                    serviceInfoDao.updateStatus(serviceInfo.getService_status(), serviceInfo.getId());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
