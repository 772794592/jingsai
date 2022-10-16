package com.example.jingsai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.jingsai.constant.PageField;
import com.example.jingsai.mapper.ProcessInfoMapper;
import com.example.jingsai.model.ProcessInfo;
import com.example.jingsai.service.ProcessInfoService;
import com.example.jingsai.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveBatch(String stdout) {
        List<ProcessInfo> list = handle(stdout);
        processInfoMapper.saveBatch(list);
    }

    @Override
    public Map<String, Object> page(int page, int size, int pid, long beginTm, long endTm) {
        Page<ProcessInfo> pageInfo = new Page<>(page, size);
        QueryWrapper<ProcessInfo> queryWrapper = new QueryWrapper<>();

        if (pid != -1) {
            queryWrapper.eq("pid", pid);
        }
        if (beginTm != -1) {
            queryWrapper.gt("create_time", TimeUtil.format(beginTm));
        }
        if (endTm != -1) {
            queryWrapper.lt("create_time", TimeUtil.format(endTm));
        }

        IPage<ProcessInfo> processInfoIPage = processInfoMapper.selectPage(pageInfo, queryWrapper);

        Map<String, Object> pageMap = new HashMap<>(3);
        pageMap.put(PageField.TOTAL_RECORD, processInfoIPage.getTotal());
        pageMap.put(PageField.TOTAL_PAGE, processInfoIPage.getPages());
        pageMap.put(PageField.CURRENT_DATA, processInfoIPage.getRecords());
        return pageMap;
    }

    @Override
    public ProcessInfo detail(int pid) {
        return processInfoMapper.selectByMaxId(pid);
    }

    @Override
    public void rollback(int max, int min) {
        long total_count = processInfoMapper.selectCount(null);
        if (total_count >= max) {
            int delCount = max - min;
            processInfoMapper.rollback(delCount);
        }
    }

    private List<ProcessInfo> handle(String stdout) {

        List<ProcessInfo> list = Stream.of(stdout)
                .flatMap(out -> Stream.of(out.split("\\n")))
                .map(line -> {
                    String[] data = line.trim().split(" +");
                    ProcessInfo processInfo = new ProcessInfo();
                    processInfo.setPid(Integer.parseInt(data[0]));
                    processInfo.setUser(data[1]);
                    processInfo.setPr(Integer.parseInt(data[2]));
                    processInfo.setNi(Integer.parseInt(data[3]));
                    processInfo.setVirt(Integer.parseInt(data[4]));
                    processInfo.setRes(Integer.parseInt(data[5]));
                    processInfo.setShr(Integer.parseInt(data[6]));
                    processInfo.setS(data[7]);
                    processInfo.setCpu(Double.parseDouble(data[8]));
                    processInfo.setMem(Double.parseDouble(data[9]));
                    processInfo.setTime(data[10]);
                    processInfo.setCommand(data[11]);
                    processInfo.setCreateTime(LocalDateTime.now());

                    return processInfo;
                }).collect(Collectors.toList());

        return list;
    }

}
