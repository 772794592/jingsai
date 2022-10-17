package com.example.jingsai.controller;


import com.example.jingsai.model.ProcessInfo;
import com.example.jingsai.service.ProcessInfoService;
import com.example.jingsai.utils.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @GetMapping("detail/{pid}")
    public BaseResponse detail(@PathVariable("pid") int pid) {
        ProcessInfo processInfo = processInfoService.detail(pid);
        return BaseResponse.createBySuccess(processInfo);
    }

    @GetMapping("page")
    public BaseResponse page(@RequestParam(value = "page", defaultValue = "1") int page,
                             @RequestParam(value = "size", defaultValue = "10") int size,
                             @RequestParam(value = "pid", defaultValue = "-1") int pid,
                             @RequestParam(value = "beginTm", defaultValue = "-1") long beginTm,
                             @RequestParam(value = "endTm", defaultValue = "-1") long endTm) {
        Map<String, Object> pageMap = processInfoService.page(page, size, pid, beginTm, endTm);
        return BaseResponse.createBySuccess(pageMap);
    }

}
