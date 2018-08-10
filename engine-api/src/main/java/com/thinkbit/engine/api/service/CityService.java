package com.thinkbit.engine.api.service;

import com.thinkbit.common.usural.service.CrudService;
import com.thinkbit.engine.api.repository.bean.City;
import com.thinkbit.engine.api.repository.data.example.CityExample;
import com.thinkbit.engine.api.vo.CityVO;

import java.util.List;


public interface CityService extends CrudService<City, CityExample, Long> {

    String hello(String nickName);


}