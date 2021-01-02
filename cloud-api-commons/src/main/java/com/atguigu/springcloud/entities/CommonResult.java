package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {

    //响应码
    private Integer code;

    //响应消息
    private String message;

    //响应数据
    private T data;

    public CommonResult(int code, String message){
        this(code, message, null);
    }
}
