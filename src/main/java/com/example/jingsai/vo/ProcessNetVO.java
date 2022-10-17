package com.example.jingsai.vo;

import java.util.List;

public class ProcessNetVO {

    /**
     * tcp活跃的连接数
     */
    private int tcpActiveCount;

    /**
     * tcp使用过的连接数
     */
    private int tcpSocketCount;

    /**
     * 网卡流速
     */
    private long nicSpeed;

    /**
     * 网卡流量
     */
    private long nicTraffic;

    /**
     * 每个连接的详细信息
     */
    private List<ProcessNetInfoVO> processNetInfoVOList;

}
