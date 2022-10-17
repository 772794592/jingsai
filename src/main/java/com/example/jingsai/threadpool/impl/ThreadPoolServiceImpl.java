package com.example.jingsai.threadpool.impl;

import com.baomidou.mybatisplus.generator.IFill;
import com.example.jingsai.threadpool.ThreadPoolService;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ThreadPoolServiceImpl implements ThreadPoolService {

    private volatile ScheduledExecutorService scheduledExecutorService;

    @Override
    public ScheduledExecutorService getScheduledService() {
        if (scheduledExecutorService == null) {
            synchronized (this) {
                if (scheduledExecutorService == null) {
                    ThreadFactory threadFactory = threadFactory("Scheduled Task Threads", "JINGSAI-SCHEDULED-");
                    scheduledExecutorService = Executors.newScheduledThreadPool(22, threadFactory);
                }
            }
        }

        return scheduledExecutorService;
    }

    @Override
    public ExecutorService createCachedThreadPool(String threadGroupName, String threadNamePrefix, int coreSize, int maxSize) {

        ThreadFactory threadFactory = threadFactory(threadGroupName, threadNamePrefix);

        return new ThreadPoolExecutor(coreSize, maxSize, 60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(), threadFactory);
    }

    @Override
    public ExecutorService createFixedThreadPool(String threadGroupName, String threadNamePrefix, int nThreads, int queueSize) {

        ThreadFactory threadFactory = threadFactory(threadGroupName, threadNamePrefix);

        return new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(queueSize), threadFactory);
    }

    private ThreadFactory threadFactory(String threadGroupName, String threadNamePrefix) {
        ThreadGroup threadGroup = new ThreadGroup(threadGroupName);
        ThreadFactory threadFactory = new ThreadFactory() {
            AtomicInteger id = new AtomicInteger();

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(threadGroup, r, threadNamePrefix + id.getAndIncrement());
            }
        };
        return threadFactory;
    }
}
