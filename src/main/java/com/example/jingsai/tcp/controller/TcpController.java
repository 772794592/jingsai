package com.example.jingsai.tcp.controller;

import com.example.jingsai.tcp.pojo.Message;
import com.example.jingsai.tcp.service.TcpService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 *
 * @author liwen <devinlive@163.com>
 * @version 1.0
 * @since 2022-10-12 22:44
 */
@RestController
@RequestMapping("/tcp")
public class TcpController {

    @Resource
    private TcpService tcpService;

    /**
     * 通过服务名获取服务pid
     *
     * @param serName
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    @GetMapping("/getPid/{serviceName}")
    public String getPidByService(@PathVariable("serviceName") String serName) throws IOException, InterruptedException {
        return tcpService.getPidByService(serName);
    }

    /**
     * 通过pid获取tcp连接数
     * @return
     */
    @GetMapping("/getTcpCount/{pid}")
    public String getTcpCount(@PathVariable String pid) throws IOException, InterruptedException {
        return String.valueOf(tcpService.tcpEstablishedCount(pid));
    }
    /**
     * 根据进程pid获取进程tcp连接状态
     */
    @GetMapping("/getTcpState/{pid}")
    public List<Message> getTcpState(@PathVariable String pid) throws IOException, InterruptedException {
        List<Message> list = tcpService.tcpList(pid);
        return list;
    }
    /**
     * 根据进程pid获取进程tcp端口
     */
    @GetMapping("/getTcpPort/{pid}")
    public List<String> TcpPort(@PathVariable String pid) throws IOException, InterruptedException {
        List<String> tcpPorts = tcpService.tcpPort(pid);
        return tcpPorts;
    }


    @RequestMapping("/test")
    public String test(){
        return "test ok";
    }

    @RequestMapping("/queryAll/{pid}")
    public List<Message> queryAll(@PathVariable String pid){
        return tcpService.queryAll(pid);
    }



}
