package com.producer.cn.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author : asus
 * @version : 1.0
 * @Description:
 * @Copyright : Copyright (c) 2018 All Rights Reserved
 * @date :2018/4/26 21:45
 */
@RestController
public class ProducerController {

    @Value("${server.port}")
    String port;

    @RequestMapping({"/hi"})
    public String home(@RequestParam String name) {
        return "Hi " + name + ",I am from port:" + this.port;
    }

    @RequestMapping({"/hello"})
    public String hello(@RequestParam String name) {
        return "Hello " + name + "，This is First Messge";
    }

    @RequestMapping({"/nowTime"})
    public String nowTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

}