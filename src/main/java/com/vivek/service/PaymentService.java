package com.vivek.service;

import com.vivek.dto.OrderDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentService {

    public OrderDto processPayment(OrderDto orderDto) throws InterruptedException {
        log.info("initiate payment for order " + orderDto.getProductId());
        //call actual payment gateway
        Thread.sleep(2000L);
        log.info("completed payment for order " + orderDto.getProductId());
        return orderDto;
    }


}
