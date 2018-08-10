package com.thinkbit.engine.service;

import com.thinkbit.common.usural.service.abs.AbsCrudService;
import com.thinkbit.engine.api.repository.bean.City;
import com.thinkbit.engine.api.repository.data.example.CityExample;
import com.thinkbit.engine.api.service.CityService;
import com.thinkbit.engine.api.vo.CityVO;
import com.thinkbit.engine.repository.CityRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@com.alibaba.dubbo.config.annotation.Service(version = "1.0.0")
public class CityServiceImpl extends AbsCrudService<CityRepository, City, CityExample, Long>
        implements CityService {


    @Override
    public String hello(String nickName) {
        log.info(nickName + " call me!");
        return String.format("hi , %s!", nickName);
    }



}
