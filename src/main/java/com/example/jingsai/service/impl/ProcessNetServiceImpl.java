package com.example.jingsai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.jingsai.mapper.ProcessNetMapper;
import com.example.jingsai.model.ProcessNet;
import com.example.jingsai.service.ProcessNetService;
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
public class ProcessNetServiceImpl implements ProcessNetService {

    @Autowired
    private ProcessNetMapper processNetMapper;

    @Override
    public List<ProcessNet> query() {
        return processNetMapper.selectList(null);
    }
}
