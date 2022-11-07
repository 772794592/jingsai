package com.example.jingsai.netcard.service.impl;

import com.example.jingsai.enums.CodeEnum;
import com.example.jingsai.netcard.pojo.NicInfo;
import com.example.jingsai.netcard.service.NicInfoService;
import com.example.jingsai.systemresource.utils.BaseResponse;
import com.example.jingsai.systemresource.utils.CommandUtil;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class NicServiceImpl implements NicInfoService {
private static final Logger logger = LoggerFactory.getLogger(NicServiceImpl.class);
    @Override
    public BaseResponse getNicInfoData(List<String> nics) {
        try{
            String[] split = nics.get(0).split(",");
            nics = Arrays.asList(split);
            //所有网卡状态
            String[] nicStatusCmd = new String[]{"/bin/sh","-c","ip addr|grep BROADCAST|awk '{print $2,$9}'"};
            //网卡流量
            String[] nicTrafficCmd = new String[]{"/bin/sh","-c","cat /proc/net/dev|grep -v face|grep -v Inter|tr : ' '|awk '{print $1,$2,$10}'"};
            CommandUtil.ExecReturn nicStatus = CommandUtil.exec(nicStatusCmd);
            CommandUtil.ExecReturn nicTraffic = CommandUtil.exec(nicTrafficCmd);
            Map<String, NicInfo> map1 = getNicState(nics, nicStatus.stdout, nicTraffic.stdout);
            TimeUnit.SECONDS.sleep(1);
            nicStatus = CommandUtil.exec(nicStatusCmd);
            nicTraffic = CommandUtil.exec(nicTrafficCmd);
            Map<String, NicInfo> map2 = getNicState(nics, nicStatus.stdout, nicTraffic.stdout);
            List<NicInfo> nicLists = map1.keySet().stream().map(nic -> {
                NicInfo nicState1 = map1.get(nic);
                NicInfo nicState2 = map2.get(nic);
                nicState2.setNicSpeed(nicState2.getNicTraffic() - nicState1.getNicTraffic());
                nicState2.setNicTraffic(nicState1.getNicTraffic());
                return nicState2;
            }).collect(Collectors.toList());
            return BaseResponse.createBySuccess(nicLists);
        }catch (InterruptedException | IOException e){
            logger.info("getNicInfoData --> {}", ExceptionUtils.getMessage(e));
            Thread.currentThread().interrupt();
        }
        return BaseResponse.createByError(CodeEnum.EC_GEN_INTERNAL);
    }



    private Map<String, NicInfo> getNicState(List<String> nicList, String nicStateResult, String trafficResult) {
        Map<String, NicInfo> nicStateMap = new HashMap<>();
        Stream.of(nicStateResult)
                .flatMap(out -> Stream.of(out.split("\n")))
                .forEach(line -> {
                    String[] data = line.split(":");
                    String nic = data[0];
                    if (!nicList.contains(nic)) {
                        return;
                    }
                    int state = "UP".equals(data[1].trim()) ? 0 : 1;
                    NicInfo nicState = new NicInfo();
                    nicState.setNicStatus(state);
                    nicStateMap.put(nic, nicState);

                });
        Stream.of(trafficResult)
                .flatMap(out -> Stream.of(out.split("\n")))
                .forEach(line -> {
                    String[] data = line.split(" ");
                    String nic = data[0];
                    if (!nicList.contains(nic)) {
                        return;
                    }
                    long rx = Long.parseLong(data[1]);
                    NicInfo nicState = nicStateMap.get(nic);
                    if (nicState != null) {
                        nicState.setNicName(nic);
                        nicState.setNicTraffic(rx);
                    }
                });

        return nicStateMap;
    }
}
