package com.example.jingsai.tcp.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 *
 * @author liwen <devinlive@163.com>
 * @version 1.0
 * @since 2022/10/16 11:23
 */
@TableName("l_service_log")
public class ServiceLog {

    @TableId(type = IdType.AUTO)
    private String id;
    private String servicePid;

    private String serviceName;

    private String tcpCount;
    private String tcpPort;
//    private List<Message> tcpState;
    private Long insertTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServicePid() {
        return servicePid;
    }

    public void setServicePid(String servicePid) {
        this.servicePid = servicePid;
    }

    public String getTcpCount() {
        return tcpCount;
    }

    public void setTcpCount(String tcpCount) {
        this.tcpCount = tcpCount;
    }


    public String getTcpPort() {
        return tcpPort;
    }

    public void setTcpPort(String tcpPort) {
        this.tcpPort = tcpPort;
    }

    public Long getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Long insertTime) {
        this.insertTime = insertTime;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
