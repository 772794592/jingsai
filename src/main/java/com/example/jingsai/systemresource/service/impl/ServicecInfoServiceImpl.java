package com.example.jingsai.systemresource.service.impl;

import com.example.jingsai.enums.CodeEnum;
import com.example.jingsai.systemresource.dao.ServiceInfoDao;
import com.example.jingsai.systemresource.pojo.ServiceInfo;
import com.example.jingsai.systemresource.service.ServiceInfoService;
import com.example.jingsai.utils.BaseResponse;
import com.example.jingsai.utils.CommandUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author: shigw
 * @Date:2022-10-13 14:43
 */
@Service
public class ServicecInfoServiceImpl  implements ServiceInfoService {


    private final static Logger log=  LoggerFactory.getLogger(ServicecInfoServiceImpl.class);
    @Resource
    private ServiceInfoDao serviceInfoDao;


    @Override
    public BaseResponse query() {
        return BaseResponse.createBySuccess(serviceInfoDao.query());
    }

    @Override
    public BaseResponse addService(ServiceInfo serviceInfo) {
        try{
            //判断该服务是否存在，并查看服务的状态 （service 服务名 status ）
            String[] command = new String[]{"/opt/jdwa/sync/etc/unisync.sh", "get_service_name", serviceInfo.getService_name()};
            CommandUtil.ExecReturn exec = CommandUtil.exec(command);
            if(exec.exitCode == 0){
                if(exec.equals("failed")){
                    serviceInfo.setService_status(1);
                }
                serviceInfo.setService_status(0);
                serviceInfo.setRecord_time(LocalDateTime.now());
                serviceInfoDao.addService(serviceInfo);
                return BaseResponse.createBySuccess();
            }
        }catch (Exception e){
            e.printStackTrace();
            log.info("add service error {}",e.getMessage());
        }
        return BaseResponse.createByError(CodeEnum.ADD_SERVICE_ERROR);
    }

    @Override
    public BaseResponse delService(int id) {
       try{
           int count = serviceInfoDao.delService(id);
           if(count > 0){
               return BaseResponse.createBySuccess();
           }

       }catch (Exception e){
           e.printStackTrace();
           log.info("del service data error {}",e.getMessage());
       }
        return BaseResponse.createByError(CodeEnum.DEL_SERVICE_ERROR);
    }
}
