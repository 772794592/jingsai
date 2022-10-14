package com.example.jingsai.systemresource.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

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
    private String service_name;

    /**
     * 服务状态
     */
    private Integer service_status;

    /**
     *记录时间
     */
    private LocalDateTime record_time;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public Integer getService_status() {
        return service_status;
    }

    public void setService_status(Integer service_status) {
        this.service_status = service_status;
    }

    public LocalDateTime getRecord_time() {
        return record_time;
    }

    public void setRecord_time(LocalDateTime record_time) {
        this.record_time = record_time;
    }

    @Override
    public String toString() {
        return "ServiceInfo{" +
                "id=" + id +
                ", service_name='" + service_name + '\'' +
                ", service_status=" + service_status +
                ", record_time=" + record_time +
                '}';
    }
}
