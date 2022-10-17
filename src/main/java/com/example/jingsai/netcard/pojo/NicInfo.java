package com.example.jingsai.netcard.pojo;

import java.io.Serializable;

public class NicInfo  implements Serializable {

    /***
     *网卡名称
     */
    private String nicName;

    /***
     *网卡流速
     */
    private long nicSpeed;

    /***
     *网卡流量
     */
    private long nicTraffic;

    /***
     *网卡状态
     */
    private int nicStatus;

    public String getNicName() {
        return nicName;
    }

    public void setNicName(String nicName) {
        this.nicName = nicName;
    }


    public long getNicSpeed() {
        return nicSpeed;
    }

    public void setNicSpeed(long nicSpeed) {
        this.nicSpeed = nicSpeed;
    }

    public long getNicTraffic() {
        return nicTraffic;
    }

    public void setNicTraffic(long nicTraffic) {
        this.nicTraffic = nicTraffic;
    }

    public int getNicStatus() {
        return nicStatus;
    }

    public void setNicStatus(int nicStatus) {
        this.nicStatus = nicStatus;
    }

    @Override
    public String toString() {
        return "NicInfo{" +
                "nicName='" + nicName + '\'' +
                ", nicSpeed='" + nicSpeed + '\'' +
                ", nicTraffic='" + nicTraffic + '\'' +
                ", nicStatus='" + nicStatus + '\'' +
                '}';
    }
}
