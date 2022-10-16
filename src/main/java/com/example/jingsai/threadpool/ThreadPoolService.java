package com.example.jingsai.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

public interface ThreadPoolService {

    ScheduledExecutorService getScheduledService();

    ExecutorService createCachedThreadPool(String threadGroupName, String threadNamePrefix, int coreSize, int maxSize);

    ExecutorService createFixedThreadPool(String threadGroupName, String threadNamePrefix, int nThreads, int queueSize);

}
