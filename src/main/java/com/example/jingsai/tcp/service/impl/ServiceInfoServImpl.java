package com.example.jingsai.tcp.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.jingsai.tcp.common.Result;
import com.example.jingsai.tcp.dao.ServiceInfoMapper;
import com.example.jingsai.tcp.exception.CustomException;
import com.example.jingsai.tcp.pojo.ServiceInfo;
import com.example.jingsai.tcp.service.ServiceInfoService;
import com.example.jingsai.tcp.service.TcpService;
import com.example.jingsai.tcp.vo.ServiceVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 *
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

    public int addService(ServiceVo serviceVo) throws IOException, InterruptedException {

        String servicePid = tcpService.getPidByService(serviceVo.getServiceName());
logger.info("添加服务：服务pid==>{}",servicePid);
        ServiceInfo serviceInfo = new ServiceInfo();

        serviceInfo.setServiceName(serviceVo.getServiceName());
        serviceInfo.setNetName(serviceVo.getNetName());

        // 查服务状态
        // TODO: 2022/10/17 获取进程状态
        serviceInfo.setServiceState("0");

        serviceInfo.setInsertTime(new Date());
       return serviceInfoMapper.insert(serviceInfo);
    }

    // 查询服务列表
    public List<ServiceInfo> queryService(){
        return serviceInfoMapper.selectList(null);
    }

    @Override
    public ServiceInfo selectOne(String id) {
        QueryWrapper<ServiceInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        ServiceInfo one = serviceInfoMapper.selectOne(queryWrapper);
        if (one==null){
            throw new CustomException("304","未查询到数据");
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
