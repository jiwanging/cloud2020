package com.atguigu.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class FlowLimitController {

   @GetMapping(value = "/testA")
    public String testA(){
       log.info(Thread.currentThread().getName()+"开始时间：" + new Date().getTime());
       return "----testA";
    }

    @GetMapping(value = "/testB")
    public String testB(){
        return "----testB";
    }

    @GetMapping(value = "/testD")
    public String testD(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "----testD";
    }

    //测试异常比例
    @GetMapping(value = "/testm")
    public String testM(){
        int i = 10/0;
        return "----testM";
    }

    //测试异常数
    @GetMapping(value = "/tests")
    public String testS(){
        int i = 10/0;
        return "----tests";
    }

    //测试热点规则
    @GetMapping(value = "/testhotkey")
    public String testHotKey(@RequestParam(value = "p1",required = false)String p1,
                             @RequestParam(value = "p2",required = false)String p2){
       return "--------testHotKey!";
    }

    //兜底方法
    public String deal_testHotKey (String p1, String p2, BlockException exception){
        return "------deal_testHotKey,o(╥﹏╥)o";
    }
}
