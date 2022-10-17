package com.example.jingsai.tcp.controller;

import com.example.jingsai.tcp.common.Result;
import com.example.jingsai.tcp.pojo.Message;
import com.example.jingsai.tcp.pojo.ServiceInfo;
import com.example.jingsai.tcp.service.ServiceInfoService;
import com.example.jingsai.tcp.vo.ServiceVo;
import com.example.jingsai.tcp.service.TcpService;
import com.example.jingsai.tcp.vo.ServiceInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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

    private static final Logger logger = LoggerFactory.getLogger(TcpService.class);

    @Resource
    private TcpService tcpService;

    @Resource
    private ServiceInfoService serviceInfoService;

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
     *
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
    public String test() {
        return "test ok";
    }

    @RequestMapping("/queryAll/{pid}")
    public List<Message> queryAll(@PathVariable String pid) {
        return tcpService.queryAll(pid);
    }

    @RequestMapping("/serviceInfo/{id}")
    public Result<?> getServiceInfo(@PathVariable String id) throws IOException, InterruptedException {

        ServiceInfo serviceInfo = serviceInfoService.selectOne(id);

        String pid = tcpService.getPidByService(serviceInfo.getServiceName());
        logger.info("serviceInfo: 服务pid==>{}",pid);
        // 查tcp连接数
        int tcpEstablishedCount = tcpService.tcpEstablishedCount(pid);
        // 连接状态
        List<Message> tcpListState = tcpService.tcpList(pid);
        //端口
        List<String> ports = tcpService.tcpPort(pid);

        //封装vo
        ServiceInfoVo serviceInfoVo = new ServiceInfoVo();
        serviceInfoVo.setTcpCount(String.valueOf(tcpEstablishedCount));
        serviceInfoVo.setTcpState(tcpListState);
        serviceInfoVo.setTcpPort(ports);

        return Result.success(serviceInfoVo);
    }

    // 添加网络资源
    @RequestMapping("/addService")
    public Result<?> addNetResource(@RequestBody ServiceVo serviceVo) throws IOException, InterruptedException {
        int row = serviceInfoService.addService(serviceVo);
        return Result.success();
    }

    // 查询
    @RequestMapping("/queryService")
    public Result<?> queryService() {
        return Result.success(serviceInfoService.queryService());
    }

    // 删除
    @RequestMapping("/delService/{id}")
    public Result<?> delService(@PathVariable String id) {
        int row = serviceInfoService.delService(id);
        return Result.success();
    }

    // 查询/分页
    @RequestMapping("/findServiceInfoPage")
    public Result<?> findServiceInfoPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                         @RequestParam(defaultValue = "10") Integer pageSize,
                                         @RequestParam(defaultValue = "") String search) {
        return Result.success(serviceInfoService.findServiceInfoPage(pageNum, pageSize, search));
    }

}