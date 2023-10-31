package com.zcy.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.sun.deploy.security.BlockedException;
import com.zcy.springcloud.entities.CommonResult;
import com.zcy.springcloud.entities.Payment;
import com.zcy.springcloud.myhandler.CustomerBlockHandler;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RateLimitController {

//    @GetMapping("/rateLimit/customerBlockHandler")
//    @SentinelResource(value = "customerBlockHandler",
//            blockHandlerClass = CustomerBlockHandler.class,
//            blockHandler = "handlerException")
//    public CommonResult customerBlockHandler()
//    {
//        return new CommonResult(200,"按客戶自定义",new Payment(2020L,"serial003"));
//    }
    @GetMapping("/byResource")
    @SentinelResource(value = "byResource",blockHandler = "handleException")
    public CommonResult byResource(){
        return new CommonResult(200,"限流测试ok",new Payment(2020L,"serial001"));

    }

    public CommonResult handleException(BlockException exception)
    {
        return new CommonResult(444,exception.getClass().getCanonicalName()+"\t 服务不可用");
    }
    @GetMapping("/retaLimit/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult byUrl2(){
        log.info("445");
        return new CommonResult(222,"byUrl限流测试ok",new Payment(2020L,"serial001"));

    }
    @GetMapping("/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult byUrl(){
       log.info("4444");
        return new CommonResult(222,"byUrl限流测试ok",new Payment(2020L,"serial001"));

    }
    @GetMapping("/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",blockHandlerClass = CustomerBlockHandler.class,
    blockHandler = "handlerException2")
    public CommonResult customerBlockHandler(){
        return new CommonResult(200,"限流测试ok",new Payment(2020L,"serial003"));

    }
}
