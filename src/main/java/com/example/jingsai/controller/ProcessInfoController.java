package com.example.jingsai.controller;


import com.example.jingsai.model.ProcessInfo;
import com.example.jingsai.service.ProcessInfoService;
import com.example.jingsai.utils.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hejie
 * @since 2022-10-12
 */
@RestController
@RequestMapping("/process_info")
public class ProcessInfoController {

    @Autowired
    private ProcessInfoService processInfoService;

    @GetMapping("query")
    public BaseResponse query() {
        List<ProcessInfo> list = processInfoService.query();
        return BaseResponse.createBySuccess(list);
    }

}
