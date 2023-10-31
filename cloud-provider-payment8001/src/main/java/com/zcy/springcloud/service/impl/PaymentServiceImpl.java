package com.zcy.springcloud.service.impl;

import com.zcy.springcloud.dao.PaymentDao;
import com.zcy.springcloud.entities.Payment;
import com.zcy.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;
    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(long id) {
        System.out.println("aa222akkkkaf = " + id);
//        return  new Payment(1,"aaaaa");
        return paymentDao.getPaymentById(id);
    }
}
