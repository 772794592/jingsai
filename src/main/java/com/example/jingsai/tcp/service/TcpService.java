package com.example.jingsai.tcp.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.jingsai.tcp.pojo.Message;
import org.apache.ibatis.annotations.Select;

import java.io.IOException;
import java.util.List;

/**
 * tcp服务
 * <p>
 *
 * @author liwen <devinlive@163.com>
 * @version 1.0
 * @since 2022-10-11 16:51
 */
public interface TcpService {
    /**
     * 获取进程状态
     */

    /**
     * 根据服务名称获取进程pid
     */
    String getPidByService(String serviceName) throws IOException, InterruptedException;

    /**
     * 根据进程pid获取进程tcp连接数
     */
    int tcpEstablishedCount(String pid) throws IOException, InterruptedException;

    /**
     * 根据进程pid获取进程tcp连接状态
     */
    List<Message> tcpList(String pid,boolean insertDb) throws IOException, InterruptedException;
    /**
     * 根据进程pid获取进程tcp端口
     * @return
     */
    StringBuilder tcpPort(String pid) throws IOException, InterruptedException;



    Page<Message> queryAllPage(int pageNum, int pageSize, String search);
}
