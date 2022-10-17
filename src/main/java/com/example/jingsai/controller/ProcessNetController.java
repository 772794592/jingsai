package com.example.jingsai.controller;


import com.example.jingsai.model.ProcessNet;
import com.example.jingsai.service.ProcessNetService;
import com.example.jingsai.utils.BaseResponse;
import com.example.jingsai.utils.TimeUtil;
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
@RequestMapping("/process_net")
public class ProcessNetController {

    @Autowired
    private ProcessNetService processNetService;

    @GetMapping("detail/{pid}")
    public BaseResponse detail(@PathVariable("pid") int pid) {
        ProcessNet processNet = processNetService.detail(pid);
        return BaseResponse.createBySuccess(processNet);
    }

    @GetMapping("page")
    public BaseResponse page(@RequestParam(value = "page", defaultValue = "1") int page,
                             @RequestParam(value = "size", defaultValue = "10") int size,
                             @RequestParam(value = "pid", defaultValue = "-1") int pid,
                             @RequestParam(value = "beginTm", defaultValue = "-1") long beginTm,
                             @RequestParam(value = "endTm", defaultValue = "-1") long endTm) {
        Map<String, Object> pageMap = processNetService.page(page, size, pid, beginTm, endTm);
        return BaseResponse.createBySuccess(pageMap);
    }

    @GetMapping("statistics")
    public BaseResponse statistics(@RequestParam(value = "pid", defaultValue = "-1") int pid,
                                   @RequestParam(value = "beginTm", defaultValue = "-1") long beginTm,
                                   @RequestParam(value = "endTm", defaultValue = "-1") long endTm) {
        Map<String, Object> map = processNetService.statistics(pid, beginTm, endTm);
        return BaseResponse.createBySuccess(map);
    }

    public static void main(String[] args) {
        long tm = System.currentTimeMillis();
        System.out.println(tm);
        System.out.println(TimeUtil.format(tm));
    }

}
