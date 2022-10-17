package com.example.jingsai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.jingsai.model.ProcessInfo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hejie
 * @since 2022-10-12
 */
public interface ProcessInfoMapper extends BaseMapper<ProcessInfo> {

    int saveBatch(List<ProcessInfo> list);

    ProcessInfo selectByMaxId(int pid);

    void rollback(int delCount);
}
