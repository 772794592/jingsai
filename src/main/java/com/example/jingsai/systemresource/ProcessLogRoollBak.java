package com.example.jingsai.systemresource;

import com.example.jingsai.systemresource.dao.ProcessInfoDao;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
@Order(value = 2)
public class ProcessLogRoollBak implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(ProcessLogRoollBak.class);
    @Resource
    private ProcessInfoDao processInfoDao;

    private static final Integer COUNT = 100000;

    @Override
    public void run(String... args) {
        new Thread(() -> {
            try {
                while (true) {
                    int totalCount = processInfoDao.queryTotalCount();
                    if (totalCount >= COUNT) {
                        processInfoDao.delProcessData();
                    }
                }
            } catch (Exception e) {
                log.info("del process old data error {}",ExceptionUtils.getMessage(e));
            } finally {
                try {
                    Thread.sleep(1080 * 10000L);
                } catch (InterruptedException e) {
                    // 异常后，中断线程
                    Thread.currentThread().interrupt();
                    log.info("sleep --> {}", ExceptionUtils.getMessage(e));
                }
            }
        }).start();

    }
}
