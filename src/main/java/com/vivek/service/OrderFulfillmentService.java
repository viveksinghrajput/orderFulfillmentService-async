package com.vivek.service;

import com.vivek.dto.OrderDto;
import com.vivek.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class OrderFulfillmentService {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private PaymentService paymentService;

    /* All Required process */
    /*
      1. Inventory service check order availability
      2. Process payment for order
      3. Notify to the user
      3. Assign to vendor
      4. packaging
      5. assign delivery partner
      6. assign trailer
      7. dispatch product
      **/

    public Order processOrder(OrderDto orderDto) throws InterruptedException {
       orderDto.setTrackingId(UUID.randomUUID().toString());
       Order order = new Order();
        if (inventoryService.checkProductAvailability(orderDto.getProductId())) {
            //handle exception here
            OrderDto paymentProcessOrderDto = paymentService.processPayment(orderDto);
            order.setProductId(paymentProcessOrderDto.getProductId());
            order.setName(paymentProcessOrderDto.getName());
            order.setProductType(paymentProcessOrderDto.getProductType());
            order.setQty(paymentProcessOrderDto.getQty());
            order.setPrice(paymentProcessOrderDto.getPrice());
            order.setTrackingId(paymentProcessOrderDto.getTrackingId());
        } else {
            throw new RuntimeException("Technical issue please retry");
        }
        return order;
    }


    @Async("asyncTaskExecutor")
    public void notifyUser(OrderDto order) throws InterruptedException {
        Thread.sleep(4000L);
        log.info("Notified to the user " + Thread.currentThread().getName());
    }


    @Async("asyncTaskExecutor")
    public void assignVendor(OrderDto order) throws InterruptedException {
        Thread.sleep(5000L);
        log.info("Assign order to vendor " + Thread.currentThread().getName());
    }


    @Async("asyncTaskExecutor")
    public void packaging(OrderDto order) throws InterruptedException {
        Thread.sleep(2000L);
        log.info("Order packaging completed " + Thread.currentThread().getName());
    }


    @Async("asyncTaskExecutor")
    public void assignDeliveryPartner(OrderDto order) throws InterruptedException {
        Thread.sleep(10000L);
        log.info("Delivery partner assigned " + Thread.currentThread().getName());
    }


    @Async("asyncTaskExecutor")
    public void assignTrailerAndDispatch(OrderDto order) throws InterruptedException {
        Thread.sleep(3000L);
        log.info("Trailer assigned and Order dispatched " + Thread.currentThread().getName());
    }
}
