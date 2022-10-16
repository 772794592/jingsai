package com.example.jingsai.systemresource.dao;

import com.example.jingsai.systemresource.pojo.ProcessInfo;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * @author: shigw
 * @Date:2022-10-12 14:36
 */
@Mapper
public interface ProcessInfoDao {

    /***
     * 查询出所有进程的资源占用情况
     * @return
     */
    @Select("select * from process_info")
    List<ProcessInfo> queryProcess();
    //@Select("SELECT * FROM `process_info` where  service_name = #{serviceName} and record_time BETWEEN  #{beginTime}  AND #{endTime} ")
    List<ProcessInfo> query(String beginTime,String endTime,String serviceName);

    /***
     * 添加进程资源占用
     * @param processInfo
     * @return
     */
    @Insert("INSERT INTO process_info (pid,service_name,user,pr,ni,virt,res,shr,s,cpu,mem,time,command,record_time) VALUES " +
            "(#{pid},#{service_name},#{user},#{pr},#{ni},#{virt},#{res},#{shr},#{s},#{cpu},#{mem},#{time},#{command},#{recordTime})")
    int addProcess(ProcessInfo processInfo);

    /***
     * 查询进程最新的一条数据
     * @param serviceName
     * @return
     */
    @Select("SELECT * FROM process_info WHERE   id = (SELECT MAX(id) FROM process_info WHERE service_name =#{serviceName})")
    ProcessInfo queryMaxData(String serviceName);

    /***
     * 查询数据总数
     * @return
     */
    @Select("select count(id) from process_info")
    int queryTotalCount();

    /***
     * 每十万条删除5万旧数据保留5万最新数据
     * @return
     */
    @Delete("DELETE FROM process_info WHERE id in (SELECT id FROM ( SELECT id FROM process_info ORDER BY id ASC LIMIT 0, 50000)  t)")
    int delProcessData();
}
