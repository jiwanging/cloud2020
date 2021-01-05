package com.atguigu.springcloud.config;

import feign.Logger;
import org.slf4j.event.EventRecodingLogger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public Logger.Level getLoggerLevel(){
        return Logger.Level.FULL;
    }
}
