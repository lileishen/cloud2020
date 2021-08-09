package com.yntovi.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA(){
        return "-----testA";
    }
    @GetMapping("/testB")
    public String testB(){
        log.info(Thread.currentThread().getName()+"\t"+"**********************");
        return "-----testB";
    }

    @GetMapping("/testC")
    public String testC(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "-----testC";
    }

    @GetMapping("/testD")
    public String testD(){
        int age =10/0;
       log.info("testD 测试异常比例");
        return "-----testD";
    }
    @GetMapping("/testE")
    public String testE(){
        int age =10/0;
       log.info("testE测试异常");
        return "-----testE";
    }

}
