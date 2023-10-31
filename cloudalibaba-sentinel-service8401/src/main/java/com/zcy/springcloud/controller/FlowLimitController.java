package com.zcy.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;


@RestController
@Slf4j
public class FlowLimitController {
    @GetMapping("/testA")
    public String testA() throws InterruptedException {
        Thread.sleep(1000);
        return "------testA";
    }

    @GetMapping("/testB")
    public String testB() {
        System.out.println(Thread.currentThread().getName()+"-------"+"TestB");
        return "------testB";
    }



    @GetMapping("/testD")
    public String testD()
    {
        int a=2/0;
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
        log.info("testD 测试RT");
        return "------testD";
    }

    @GetMapping("/testE")
    public String testE()
    {
        int a=2/0;
        log.info("testE");
        return "------testE";
    }
    @GetMapping("/testHostKey")
    @SentinelResource(value="testHostKey",blockHandler =  "deal_testHotKey")
    public  String testHostKey(@RequestParam(value="p1",required = false) String p1,
                               @RequestParam(value="p2",required = false) String p2
                               ){
//        int a=2/0;
//        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
        return  "----testHotKey";
    }
    public String deal_testHotKey(String p1, String p2, BlockException exception){
        return  "----testHotKey，111";
    }

}
