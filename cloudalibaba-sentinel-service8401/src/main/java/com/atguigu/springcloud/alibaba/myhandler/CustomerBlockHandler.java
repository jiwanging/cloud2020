package com.atguigu.springcloud.alibaba.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;

public class CustomerBlockHandler {

    public static CommonResult handlerException(BlockException exception){
        return new CommonResult(4444,"客户自定义限流处理异常1",null);
    }

    public static CommonResult handlerException2(){
        return new CommonResult(4444,"客户自定义限流处理异常2",null);
    }

}
