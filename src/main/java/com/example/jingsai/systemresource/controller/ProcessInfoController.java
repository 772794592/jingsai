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
     * 查看进程的占用系统资源
     * @param service
     * @param pid
     * @return
     */
    @GetMapping("query")
    public BaseResponse query(@RequestParam("service") String service, @RequestParam("pid") String pid)  {
        return processInfoService.queryByname(service, pid);
    }
}
