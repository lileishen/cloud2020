package com.yntovi.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.imageio.stream.IIOByteBuffer;
import java.util.concurrent.TimeUnit;


@Service
public class PaymentHystrixService {
    /**
     * 正常访问 ，肯定ok
     * @param id
     * @return
     */
    public String paymentInfo_OK(Integer id){
        return "线程池： "+Thread.currentThread().getName() +" paymentInfo_ok,id: "+id+"\t"+"ok";
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler",commandProperties ={
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    } )
    public  String paymentInfo_timeout(Integer id){
        int timeNumber =3;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "线程池： "+Thread.currentThread().getName() +" paymentInfo_timeout,id: "+id+"\t"+"ok"+"耗时(s):"+timeNumber+"s";
    }

    public String paymentInfo_TimeoutHandler(Integer id){
        return "线程池： "+Thread.currentThread().getName() +" paymentInfo_TimeoutHandler,id: "+id+"\t";

    }

    //    服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreak_fallback",commandProperties = {
            @HystrixProperty(name="circuitBreaker.enabled",value="true"), //是否开启断路器
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value="10"), //请求次数
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value="10000"), //时间窗口期
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value="60"), //失败后达到多少号跳闸
    })
    public String  paymentCircuitBreaker(@PathVariable("id") Integer id){
        if (id<0){
            throw new RuntimeException("*********id 不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t" +"调用成功，流水号："+serialNumber;

    }

    public  String paymentCircuitBreak_fallback(@PathVariable("id") Integer id){
        return "id 不能为负数。请稍后再试。 id:"+id;
    }



}
