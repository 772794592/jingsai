package com.example.jingsai.systemresource.dao;

import com.example.jingsai.systemresource.pojo.ProcessInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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

    /***
     * 添加进程资源占用
     * @param processInfo
     * @return
     */
    @Insert("INSERT INTO process_info (pid,user,pr,ni,virt,res,shr,s,cpu,mem,time,command,record_time) VALUES " +
            "(#{pid},#{user},#{pr},#{ni},#{virt},#{res},#{shr},#{s},#{cpu},#{mem},#{time},#{command},#{record_time})")
    int addProcess(ProcessInfo processInfo);

    //TODO 日志回滚每10万条数据回滚5万条

}
