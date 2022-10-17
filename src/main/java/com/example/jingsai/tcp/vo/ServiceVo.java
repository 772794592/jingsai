package com.example.jingsai.tcp.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * 服务vo
 * <p>
 *
 * @author liwen <devinlive@163.com>
 * @version 1.0
 * @since 2022/10/17 9:58
 */
public class ServiceVo {

String serviceName;
String netName;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getNetName() {
        return netName;
    }

    public void setNetName(String netName) {
        this.netName = netName;
    }

    @Override
    public String toString() {
        return "ServiceVo{" +
                "serviceName='" + serviceName + '\'' +
                ", netName='" + netName + '\'' +
                '}';
    }
}
