package com.zcy.springcloud.controller;

import com.zcy.springcloud.entities.CommonResult;
import com.zcy.springcloud.entities.Payment;
import com.zcy.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@EnableDiscoveryClient
public class PaymentController {
    @Resource
    PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;
    @Resource
    private DiscoveryClient discoveryClient;
    @PostMapping("/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("*****插入结果："+result);
        if(result>0){
            return new CommonResult(0,"插入数据库成功,serverPort=="+serverPort,result);
        }else{
            return  new CommonResult<>(444,"插入数据库失败");
        }
    }
    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") long id){
        Payment result = paymentService.getPaymentById(id);
        log.info("*****查询结果："+result);
        if(result!=null){
            return new CommonResult(200,"查询成功,serverPort=="+serverPort,result);
        }else{
            return new CommonResult<>(444,"查询失败,id==="+id);
        }
    }
    @GetMapping("/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();

        services.forEach(t->{
            System.out.println("service==="+t);
        });

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        instances.forEach(t->{
            String instanceId = t.getInstanceId();
            System.out.println("instanceId==="+instanceId);
            String host = t.getHost();
            System.out.println("host==="+host);
            int port = t.getPort();
            System.out.println("port==="+port);
        });
        return  this.discoveryClient;
    }
    @GetMapping(value = "/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }
    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout(){
        try { TimeUnit.SECONDS.sleep(3); }catch (Exception e) {e.printStackTrace();}
        return serverPort;
    }


    @GetMapping("/payment/zipkin")
    public String paymentZipkin()
    {
        return "hi ,i'am paymentzipkin server fall back，welcome to atguigu，O(∩_∩)O哈哈~";
    }

}
