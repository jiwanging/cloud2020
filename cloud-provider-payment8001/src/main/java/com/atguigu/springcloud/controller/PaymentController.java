package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import com.sun.corba.se.impl.orbutil.ObjectUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;
import sun.rmi.runtime.Log;

import javax.annotation.Resource;

@Controller
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @PostMapping(value = "/payment/create")
    @ResponseBody
    public CommonResult<Payment> create(@RequestBody Payment payment){
        CommonResult commonResult = new CommonResult();
        try{
            log.info("订单模块：向数据库插入订单数据");
            log.info("插入的数据信息："+payment.toString());
            int affectNum  = paymentService.create(payment);
            log.info("影响行数："+affectNum);
            if(affectNum > 0){
                commonResult.setCode(200);
                commonResult.setMessage("创建成功："+payment.getId());
                commonResult.setData(payment);
            }else{
                commonResult.setCode(404);
                commonResult.setMessage("创建失败");
                commonResult.setData(null);
            }
        }catch (Exception e){
            log.error("异常：创建订单列表");
            e.printStackTrace();
        }
        log.info(commonResult.toString());
        return commonResult;
    }

    @GetMapping(value = "/payment/get/{id}")
    @ResponseBody
    public CommonResult<Payment> get(@PathVariable("id") Long id){
        CommonResult commonResult = new CommonResult();
        try{
            log.info("根据id向数据库查询数据"+id);
            Payment payment = paymentService.getPaymentById(id);
            log.info(payment.toString());
            if(payment != null){
                commonResult.setCode(200);
                commonResult.setMessage("查询成功："+id);
                commonResult.setData(payment);
            }else{
                commonResult.setCode(404);
                commonResult.setMessage("未查询到数据："+id);
                commonResult.setData(null);
            }
        }catch (Exception e){
            log.error("异常：根据id查询订单信息");
            e.printStackTrace();
        }
        log.info(commonResult.toString());
        return commonResult;
    }
}
