package com.atguigu.springcloud.alibaba.service;

import com.atguigu.springcloud.entities.Payment;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {

    public int create(Payment payment);

    public Payment getPaymentById(Long id);
}
