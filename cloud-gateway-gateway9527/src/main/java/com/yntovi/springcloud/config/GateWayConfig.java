package com.yntovi.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GateWayConfig {
    @Bean
    public RouteLocator  customRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
//        https://news.baidu.com/guonei
        routes.route("path_route_yntovi",
                r->r.path("/guonei")
                        .uri("https://news.baidu.com/guonei")).build();

        return routes.build();
    }

    @Bean
    public RouteLocator  customRouteLocator1(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
//        https://news.baidu.com/guonei
        routes.route("path_route_yntovi",
                r->r.path("/guoji")
                        .uri("https://news.baidu.com/guoji")).build();

        return routes.build();
    }
}
