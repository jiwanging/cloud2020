package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Controller
@Slf4j
public class OrderConsulController {

    private static  final  String  INVOME_URL = "http://consul-provider-payment";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/consumer/payment/consul")
    @ResponseBody
    public String payment(){
        log.info("消息：consul接收消息");
        String result = restTemplate.getForObject(INVOME_URL+"/payment/consul",String.class);
        return result;
    }

}
