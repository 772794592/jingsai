package com.example.jingsai.systemresource.controller;

import com.example.jingsai.systemresource.pojo.ServiceInfo;
import com.example.jingsai.systemresource.service.ServiceInfoService;
import com.example.jingsai.utils.BaseResponse;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: shigw
 * @Date:2022-10-13 14:45
 */
@RestController
@RequestMapping("/jingsai/sysservice")
public class ServiceInfoController {

    @Resource
    private ServiceInfoService serviceInfoService;

    @GetMapping("query")
    public BaseResponse query()  {
        return serviceInfoService.query();
    }

    @PostMapping("add")
    public BaseResponse addService(@RequestBody ServiceInfo serviceInfo){
        return serviceInfoService.addService(serviceInfo);
    }
}
