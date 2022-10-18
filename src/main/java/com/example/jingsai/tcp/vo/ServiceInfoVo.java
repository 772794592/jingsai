package com.example.jingsai.tcp.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.jingsai.tcp.pojo.Message;

import java.util.Date;
import java.util.List;

/**
 *
 * <p>
 *
 * @author liwen <devinlive@163.com>
 * @version 1.0
 * @since 2022/10/16 11:23
 */
public class ServiceInfoVo {

//    String id;
//    String serviceName;
//    String netName;

    private String tcpCount;
    private List<Message> tcpState;
    private List<String> tcpPort;
    /***
     *网卡流速
     */
//    private long nicSpeed;

    /***
     *网卡流量
     */
//    private long nicTraffic;

    Long insertTime;


    public ServiceInfoVo() {
    }

    public String getTcpCount() {
        return tcpCount;
    }

    public void setTcpCount(String tcpCount) {
        this.tcpCount = tcpCount;
    }

    public List<Message> getTcpState() {
        return tcpState;
    }

    public void setTcpState(List<Message> tcpState) {
        this.tcpState = tcpState;
    }

    public List<String> getTcpPort() {
        return tcpPort;
    }

    public void setTcpPort(List<String> tcpPort) {
        this.tcpPort = tcpPort;
    }


    public Long getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Long insertTime) {
        this.insertTime = insertTime;
    }
}
