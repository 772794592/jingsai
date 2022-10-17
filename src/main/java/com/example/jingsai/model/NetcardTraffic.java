package com.example.jingsai.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author hejie
 * @since 2022-10-14
 */
@TableName("netcard_traffic")
public class NetcardTraffic implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Integer id;

    /**
     * 网卡名称
     */
    private Integer nic;

    /**
     * 网卡状态
     */
    private String status;

    /**
     * 接收流速
     */
    private Long rx;

    /**
     * 发送流速
     */
    private Long tx;

    /**
     * 接收总流量
     */
    private Long rxBytes;

    /**
     * 发送总流量
     */
    private Long txBytes;

    /**
     * 记录的时间
     */
    private LocalDateTime createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getNic() {
        return nic;
    }

    public void setNic(Integer nic) {
        this.nic = nic;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Long getRx() {
        return rx;
    }

    public void setRx(Long rx) {
        this.rx = rx;
    }
    public Long getTx() {
        return tx;
    }

    public void setTx(Long tx) {
        this.tx = tx;
    }
    public Long getRxBytes() {
        return rxBytes;
    }

    public void setRxBytes(Long rxBytes) {
        this.rxBytes = rxBytes;
    }
    public Long getTxBytes() {
        return txBytes;
    }

    public void setTxBytes(Long txBytes) {
        this.txBytes = txBytes;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "NetcardTraffic{" +
            "id=" + id +
            ", nic=" + nic +
            ", status=" + status +
            ", rx=" + rx +
            ", tx=" + tx +
            ", rxBytes=" + rxBytes +
            ", txBytes=" + txBytes +
            ", createTime=" + createTime +
        "}";
    }
}
