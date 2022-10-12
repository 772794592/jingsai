package com.example.jingsai.controller;


import com.example.jingsai.model.ProcessNet;
import com.example.jingsai.service.ProcessNetService;
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
@RequestMapping("/process_net")
public class ProcessNetController {

    @Autowired
    private ProcessNetService processNetService;

    @GetMapping("query")
    public BaseResponse query() {
        List<ProcessNet> list = processNetService.query();
        return BaseResponse.createBySuccess(list);
    }

}
