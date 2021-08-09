package com.yntovi.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class OrderNacosController {
    @Resource
    private RestTemplate restTemplate;

    @Value("${server-url.nacos-user-service}")
    private String serverUrl;


    @GetMapping("/comsumer/payment/nacos/{id}")
    public String getPayment(@PathVariable("id") Integer id)
    {
        return  restTemplate.getForObject(serverUrl+"/payment/nacos/"+id,String.class);
    }

}
