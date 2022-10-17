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
@TableName("process_net")
public class ProcessNet implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Integer id;

    /**
     * 进程ID
     */
    private Integer pid;

    /**
     * 协议
     */
    private String proto;

    /**
     * 本地地址
     */
    private String localAddress;

    /**
     * 外部地址
     */
    private String foreignAddress;

    /**
     * 连接状态
     */
    private String state;

    /**
     * 进程ID和程序名称
     */
    private String pidProgramName;

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
    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }
    public String getProto() {
        return proto;
    }

    public void setProto(String proto) {
        this.proto = proto;
    }
    public String getLocalAddress() {
        return localAddress;
    }

    public void setLocalAddress(String localAddress) {
        this.localAddress = localAddress;
    }
    public String getForeignAddress() {
        return foreignAddress;
    }

    public void setForeignAddress(String foreignAddress) {
        this.foreignAddress = foreignAddress;
    }
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    public String getPidProgramName() {
        return pidProgramName;
    }

    public void setPidProgramName(String pidProgramName) {
        this.pidProgramName = pidProgramName;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ProcessNet{" +
            "id=" + id +
            ", pid=" + pid +
            ", proto=" + proto +
            ", localAddress=" + localAddress +
            ", foreignAddress=" + foreignAddress +
            ", state=" + state +
            ", pidProgramName=" + pidProgramName +
            ", createTime=" + createTime +
        "}";
    }
}
