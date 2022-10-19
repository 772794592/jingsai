package com.example.jingsai.tcp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.jingsai.tcp.pojo.ServiceLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.springframework.context.annotation.Primary;

/**
 * <p>
 *
 * @author liwen <devinlive@163.com>
 * @version 1.0
 * @since 2022/10/18 12:05
 */
@Mapper
public interface ServiceLogMapper extends BaseMapper<ServiceLog> {

    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    @Insert("insert into l_service_log(service_pid,tcp_count,tcp_port,insert_time,service_name) " +
            "values (#{servicePid},#{tcpCount},#{tcpPort},#{insertTime},#{serviceName})")
    void save(ServiceLog log);
}
