package com.thinkbit.engine.controller;

import com.alibaba.fastjson.JSON;
import com.thinkbit.engine.api.repository.bean.City;
import com.thinkbit.engine.api.repository.data.example.CityExample;
import com.thinkbit.engine.api.service.CityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Controller
public class CityController {


    @Resource
    private CityService cityService;


    @RequestMapping(value = "/city")
    @ResponseBody
    public String index() {
        CityExample example = new CityExample();
        List<City> cities = cityService.selectByExample(example);
        log.debug(JSON.toJSONString(cities));
        for (City c : cities) {
            log.debug(JSON.toJSONString(c));

        }
        return cityService.hello("hello thinkbit!");
    }


    @RequestMapping(value = "/heart/beat")
    @ResponseBody
    public String heart_beat() {

        return "hello thinkbit start engine !";
    }


}
