package com.example.jingsai.service.impl;

import com.example.jingsai.mapper.ProcessInfoMapper;
import com.example.jingsai.model.ProcessInfo;
import com.example.jingsai.service.ProcessInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hejie
 * @since 2022-10-12
 */
@Service
public class ProcessInfoServiceImpl implements ProcessInfoService {

    @Autowired
    private ProcessInfoMapper processInfoMapper;

    @Override
    public List<ProcessInfo> query() {
        return processInfoMapper.selectList(null);
    }
}
