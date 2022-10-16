package com.example.jingsai.systemresource;

import com.example.jingsai.systemresource.dao.ProcessInfoDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
@Order(value = 2)
public class ProcessLogRoollBak implements CommandLineRunner {

    private final static Logger log = LoggerFactory.getLogger(ProcessLogRoollBak.class);
    @Resource
    private ProcessInfoDao processInfoDao;

    private final static Integer COUNT = 100000;

    @Override
    public void run(String... args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        int totalCount = processInfoDao.queryTotalCount();
                        if (totalCount >= COUNT) {
                            processInfoDao.delProcessData();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    log.info("del process old data eroor");
                } finally {
                    try {
                        Thread.sleep(1080 * 10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }
}
