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
//@TableName("l_test_vo")
public class ServiceInfoVo {

    String id;
    String serviceName;
    String servicePid;
    String serviceState;
    String netName;
    String tcpCount;
    List<Message> tcpState;
    List<String> tcpPort;
    Date insertTime;


    public ServiceInfoVo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServicePid() {
        return servicePid;
    }

    public void setServicePid(String servicePid) {
        this.servicePid = servicePid;
    }

    public String getServiceState() {
        return serviceState;
    }

    public void setServiceState(String serviceState) {
        this.serviceState = serviceState;
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

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public String getNetName() {
        return netName;
    }

    public void setNetName(String netName) {
        this.netName = netName;
    }
}
