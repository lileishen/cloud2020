package com.yntovi.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.yntovi.springcloud.service.PaymentHystrixService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {
    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id")Integer id){
       return paymentHystrixService.paymentInfo_OK(id);
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler",commandProperties ={
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
//    } )

    @HystrixCommand
    public String paymentInfo_timeout(@PathVariable("id") Integer id){
       return paymentHystrixService.paymentInfo_timeout(id);
    }

    /**
     * 下面是全局 fallback
     * @param id
     * @return
     */
    public String payment_Global_FallbackMethod(Integer id){
        return "Global 异常处理信息，请稍后再试……";

    }
//    public String paymentInfo_TimeoutHandler(Integer id){
//        return "线程池： "+Thread.currentThread().getName() +" paymentInfo_TimeoutHandler,id: "+id+"\t";
//
//    }
}
