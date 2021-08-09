package com.yntovi.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PaymentMian9001 {
     public static void main(String[] args){
               SpringApplication.run(PaymentMian9001.class,args);
             }

}
