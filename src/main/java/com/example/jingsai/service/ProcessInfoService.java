package com.example.jingsai.service;

import com.example.jingsai.model.ProcessInfo;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hejie
 * @since 2022-10-12
 */
public interface ProcessInfoService {

    void saveBatch(String stdout);

    Map<String, Object> page(int i, int page, int size, long beginTm, long endTm);

    ProcessInfo detail(int pid);

    void rollback(int max, int min);
}
