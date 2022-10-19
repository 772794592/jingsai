package com.example.jingsai.tcp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.jingsai.tcp.common.BaseEnum;
import com.example.jingsai.tcp.pojo.ServiceInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *
 * @author liwen <devinlive@163.com>
 * @version 1.0
 * @since 2022/10/17 11:03
 */
@Mapper
public interface ServiceInfoMapper extends BaseMapper<ServiceInfo> {

    @Select("select * from l_service_info where service_name = #{serviceName} ")
    ServiceInfo queryByName(String serviceName);
}
