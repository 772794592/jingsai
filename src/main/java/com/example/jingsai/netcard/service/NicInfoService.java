package com.example.jingsai.netcard.service;

import com.example.jingsai.systemresource.utils.BaseResponse;

import java.util.List;

public interface NicInfoService {


    BaseResponse getNicInfoData(List<String> nicLists);
}
