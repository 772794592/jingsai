package com.example.jingsai.initialize.task;

import com.example.jingsai.service.ProcessNetService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProcessNetRollBackTask implements Runnable {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProcessNetService processNetService;

    private final int max = 1200000;
    private final int min = 1000000;

    @Override
    public void run() {
        try {
            processNetService.rollback(max, min);
        } catch (Exception e) {
            log.error("processNet rollback fail={}", ExceptionUtils.getStackTrace(e));
        }
    }
}
