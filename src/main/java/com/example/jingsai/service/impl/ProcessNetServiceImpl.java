package com.example.jingsai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.jingsai.constant.PageField;
import com.example.jingsai.mapper.ProcessNetMapper;
import com.example.jingsai.model.ProcessNet;
import com.example.jingsai.service.ProcessNetService;
import com.example.jingsai.utils.TimeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class ProcessNetServiceImpl implements ProcessNetService {

    @Autowired
    private ProcessNetMapper processNetMapper;


    @Override
    public void saveBatch(String stdout) {
        List<ProcessNet> list = handle(stdout);
        processNetMapper.saveBatch(list);
    }

    @Override
    public Map<String, Object> page(int page, int size, int pid, long beginTm, long endTm) {
        Page<ProcessNet> pageInfo = new Page<>(page, size);
        QueryWrapper<ProcessNet> queryWrapper = new QueryWrapper<>();

        if (pid != -1) {
            queryWrapper.eq("pid", pid);
        }
        if (beginTm != -1) {
            queryWrapper.gt("create_time", TimeUtil.format(beginTm));
        }
        if (endTm != -1) {
            queryWrapper.lt("create_time", TimeUtil.format(endTm));
        }

        IPage<ProcessNet> processNetIPage = processNetMapper.selectPage(pageInfo, queryWrapper);

        Map<String, Object> pageMap = new HashMap<>(3);
        pageMap.put(PageField.TOTAL_RECORD, processNetIPage.getTotal());
        pageMap.put(PageField.TOTAL_PAGE, processNetIPage.getPages());
        pageMap.put(PageField.CURRENT_DATA, processNetIPage.getRecords());
        return pageMap;
    }

    @Override
    public ProcessNet detail(int pid) {
        return processNetMapper.selectByMaxId(pid);
    }

    @Override
    public Map<String, Object> statistics(int pid, long beginTm, long endTm) {

        List<ProcessNet> tcp = processNetMapper.selectTcp(pid, beginTm, endTm);
        List<ProcessNet> tcpEstablished = processNetMapper.selectTcpEstablished(pid, beginTm, endTm);

        Map<String, Object> netstat = new HashMap<>();
        netstat.put("tcp", tcp.size());
        netstat.put("tcpEstablished", tcpEstablished.size());

        return netstat;
    }

    @Override
    public void rollback(int max, int min) {
        long total_count = processNetMapper.selectCount(null);
        if (total_count >= max) {
            int delCount = max - min;
            processNetMapper.rollback(delCount);
        }
    }

    private List<ProcessNet> handle(String stdout) {

        List<ProcessNet> list = Stream.of(stdout.trim())
                .flatMap(out -> Stream.of(out.split("\\n")))
                .map(line -> {
                    String[] data = line.trim().split(" +");
                    ProcessNet processNet = new ProcessNet();

                    if (data[4].contains("/")) {
                        String pid = data[4].split("/")[0];
                        processNet.setPid(Integer.parseInt(pid));
                    }

                    processNet.setProto(data[0]);
                    processNet.setLocalAddress(data[1]);
                    processNet.setForeignAddress(data[2]);
                    processNet.setState(data[3]);
                    processNet.setPidProgramName(data[4]);
                    processNet.setCreateTime(LocalDateTime.now());

                    return processNet;
                }).collect(Collectors.toList());

        return list;
    }
}
