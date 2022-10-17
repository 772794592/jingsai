package com.example.jingsai.netcard.controller;


import com.example.jingsai.netcard.service.NicInfoService;
import com.example.jingsai.systemresource.utils.BaseResponse;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/jingsai/nic")
public class NicinfoController {


    @Resource
    private NicInfoService nicInfoService;


    @RequestMapping("queryNic")
    public BaseResponse getNicInfoData(@RequestBody List<String> nicLists){

        return nicInfoService.getNicInfoData(nicLists);
    }

}
