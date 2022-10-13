package com.example.jingsai.systemresource.pojo;

import java.io.Serializable;

/**
 * @author: shigw
 * @Date:2022-10-13 13:53
 */
public class ServiceInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Integer id;

    /**
     * 服务名称
     */
    private String serviceName;

    /**
     * 服务状态
     */
    private Integer serviceStatus;

    /**
     *记录时间
     */
    private Integer recordTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Integer getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(Integer serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    public Integer getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Integer recordTime) {
        this.recordTime = recordTime;
    }

    @Override
    public String toString() {
        return "ServiceInfo{" +
                "id=" + id +
                ", serviceName='" + serviceName + '\'' +
                ", serviceStatus=" + serviceStatus +
                ", recordTime=" + recordTime +
                '}';
    }
}
