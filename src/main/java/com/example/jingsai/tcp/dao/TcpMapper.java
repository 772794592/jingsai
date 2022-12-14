package com.example.jingsai.tcp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.jingsai.tcp.pojo.Message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;


import java.util.List;

/**
 * <p>
 *
 * @author liwen <devinlive@163.com>
 * @version 1.0
 * @since 2022-10-12 22:28
 */
@Mapper
public interface TcpMapper extends BaseMapper<Message> {
    /**
     * 通过pid查询所有tcp消息
     */
    @Select("select * from l_message where pid = #{pid}")
    List<Message> queryAll(String pid);

    // 插入
    @Insert("insert into l_message (type,local_address,foreign_address,state,pid,program,name,insert_time,service_log_id) " +
            "values(#{type}, #{localAddress}, #{foreignAddress}, " +
            "#{state}, #{pid}, #{program}, #{name}, #{insertTime}, #{serviceLogId})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
//    void insertMessage(String id, Message.ProtoType type,String localAddress,String foreignAddress,String state,String pid,String program);
    int insertMessage(Message message);

    @Select("SELECT m.* FROM l_message m \n" +
            "WHERE m.pid = #{pid} \n" +
            "and m.insert_time \n" +
            "BETWEEN #{beginTm}  and #{endTm}")
    List<Message> queryMessagePage(String pid , long beginTm, long endTm);

}
