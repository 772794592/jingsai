package com.example.jingsai.initialize.task;

import com.example.jingsai.service.ProcessInfoService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProcessInfoRollBackTask implements Runnable {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProcessInfoService processInfoService;

    private final int max = 604800; // 7天，每秒1条
    private final int min = 432000; // 5天

    @Override
    public void run() {
        try {
            processInfoService.rollback(max, min);
        } catch (Exception e) {
            log.error("processInfo rollback fail={}", ExceptionUtils.getStackTrace(e));
        }
    }
}
