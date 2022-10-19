package com.example.jingsai.tcp.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.jingsai.tcp.common.BaseEnum;
import com.example.jingsai.tcp.dao.ServiceInfoMapper;
import com.example.jingsai.tcp.dao.ServiceLogMapper;
import com.example.jingsai.tcp.dao.TcpMapper;
import com.example.jingsai.tcp.exception.CustomException;
import com.example.jingsai.tcp.pojo.Message;
import com.example.jingsai.tcp.pojo.ServiceLog;
import com.example.jingsai.tcp.service.ServiceLogService;
import com.example.jingsai.tcp.service.TcpService;
import com.example.jingsai.tcp.utils.ExecResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * tcp服务实现类
 * <p>
 *
 * @author liwen <devinlive@163.com>
 * @version 1.0
 * @since 2022-10-12 22:26
 */
@Service
public class TcpServiceImpl implements TcpService {

    private static final Logger logger = LoggerFactory.getLogger(TcpService.class);

    @Resource
    private TcpMapper tcpMapper;
    @Resource
    private ServiceInfoMapper serviceInfoMapper;
    @Resource
    private ServiceLogMapper serviceLogMapper;

    @Override
    public String getPidByService(String serviceName) throws IOException, InterruptedException {
        Assert.hasText(serviceName, "服务名不能为空");
//        String[] cmd = {"sh", "-c", "pgrep -f " + serviceName};
        String[] cmd = {"sh", "-c", "systemctl status " + serviceName + " |grep 'Main PID'|awk '{print $3}'"};
        ExecResult result = ExecResult.exec(cmd);
        if (result.exitCode != 0) {
            throw new CustomException("303", "未找到该服务");
        }
        // 按空格截取字符串
        String replace = result.stdout.replace("\n", "");
        return replace;
    }

    @Override
    public String getPidByServiceLogId(Long serviceLogId) throws IOException, InterruptedException {



        return null;
    }

    @Override
    public int tcpEstablishedCount(String pid) throws IOException, InterruptedException {
        if (!StringUtils.hasLength(pid)) {
            throw new CustomException(BaseEnum.PID_ISNULL.getResultCode(), BaseEnum.PID_ISNULL.getResultMsg());
        }
        String[] cmd = {"sh", "-c", "netstat -anp|grep ESTABLISHED|grep " + pid + "|wc -l"};
        ExecResult result = ExecResult.exec(cmd);
        String[] count = result.stdout.split(String.valueOf('\n'));
// TODO: 2022/10/14 to fix
        return Integer.parseInt(count[0]);
    }

    @Override
    public List<Message> tcpList(String pid, boolean insertDb, Long serviceLogId) throws IOException, InterruptedException {
        if (!StringUtils.hasLength(pid)) {
            throw new CustomException(BaseEnum.PID_ISNULL.getResultCode(), BaseEnum.PID_ISNULL.getResultMsg());
        }
        String[] cmd = {"sh", "-c", "netstat -anp|grep -v LISTEN|grep " + pid + "|awk '{print $1,$4,$5,$6,$7,$8}'"};

        ExecResult exec = ExecResult.exec(cmd);
        String stdout = exec.stdout;
        BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(stdout.getBytes()), StandardCharsets.UTF_8));
        String line;
        ArrayList<Message> messageList = new ArrayList<>();
        while ((line = br.readLine()) != null) {

            Message vo = this.convertLine(line, serviceLogId);
            if (vo != null) {
                vo.setPid(pid);

                // 入库
                if (insertDb) {
                    vo.setInsertTime(System.currentTimeMillis());
                    tcpMapper.insertMessage(vo);
                }
                messageList.add(vo);
            }
        }

        return messageList;
    }

    public int insertMessage(Message message) {
        if (message == null) {
            throw new CustomException();
        }
        message.setInsertTime(System.currentTimeMillis());
        return tcpMapper.insertMessage(message);
    }

    @Override
    public StringBuilder tcpPort(String pid) throws IOException, InterruptedException {
        List<Message> tcpList = this.tcpList(pid, false, 0L);
        StringBuilder stringBuilder = new StringBuilder();
        for (Message message : tcpList) {
            String localAddress = message.getLocalAddress();
            // 判断协议不是unix，截取端口号
            if (!Message.ProtoType.unix.equals(message.getType())) {

                String[] locals = localAddress.split(":");
                stringBuilder.append(locals[1]).append(" ");
            }
        }
        return stringBuilder;
    }


    @Override
    public Page<Message> queryAllPage(int pageNum, int pageSize, String search) {
        LambdaQueryWrapper<Message> wrapper = Wrappers.lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(Message::getPid, search);
        }
        return tcpMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
    }

    @Override
    public Page<Message> queryMessagePageByPid(String pid, int pageNum, int pageSize, long beginTm, long endTm) {
        QueryWrapper<Message> wrapper = new QueryWrapper<>();
        if (beginTm == -1 && endTm == -1) {
            return tcpMapper.selectPage(new Page<>(pageNum, pageSize), null);
// TODO: 2022/10/19 to fix
        } else if (endTm == -1) { // 有开始时间
            wrapper.ge("insert_time", beginTm)
                    .le("insert_time", System.currentTimeMillis()); //
        } else if (beginTm == -1) { // 有结束时间
            wrapper.le("insert_time", endTm);
        }
        Page<Message> messagePage = tcpMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
//        long newTm = System.currentTimeMillis() / 1000 / 60 + 1;
        return messagePage;
    }

    @Override
    public List<Message> querMessageByServiceLogId(Long serviceLogId, boolean b) {
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("service_log_id", serviceLogId);
        List<Message> list = tcpMapper.selectList(queryWrapper);
        return list;
    }

    public Message convertLine(String line, Long serviceLogId) {

        String[] split = line.split(" ");
        if (Objects.equals(split[0], Message.ProtoType.tcp.toString()) || Objects.equals(split[0], Message.ProtoType.tcp6.toString())) {
            // 构造对象
            return new Message.Builder()
                    .type(Message.ProtoType.valueOf(split[0]))
                    .localAddress(split[1])
                    .foreignAddress(split[2])
                    .state(split[3])
                    // 参数
                    .program("test_java")
                    // 名字
                    .name("test_/usr/sb")
                    .insertTime(System.currentTimeMillis())
                    .serviceLogId(serviceLogId)
                    .build();
        }
        return null;
    }


    //-----------------------db----------------------

}
