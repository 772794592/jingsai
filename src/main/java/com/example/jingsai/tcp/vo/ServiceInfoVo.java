package com.example.jingsai.tcp.vo;

import com.example.jingsai.tcp.pojo.Message;

import java.util.List;

/**
 * <p>
 *
 * @author liwen <devinlive@163.com>
 * @version 1.0
 * @since 2022/10/16 11:23
 */
public class ServiceInfoVo {

    private String id;
    private String pid;
    private String tcpCount;
    private StringBuilder tcpPort;
    private List<Message> tcpState;
    private Long insertTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getTcpCount() {
        return tcpCount;
    }

    public void setTcpCount(String tcpCount) {
        this.tcpCount = tcpCount;
    }

    public StringBuilder getTcpPort() {
        return tcpPort;
    }

    public void setTcpPort(StringBuilder tcpPort) {
        this.tcpPort = tcpPort;
    }

    public List<Message> getTcpState() {
        return tcpState;
    }

    public void setTcpState(List<Message> tcpState) {
        this.tcpState = tcpState;
    }

    public Long getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Long insertTime) {
        this.insertTime = insertTime;
    }
}
