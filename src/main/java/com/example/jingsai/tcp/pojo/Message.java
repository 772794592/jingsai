package com.example.jingsai.tcp.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 消息实体
 * <p>
 *
 * @author liwen <devinlive@163.com>
 * @version 1.0
 * @since 2022-10-13 13:48
 */
@TableName("l_message")
public class Message {
    /*
     * Proto Recv-Q Send-Q Local Address           Foreign Address         State       PID/Program name
     * */

    // 主键
    @TableId(type = IdType.AUTO)
    private String id;
    // 协议类型
    private ProtoType type;
    // 本机地址
    private String localAddress;
    // 远程地址
    private String foreignAddress;
    // 连接状态
    private String state;
    // 进程pid
    private String pid;
//    private String serviceName;
    // 进程参数
    private String program;
    // 进程name
    private String name;
    // 插入时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Long insertTime;

    public enum ProtoType {
        tcp,
        udp,
        tcp6,
        udp6,
        unix

    }

    public static class Builder {

        private final Message message;

//        // 协议类型
//        private ProtoType type;
//        // 本机地址
//        private String localAddress;
//        // 远程地址
//        private String foreignAddress;
//        // 连接状态
//        private String state;
//        // 进程pid
//        private String pid;
//        // 进程参数
//        private String program;
//        // 进程name
//        private String name;
//
//        private Date insert;

        public Builder() {
            message = new Message();
        }

        public Builder type(ProtoType type) {
            message.type = type;
            return this;
        }

        public Builder localAddress(String localAddress) {
            message.localAddress = localAddress;
            return this;
        }

        public Builder foreignAddress(String foreignAddress) {
            message.foreignAddress = foreignAddress;
            return this;
        }

        public Builder state(String state) {
            message.state = state;
            return this;
        }

        public Builder pid(String pid) {
            message.pid = pid;
            return this;
        }

        public Builder program(String program) {
            message.program = program;
            return this;
        }

        public Builder name(String name) {
            message.name = name;
            return this;
        }
//        public Builder serviceName(String serviceName) {
//            message.serviceName = serviceName;
//            return this;
//        }
        public Builder insertTime(Long insert) {
            message.insertTime = insert;
            return this;
        }

        public Message build() {
            return message;
        }

    }

    public String getId() {
        return id;
    }

    public ProtoType getType() {
        return type;
    }

    public String getLocalAddress() {
        return localAddress;
    }

    public String getForeignAddress() {
        return foreignAddress;
    }

    public String getState() {
        return state;
    }

    public String getPid() {
        return pid;
    }

    public String getProgram() {
        return program;
    }

    public String getName() {
        return name;
    }



    public Long getInsertTime() {
        return insertTime;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setInsertTime(Long insertTime) {
        this.insertTime = insertTime;
    }



    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", type=" + type +
                ", localAddress='" + localAddress + '\'' +
                ", foreignAddress='" + foreignAddress + '\'' +
                ", state='" + state + '\'' +
                ", pid='" + pid + '\'' +
                ", program='" + program + '\'' +
                ", name='" + name + '\'' +
                ", insertTime=" + insertTime +
                '}';
    }
}
