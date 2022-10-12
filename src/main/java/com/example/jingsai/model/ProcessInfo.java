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
 * @since 2022-10-12
 */
@TableName("process_info")
public class ProcessInfo implements Serializable {

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
     * 进程所有者用户
     */
    private String user;

    /**
     * 进程优先级，值越小优先级越高
     */
    private Integer pr;

    /**
     * 负值表示高优先级，正值表示低优先级
     */
    private Integer ni;

    /**
     * 进程使用的虚拟内存总量，单位kb
     */
    private Integer virt;

    /**
     * 进程使用的，未被换出的物理内存大小，单位kb
     */
    private Integer res;

    /**
     * 共享内存大小
     */
    private Integer shr;

    /**
     * 进程状态(D=不可中断的睡眠状态,R=运行,S=睡眠,T=跟踪/停止,Z=僵尸进程)
     */
    private String s;

    /**
     * CPU占用百分比
     */
    private Double cpu;

    /**
     * 进程使用的物理内存百分比
     */
    private Double mem;

    /**
     * 进程使用的CPU时间总计,单位1/100秒
     */
    private String time;

    /**
     * 命令名/命令行
     */
    private String command;

    /**
     * 记录的时间
     */
    private LocalDateTime recordTime;

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
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    public Integer getPr() {
        return pr;
    }

    public void setPr(Integer pr) {
        this.pr = pr;
    }
    public Integer getNi() {
        return ni;
    }

    public void setNi(Integer ni) {
        this.ni = ni;
    }
    public Integer getVirt() {
        return virt;
    }

    public void setVirt(Integer virt) {
        this.virt = virt;
    }
    public Integer getRes() {
        return res;
    }

    public void setRes(Integer res) {
        this.res = res;
    }
    public Integer getShr() {
        return shr;
    }

    public void setShr(Integer shr) {
        this.shr = shr;
    }
    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }
    public Double getCpu() {
        return cpu;
    }

    public void setCpu(Double cpu) {
        this.cpu = cpu;
    }
    public Double getMem() {
        return mem;
    }

    public void setMem(Double mem) {
        this.mem = mem;
    }
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
    public LocalDateTime getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(LocalDateTime recordTime) {
        this.recordTime = recordTime;
    }

    @Override
    public String toString() {
        return "ProcessInfo{" +
            "id=" + id +
            ", pid=" + pid +
            ", user=" + user +
            ", pr=" + pr +
            ", ni=" + ni +
            ", virt=" + virt +
            ", res=" + res +
            ", shr=" + shr +
            ", s=" + s +
            ", cpu=" + cpu +
            ", mem=" + mem +
            ", time=" + time +
            ", command=" + command +
            ", recordTime=" + recordTime +
        "}";
    }
}
