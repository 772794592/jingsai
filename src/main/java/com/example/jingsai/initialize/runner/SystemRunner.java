package com.example.jingsai.initialize.runner;

import com.example.jingsai.initialize.task.ProcessInfoTask;
import com.example.jingsai.threadpool.ThreadPoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class SystemRunner implements CommandLineRunner {

    @Autowired
    private ThreadPoolService threadPoolService;

    @Autowired
    private ProcessInfoTask processInfoTask;

    @Override
    public void run(String... args) throws Exception {
        ScheduledExecutorService scheduledExecutorService = threadPoolService.getScheduledService();
        scheduledExecutorService.scheduleWithFixedDelay(processInfoTask, 10, 1, TimeUnit.SECONDS);
    }

}
