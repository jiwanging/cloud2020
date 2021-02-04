package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.alibaba.service.impl.MessageProviderImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SendMessageController {
    @Resource
    private MessageProviderImpl messageProvider;

    @GetMapping(value = "/sendMessage")
    public String sendMessage(){
        return "发送消息内容："+messageProvider.send()+"   发送成功！";
    }
}
