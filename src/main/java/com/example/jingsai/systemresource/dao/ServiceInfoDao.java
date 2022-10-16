package com.example.jingsai.systemresource.dao;

import com.example.jingsai.systemresource.pojo.ServiceInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author: shigw
 * @Date:2022-10-13 13:56
 */
@Mapper
public interface ServiceInfoDao {

    /***
     * 查询出所有服务信息
     * @return
     */
    @Select("SELECT * from service_process")
    List<ServiceInfo> query();

    /***
     * 添加服务
     * @param serviceInfo
     * @return
     */
    @Insert("INSERT into service_process (service_name,service_status,record_time) VALUES " +
            "(#{serviceName},#{serviceStatus},#{record_time})")
    int addService(ServiceInfo serviceInfo);

    /***
     * 删除服务
     * @param id
     * @return
     */
    @Delete("DELETE FROM service_process where id =#{id}")
    int  delService(int id);

    @Update("update service_process set service_status = #{serviceStatus} where id =#{id}")
    int updateStatus(@Param("serviceStatus") int serviceStatus,@Param("id") int id);
}
