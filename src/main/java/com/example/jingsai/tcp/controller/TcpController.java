package com.example.jingsai.tcp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.jingsai.netcard.service.NicInfoService;
import com.example.jingsai.tcp.common.Result;
import com.example.jingsai.tcp.dao.ServiceInfoMapper;
import com.example.jingsai.tcp.dao.ServiceLogMapper;
import com.example.jingsai.tcp.dao.TcpMapper;
import com.example.jingsai.tcp.pojo.Message;
import com.example.jingsai.tcp.pojo.ServiceInfo;
import com.example.jingsai.tcp.pojo.ServiceLog;
import com.example.jingsai.tcp.service.ServiceInfoService;
import com.example.jingsai.tcp.service.ServiceLogService;
import com.example.jingsai.tcp.service.TcpService;
import com.example.jingsai.tcp.vo.ServiceDTO;
import com.example.jingsai.tcp.vo.ServiceInfoVo;
import com.example.jingsai.utils.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @Resource
    private ServiceLogService serviceLogService;
    @Resource
    private ServiceLogMapper serviceLogMapper;
    @Resource
    private TcpMapper tcpMapper;

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

    @GetMapping("/getTcpInfo/{serviceLogId}")
    public List<Message> getTcpInfo(@PathVariable Long serviceLogId) throws IOException, InterruptedException {
        List<Message> messages = tcpService.querMessageByServiceLogId(serviceLogId, true);
        return messages;
    }

    @RequestMapping("/test")
    public Result<?> test() {
        List<Message> messages = tcpMapper.queryMessagePage("1526", 20221018100716L, 20221018101651L);

        return Result.success(messages);
    }

    @RequestMapping("/queryAllPage")
    public Result<?> queryAllPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                  @RequestParam(defaultValue = "10") Integer pageSize,
                                  @RequestParam(defaultValue = "") String search) {
        return Result.success(tcpService.queryAllPage(pageNum, pageSize, search));
    }


    @RequestMapping("/serviceLog")
    public Result<?> getServiceLog(@RequestParam String id,
                                   @RequestParam(defaultValue = "1") int pageNum,
                                   @RequestParam(defaultValue = "10") int pageSize,
                                   @RequestParam(defaultValue = "") String beginTm,
                                   @RequestParam(defaultValue = "") String endTm) throws IOException, InterruptedException {

        ServiceInfo serviceInfo = serviceInfoService.selectOne(id);

        Page<ServiceLog> serviceLogPage = serviceLogService.queryServiceLogPageByName(serviceInfo.getServiceName(), pageNum, pageSize);

        return Result.success(serviceLogPage);
    }


    // 添加网络资源
    @PostMapping("/addService")
    public BaseResponse addNetResource(@RequestBody ServiceDTO serviceDTO) throws IOException, InterruptedException {
        return serviceInfoService.addService(serviceDTO);
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
