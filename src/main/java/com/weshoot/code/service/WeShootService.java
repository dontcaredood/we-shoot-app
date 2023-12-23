package com.weshoot.code.service;

import com.weshoot.code.entity.Orders;
import com.weshoot.code.model.Order;
import com.weshoot.code.repository.WeShootRepository;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Log4j2
public class WeShootService {
    @Autowired
    WeShootRepository weShootRepository;

    public Long addOrders(Order order) {
        return weShootRepository.save(mapOrderToEntity(order)).getOrderId();

    }

    private Orders mapOrderToEntity(Order order) {
        Double remainingAmount = order.getOrderAmount() - order.getAmountPaid();
        log.info("Order Amount : {}, Amount Paid in Advance: {}, Remaining Balance: {}", order.getOrderAmount(), order.getAmountPaid(), remainingAmount);
        Orders orders = Orders.builder()
                .orderDate(new Date())
                .amount_paid(order.getAmountPaid())
                .orderDescription(order.getOrderDescription())
                .orderAmount(order.getOrderAmount())
                .remainingBalance(remainingAmount)
                .orderType(order.getOrderType())
                .customerName(order.getCustomerName())
                .customerEmail(order.getCustomerEmail())
                .customerPhone(order.getCustomerPhone())
                .eventLocation(order.getEventLocation())
                .eventDate(order.getEventDate())
                .build();
        return orders;
    }

    public List<Orders> getAllOrders() {
        return weShootRepository.findAll();
    }
}
