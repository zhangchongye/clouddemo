package com.zcy.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(Sink.class)
public class ReciveMessageController {
    @Value("${server.port}")
    public String serverPort;

    @StreamListener(Sink.INPUT)
    public  void getMessage(Message<String> msgs){
        System.out.println("消费者2号，---收到消息"+msgs.getPayload()+"\t port:"+serverPort);
    }
}
