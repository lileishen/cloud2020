package com.yntovi.springcloud.controller;

import com.yntovi.springcloud.entities.CommonResult;
import com.yntovi.springcloud.service.PaymentFeginService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderFeginController {
    @Resource
    private PaymentFeginService paymentFeginService;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        return paymentFeginService.getPaymentById(id);
    }


    @GetMapping("/consumer/payment/fegin/timeout")
    public String paymentFeginTimeout(){
        //openfegin -ribbon 客户端一般默认等待1s
        return  paymentFeginService.paymentFeginTimeout();
    }
}
