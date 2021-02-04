package com.atguigu.springcloud.alibaba.service.impl;

import com.atguigu.springcloud.alibaba.service.PaymentHystrixService;
import org.springframework.stereotype.Component;

@Component
public class PaymentHystrixFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "调用了PaymentHystrixFallbackService的paymentInfo_OK 呜呜呜！";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "调用了PaymentHystrixFallbackService的paymentInfo_TimeOut 呜呜呜！";
    }
}
