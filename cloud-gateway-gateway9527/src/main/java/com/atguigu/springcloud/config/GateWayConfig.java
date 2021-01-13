package com.atguigu.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GateWayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        //配置使得百度国内新闻的访问地址可以为：http://localhost:9527/guonei
        routes.route("path_rote_atguigu",r-> r.path("/guonei")
                .uri("http://news.baidu.com/guonei")).build();
        return routes.build();
    }

}
