package com.example.jingsai.systemresource.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.jingsai.systemresource.pojo.ProcessInfo;
import com.example.jingsai.systemresource.service.ProcessInfoService;
import com.example.jingsai.systemresource.utils.BaseResponse;
import com.example.jingsai.systemresource.utils.PageUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
     * 根据条件查询出历史进程信息
     */
    @PostMapping("queryBytime")
    public BaseResponse query(@RequestBody String data)  {
        JSONObject jsonObject = JSONObject.parseObject(data);
        Integer pageSize = jsonObject.getInteger("pageSize");
        Integer pageNum = jsonObject.getInteger("pageNum");
        String beginTime = jsonObject.getString("beginTime");
        String endTime = jsonObject.getString("endTime");
        String serviceName = jsonObject.getString("serviceName");
        PageUtil pageUtil = new PageUtil(pageNum, pageSize, processInfoService.query(beginTime, endTime, serviceName));
        return BaseResponse.createBySuccess(pageUtil);
    }

    /***
     * 实时查看进程的占用系统资源
     * @param serviceName
     * @return
     */
    @GetMapping("queryByname")
    public BaseResponse queryByname(String serviceName)  {
        return  processInfoService.queryByname(serviceName);
    }


}
