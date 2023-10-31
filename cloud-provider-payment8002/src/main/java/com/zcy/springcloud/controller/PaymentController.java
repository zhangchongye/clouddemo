package com.zcy.springcloud.controller;

import com.zcy.springcloud.entities.CommonResult;
import com.zcy.springcloud.entities.Payment;
import com.zcy.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;
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
    @GetMapping(value = "/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }
    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout(){
        try { TimeUnit.SECONDS.sleep(3); }catch (Exception e) {e.printStackTrace();}
        return serverPort;
    }


}
