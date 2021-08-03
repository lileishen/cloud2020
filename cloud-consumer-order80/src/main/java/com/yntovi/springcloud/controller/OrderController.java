package com.yntovi.springcloud.controller;

import com.yntovi.springcloud.entities.CommonResult;
import com.yntovi.springcloud.entities.Payment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class OrderController {
//    private static final String PAYMNET_URL="http://localhost:8001";
    private static final String PAYMNET_URL="http://cloud-payment-service";
    @Resource
    private RestTemplate restTemplate;//客户端通过RestTemplate调用服务端

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment) {
        return restTemplate.postForObject(PAYMNET_URL + "/payment/create", payment, CommonResult.class);
    }


    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> get(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMNET_URL + "/payment/get/"+id, CommonResult.class);
    }

    @GetMapping("/consumer/payment/create1")
    public CommonResult<Payment> create1(Payment payment) {
        ResponseEntity<CommonResult> entity = restTemplate.postForEntity(PAYMNET_URL + "/payment/create", payment, CommonResult.class);
        if(entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }else{
            return new CommonResult<>(444,"插入失败",null);
        }


    }


    @GetMapping("/consumer/payment/get1/{id}")
    public CommonResult<Payment> get1(@PathVariable("id") Long id) {
         ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMNET_URL + "/payment/get/" + id, CommonResult.class);
           if(entity.getStatusCode().is2xxSuccessful()){
               return entity.getBody();
           }else{
               return new CommonResult<>(444,"数据不存在333",null);
           }
    }




}
