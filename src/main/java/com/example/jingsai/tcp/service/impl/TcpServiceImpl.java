package com.example.jingsai.tcp.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.example.jingsai.tcp.common.BaseEnum;
import com.example.jingsai.tcp.dao.TcpMapper;
import com.example.jingsai.tcp.exception.CustomException;
import com.example.jingsai.tcp.pojo.Message;
import com.example.jingsai.tcp.pojo.ServiceInfo;
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
import java.util.Date;
import java.util.List;

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
    public List<Message> tcpList(String pid, boolean insertDb) throws IOException, InterruptedException {
        if (!StringUtils.hasLength(pid)) {
            throw new CustomException(BaseEnum.PID_ISNULL.getResultCode(), BaseEnum.PID_ISNULL.getResultMsg());
        }
        String[] cmd = {"sh", "-c", "netstat -anp|grep " + pid + "|awk '{print $1,$4,$5,$6,$7,$8}'"};
        /*String stdout = "tcp6 192.168.50.134:80 :::* LISTEN 1008/java \n" +
                "tcp6 244.255.255.244:5009 :::* LISTEN 1008/java \n" +
                "tcp6 244.255.255.244:6005 :::* LISTEN 1008/java \n" +
                "tcp6 192.168.50.134:443 :::* LISTEN 1008/java \n" +
                "tcp6 244.255.255.244:52840 244.255.255.246:3306 ESTABLISHED 1008/java \n" +
                "tcp6 244.255.255.244:52802 244.255.255.246:3306 ESTABLISHED 1008/java \n" +
                "tcp6 244.255.255.244:52798 244.255.255.246:3306 ESTABLISHED 1008/java \n" +
                "tcp6 244.255.255.244:6005 244.255.255.246:34492 ESTABLISHED 1008/java \n" +
                "tcp6 244.255.255.244:52800 244.255.255.246:3306 ESTABLISHED 1008/java \n" +
                "tcp6 244.255.255.244:52836 244.255.255.246:3306 ESTABLISHED 1008/java \n" +
                "unix ] STREAM CONNECTED 200786021 1008/java\n" +
                "unix ] STREAM CONNECTED 200794700 1008/java";*/
        ExecResult exec = ExecResult.exec(cmd);
        String stdout = exec.stdout;
//        logger.info("tcp的stdout {}", stdout);

        BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(stdout.getBytes()), StandardCharsets.UTF_8));
        String line;

//        Message messages = JSONObject.parseObject(jsonStr, Message.class);
//        System.out.println(messages);

        ArrayList<Message> messageList = new ArrayList<>();

        while ((line = br.readLine()) != null) {

            Message vo = this.convertLine(line);
            System.out.println("===================" + vo.getInsertTime());
            vo.setPid(pid);

            // 入库
            if (insertDb) {
                vo.setInsertTime(new Date());
                tcpMapper.insertMessage(vo);
            }
//            tcpMapper.insert(vo);

            System.out.println("--------------------" + vo.getId());


            messageList.add(vo);
            logger.info("构造的对象：{}", vo);
        }
        System.out.println("列表集合：" + messageList);

        // 数组转集合
//        List<String> strings = Arrays.asList(split);
//        System.out.println(strings);
//


//        StringBuilder stringBuilder = new StringBuilder();
//        while ((line = br.readLine()) != null) {
//            stringBuilder.append(line);
//
//            stringBuilder.append('\n');
//        }

//        Message message = new Message();
//        Message ms = Message.builder().pid("").build();
//        message.setType(Message.ProtoType.tcp);
//        message.setLocalAddress("0.0.0.0:22");
//        message.setForeignAddress("0.0.0.0:*");
//        message.setState("LISTEN");
//        message.setPid("0001");
//        message.setProgram("sshd");
//        message.setName("/usr/sb");
//        System.out.println("对象：" + message);


        // 对象转json
//        String s = JSONObject.toJSONString(message);
//        System.out.println("Json:" + s);
        return messageList;
    }

    public int insertMessage(Message message) {
        if (message == null) {
            throw new CustomException();
        }
        message.setInsertTime(new Date());
        return tcpMapper.insertMessage(message);
    }

    @Override
    public StringBuilder tcpPort(String pid) throws IOException, InterruptedException {
        ArrayList<String> ports = new ArrayList<>();

        List<Message> tcpList = this.tcpList(pid, false);
        StringBuilder stringBuilder = new StringBuilder();
        for (Message message : tcpList) {
            String localAddress = message.getLocalAddress();
            // 判断协议不是unix，截取端口号
            if (!Message.ProtoType.unix.equals(message.getType())) {

                String[] locals = localAddress.split(":");
                /*TcpLocalAddressPortVo addressPortVo = new TcpLocalAddressPortVo(pid, locals[0], locals[1]);*/
                stringBuilder.append(locals[1]);
                //ports.add(locals[1]);
            }
        }
        return stringBuilder;
    }


    @Override
    public List<Message> queryAll(String pid) {
        return tcpMapper.queryAll(pid);
    }

    public Message convertLine(String line) {
//        String jsonStr = JSONObject.toJSONString(line);
//        System.out.println("jsonStr:" + jsonStr);

        String[] split = line.split(" ");

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
                .insertTime(new Date())
                .build();
    }


    //-----------------------db----------------------

}
