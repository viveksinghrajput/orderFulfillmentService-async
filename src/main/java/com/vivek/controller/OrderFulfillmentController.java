package com.vivek.controller;

import com.vivek.dto.OrderDto;
import com.vivek.entity.Order;
import com.vivek.service.OrderFulfillmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderFulfillmentController {


    @Autowired
    private OrderFulfillmentService service;

    @PostMapping
    public ResponseEntity<Order> processOrder(@RequestBody OrderDto orderDto) throws InterruptedException {
        Order order = service.processOrder(orderDto);// synchronous
        // asynchronous
        service.notifyUser(orderDto);
        service.assignVendor(orderDto);
        service.packaging(orderDto);
        service.assignDeliveryPartner(orderDto);
        service.assignTrailerAndDispatch(orderDto);
        return ResponseEntity.ok(order);
    }
}
