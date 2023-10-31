package com.zcy.springcloud.myhandler;

import com.sun.deploy.security.BlockedException;
import com.zcy.springcloud.entities.CommonResult;

public class CustomerBlockHandler {
    public static CommonResult handlerException(BlockedException exception){
        return  new CommonResult(2020,"自定义处理限流信息。。。。CustomerBlockHandler");
    }
    public static CommonResult handlerException2(BlockedException exception){
        return  new CommonResult(2020,"自定义处理限流信息。。。。CustomerBlockHandler222");
    }
}
