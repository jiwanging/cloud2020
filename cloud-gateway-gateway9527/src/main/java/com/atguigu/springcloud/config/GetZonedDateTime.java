package com.atguigu.springcloud.config;

import java.time.ZonedDateTime;

public class GetZonedDateTime {
    public static void main(String[] args) {

        ZonedDateTime now = ZonedDateTime.now();
        System.out.println(now);
    }
}
