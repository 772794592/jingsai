package com.example.jingsai.systemresource.service.impl;

import com.example.jingsai.enums.CodeEnum;
import com.example.jingsai.systemresource.dao.ProcessInfoDao;
import com.example.jingsai.systemresource.service.ProcessInfoService;
import com.example.jingsai.utils.BaseResponse;
import com.example.jingsai.utils.CommandUtil;
import com.example.jingsai.utils.EntityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @author: shigw
 * @Date:2022-10-12 14:36
 */
@Service
public class ProcessinfoServiceImpl implements ProcessInfoService {

    @Resource
    private ProcessInfoDao processInfoDao;


    /***
     * 查看所有历史进程占用情况
     * @return
     */
    @Override
    public BaseResponse queryProcess() {
        return BaseResponse.createBySuccess(processInfoDao.queryProcess());
    }

    @Override
    public BaseResponse query(String beginTime, String endTime, String serviceName) {
        return BaseResponse.createBySuccess(processInfoDao.query(beginTime,endTime,serviceName));
    }

    /***
     * 查看进程的占用系统资源
     * @param service
     * @return
     */
    @Override
    public BaseResponse queryByname(String service) {
        try {
            if (service != null || !"".equals(service)) {
                CommandUtil.ExecReturn severPid = CommandUtil.exec(new String[]{"pgerp", "-f", service});
                if (severPid.exitCode != 0 || "".equals(severPid.stdout)) {
                    return BaseResponse.createByError(CodeEnum.EC_GEN_INTERNAL);
                }
                CommandUtil.ExecReturn execReturn = CommandUtil.exec(new String[]{"kill", "-0", severPid.stdout});
                if (execReturn.exitCode != 0 || execReturn.stdout == null) {
                    return BaseResponse.createByError(CodeEnum.PROCESS_IS_NOT);
                }
                String[] command = new String[]{"/opt/jdwa/sync/etc/unisync.sh", "get_process", severPid.stdout};
                CommandUtil.ExecReturn exec = CommandUtil.exec(command);
                if (exec.exitCode == 0 || (exec.stdout != null || !"".equals(exec.stdout))) {
                    return BaseResponse.createBySuccess(EntityUtils.getProessMessage(exec.stdout,service));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BaseResponse.createByError(CodeEnum.EC_GEN_INTERNAL);
    }





}
