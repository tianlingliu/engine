package com.thinkbit.engine.controller;

import com.thinkbit.engine.redis.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
public class RedisController {

    @Resource
    private RedisUtils redisService;


    @RequestMapping(value = "/redis", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String redis() {
        redisService.set("str", "value22222");
        log.debug("--------{}",redisService.get("str"));
        return "thinkbit hello!";
    }

}
