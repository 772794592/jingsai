package com.example.jingsai.tcp.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.jingsai.systemresource.utils.CommandUtil;
import com.example.jingsai.systemresource.utils.EntityUtils;
import com.example.jingsai.tcp.common.BaseEnum;
import com.example.jingsai.tcp.common.Result;
import com.example.jingsai.tcp.dao.ServiceInfoMapper;
import com.example.jingsai.tcp.exception.CustomException;
import com.example.jingsai.tcp.pojo.ServiceInfo;
import com.example.jingsai.tcp.service.ServiceInfoService;
import com.example.jingsai.tcp.service.TcpService;
import com.example.jingsai.tcp.utils.ExecResult;
import com.example.jingsai.tcp.vo.ServiceDTO;
import com.example.jingsai.utils.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 服务列表信息
 * <p>
 *
 * @author liwen <devinlive@163.com>
 * @version 1.0
 * @since 2022/10/17 11:05
 */
@Service
public class ServiceInfoServImpl implements ServiceInfoService {

    private static final Logger logger = LoggerFactory.getLogger(TcpService.class);

    @Resource
    private ServiceInfoMapper serviceInfoMapper;

    @Resource
    private TcpService tcpService;

    public BaseResponse addService(ServiceDTO serviceDTO) throws IOException, InterruptedException {
        ServiceInfo serviceInfo = new ServiceInfo();
        ServiceInfo serviceInfo1 = serviceInfoMapper.queryByName(serviceDTO.getServiceName());
        if(serviceInfo1 != null){
            //return 服务名称已存在
            return BaseResponse.createByError(BaseEnum.NOT_SERVICE.getResultMsg());
        }
        serviceInfo.setServiceName(serviceDTO.getServiceName());
        // 查询有效网卡
        List<String> cars = queryNetCar();
        if (!cars.contains(serviceDTO.getNetName())){
             return  BaseResponse.createByError(BaseEnum.NET_CARD_NOT_EXIST.getResultMsg());
        }
        serviceInfo.setNetName(serviceDTO.getNetName());
        //判断服务是否能存在不存在状态是否是启动的
        String[] command = new String[]{EntityUtils.CMDPARAM, "get_service_name", serviceInfo.getServiceName()};
        CommandUtil.ExecReturn exec = CommandUtil.exec(command);
        if (exec.exitCode == 0 && !"".equals(exec.stdout)) {
            if (exec.stdout.trim().equals("failed")) {
                serviceInfo.setServiceState("1");
            }else{
                serviceInfo.setServiceState("0");
            }
        }else{
            return  BaseResponse.createByError(BaseEnum.IS_NOT_SERVICE.getResultMsg());
        }
        serviceInfo.setServiceState(this.serviceState(serviceDTO.getServiceName()));
        serviceInfo.setInsertTime(System.currentTimeMillis());
        serviceInfoMapper.insert(serviceInfo);
        return BaseResponse.createBySuccess();
    }

    public List<String> queryNetCar() throws IOException, InterruptedException {
        String[] cmd = {"sh", "-c","ns cat /proc/net/dev|grep -v face|grep -v Inter|tr : ' '|awk '{print $1}'"};
        ExecResult exec = ExecResult.exec(cmd);
        String stdout = exec.stdout;
        String[] split = stdout.split("\n");
        List<String> list = Arrays.asList(split);

        return list;
    }

    public String serviceState(String serviceName) throws IOException, InterruptedException {
        String[] command = new String[]{EntityUtils.CMDPARAM, "get_service_name", serviceName};
        CommandUtil.ExecReturn exec = CommandUtil.exec(command);
        logger.info("exec===>{}",exec.stdout);
        if (exec.exitCode != 0 && "".equals(exec.stdout)) {
            return "1";
        }
        if (!exec.stdout.trim().equals("failed")) {
            return "0";
        }
        return "1";
    }


    // 查询服务列表
    public List<ServiceInfo> queryService() {
        return serviceInfoMapper.selectList(null);
    }

    @Override
    public ServiceInfo selectOne(String id) {
        QueryWrapper<ServiceInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        ServiceInfo one = serviceInfoMapper.selectOne(queryWrapper);
        if (one == null) {
            throw new CustomException("304", "未查询到数据");
        }
        return one;
    }

    @Override
    public Page<ServiceInfo> findServiceInfoPage(int pageNum, int pageSize, String search) {
        LambdaQueryWrapper<ServiceInfo> wrapper = Wrappers.lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(ServiceInfo::getServiceName, search);
        }
        Page<ServiceInfo> page = new Page<>(pageNum, pageSize);
        Page<ServiceInfo> serviceInfoPage = serviceInfoMapper.selectPage(page, wrapper);
        return serviceInfoPage;
    }

    @Override
    public int delService(String id) {
        return serviceInfoMapper.deleteById(id);
    }
}
