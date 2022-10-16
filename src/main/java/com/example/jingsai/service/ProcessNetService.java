package com.example.jingsai.service;

import com.example.jingsai.model.ProcessNet;

import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author hejie
 * @since 2022-10-12
 */
public interface ProcessNetService {

    void saveBatch(String stdout);

    Map<String, Object> page(int page, int size, int pid, long beginTm, long endTm);

    ProcessNet detail(int pid);

    Map<String, Object> statistics(int pid, long beginTm, long endTm);

    void rollback(int max, int min);

}
