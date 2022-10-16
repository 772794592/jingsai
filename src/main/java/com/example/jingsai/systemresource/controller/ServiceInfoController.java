package com.example.jingsai.systemresource.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.jingsai.systemresource.pojo.ServiceInfo;
import com.example.jingsai.systemresource.service.ServiceInfoService;
import com.example.jingsai.systemresource.utils.BaseResponse;
import com.example.jingsai.systemresource.utils.PageUtil;
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
    public BaseResponse query(@RequestBody String data)  {
        JSONObject jsonObject = JSONObject.parseObject(data);
        Integer pageSize = jsonObject.getInteger("pageSize");
        Integer pageNum = jsonObject.getInteger("pageNum");
        PageUtil pageUtil = new PageUtil(pageNum, pageSize,serviceInfoService.query());
        System.out.println(pageUtil);
        return BaseResponse.createBySuccess(pageUtil);
    }

    @PostMapping("add")
    public BaseResponse addService(@RequestBody ServiceInfo serviceInfo){
        return serviceInfoService.addService(serviceInfo);
    }

    @PostMapping("del")
    public BaseResponse delService(@RequestParam int id){
        return serviceInfoService.delService(id);
    }
}
