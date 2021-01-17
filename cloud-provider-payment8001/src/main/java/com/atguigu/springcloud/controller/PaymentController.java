package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

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
                commonResult.setMessage("创建成功："+payment.getId()+"端口号:"+serverPort);
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
                commonResult.setMessage("查询成功："+id+" 端口号："+serverPort);
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

    @GetMapping(value = "/payment/discovery")
    @ResponseBody
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            log.info("***** element:"+element);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        return this.discoveryClient;
    }

    /**
     * 为测试fegin超时写的超时程序
     */
    @GetMapping(value = "/payment/feign/timeout")
    @ResponseBody
    public String paymentFeignTimeout(){
        try {
            log.info("超时响应请求开始");
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "超时响应";
    }

    /**
     * 向外提供本例端口号
     */
    @GetMapping(value = "/payment/lb")
    @ResponseBody
    public String getPort(){
        return serverPort;
    }

    @GetMapping(value = "/payment/zipkin")
    @ResponseBody
    public String paymentZipkin()
    {
        return "hi ,i'am paymentzipkin server fall back，welcome to atguigu，O(∩_∩)O哈哈~";
    }
}
