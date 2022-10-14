package com.example.jingsai.systemresource.controller;

import com.example.jingsai.systemresource.pojo.ProcessInfo;
import com.example.jingsai.systemresource.service.ProcessInfoService;
import com.example.jingsai.utils.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.List;
/**
 * @author: shigw
 * @Date:2022-10-12 14:50
 */
@RestController
@RequestMapping("/jingsai/sysprocess")
public class ProcessInfoController {

    @Resource
    private ProcessInfoService processInfoService;

    /***
     * 根据条件查询出进程信息
     */
    @GetMapping("queryBytime")
    public BaseResponse query(@RequestParam("beginTime") String beginTime,@RequestParam("endTime")  String endTime,@RequestParam("serviceName")  String serviceName)  {
        return processInfoService.query(beginTime,endTime,serviceName);
    }

    /***
     * 查看进程的占用系统资源
     * @param service
     * @param pid
     * @return
     */
    @GetMapping("queryByname")
    public BaseResponse queryByname(@RequestParam("service") String service)  {
        return processInfoService.queryByname(service);
    }
}
