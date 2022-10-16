package com.example.jingsai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.jingsai.model.ProcessNet;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hejie
 * @since 2022-10-12
 */
public interface ProcessNetMapper extends BaseMapper<ProcessNet> {

    int saveBatch(List<ProcessNet> list);

    ProcessNet selectByMaxId(int pid);

    void rollback(int delCount);
}