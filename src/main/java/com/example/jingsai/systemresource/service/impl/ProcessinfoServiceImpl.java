package com.example.jingsai.systemresource.service.impl;

import com.example.jingsai.enums.CodeEnum;
import com.example.jingsai.systemresource.dao.ProcessInfoDao;
import com.example.jingsai.systemresource.pojo.ProcessInfo;
import com.example.jingsai.systemresource.service.ProcessInfoService;
import com.example.jingsai.utils.BaseResponse;
import com.example.jingsai.utils.CommandUtil;
import com.example.jingsai.utils.entityUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;


/**
 * @author: shigw
 * @Date:2022-10-12 14:36
 */
public class ProcessinfoServiceImpl implements ProcessInfoService {

    @Resource
    private ProcessInfoDao processInfoDao;

    /***
     * 查看进程的占用系统资源
     * @param service
     * @param pid
     * @return
     */
    @Override
    public BaseResponse queryByname(String service, String pid) {
       try{
           if(service != null ||  !"".equals(service)){
               CommandUtil.ExecReturn severPid = CommandUtil.exec(new String[]{"pgerp", "-f", service});
               if(severPid.exitCode != 0 || "".equals(severPid.stdout)){
                   return BaseResponse.createByError(CodeEnum.EC_GEN_INTERNAL);
               }
               CommandUtil.ExecReturn execReturn = CommandUtil.exec(new String[]{"kill", "-0", severPid.stdout});
               if(execReturn.exitCode != 0 || execReturn.stdout == null){
                   return BaseResponse.createByError(CodeEnum.PROCESS_IS_NOT);
               }
               String[] command = new String[]{"/opt/jdwa/sync/etc/unisync.sh","get_process",severPid.stdout};
               CommandUtil.ExecReturn exec = CommandUtil.exec(command);
               if(exec.exitCode ==0 ||(exec.stdout != null || !"".equals(exec.stdout))){
                   return BaseResponse.createBySuccess(entityUtils.getProessMessage(exec.stdout));
               }
           }
       }catch (Exception e){
           e.printStackTrace();
       }
        return BaseResponse.createByError(CodeEnum.EC_GEN_INTERNAL);
    }



    @Override
    public List<ProcessInfo> queryProcess() {
        return processInfoDao.queryProcess();
    }


}
