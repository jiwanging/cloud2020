package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Controller
@Slf4j
public class OrderController {

    @Resource
    private RestTemplate restTemplate;
    public static final String BASE_URL = "http://CLOUD-PAYMENT-SERVICE";

    @PostMapping(value = "consumer/payment/create")
    @ResponseBody
    public CommonResult<Payment> create(Payment payment){

        CommonResult<Payment> commonResult = new CommonResult<Payment>();
        try{
            log.info("消息：消费者调创建订单接口"+payment.toString());
            commonResult = restTemplate.postForObject(BASE_URL+"/payment/create",payment,CommonResult.class);
        }catch(Exception e){
            log.error("异常：消费者创建订单出现异常！");
            e.printStackTrace();
        }
        log.info(commonResult.toString());
        return commonResult;
    }

    @GetMapping(value = "/consumer/payment/get/{id}")
    @ResponseBody
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        CommonResult<Payment> commonResult = new CommonResult<Payment>();
        try {
            log.info("消息：消费者开始查询订单编号为："+id+"的信息");
            commonResult = restTemplate.getForObject(BASE_URL+"/payment/get/"+id,CommonResult.class);
        } catch (RestClientException e) {
            log.error("异常：消费者请求获取订单信息失败");
            e.printStackTrace();
        }
        log.info(commonResult.toString());
        return commonResult;
    }

    @GetMapping(value = "/consumer/payment/zipkin")
    @ResponseBody
    public String paymentZipkin()
    {
        String result = restTemplate.getForObject("http://CLOUD-PAYMENT-SERVICE"+"/payment/zipkin", String.class);
        return "哈哈啊哈哈哈：   "+result;
    }


}
