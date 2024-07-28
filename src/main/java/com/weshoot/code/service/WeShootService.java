package com.weshoot.code.service;

import com.weshoot.code.entity.Orders;
import com.weshoot.code.model.Order;
import com.weshoot.code.repository.WeShootRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class WeShootService {
    @Autowired
    WeShootRepository weShootRepository;

    @Autowired
    AuditHistoryService auditHistoryService;

    public Long addOrders(Order order) {
        long orderId =  weShootRepository.save(mapOrderToEntity(order)).getOrderId();
        auditHistoryService.addOrderHistory(orderId);
        return orderId;

    }

    private Orders mapOrderToEntity(Order order) {
        //add validation

        Double remainingAmount = order.getOrderAmount() - order.getAmountPaid();
        if(remainingAmount<0){
            throw new IllegalArgumentException("Invalid Amount paid value");
        }
        log.info("Order Amount : {}, Amount Paid in Advance: {}, Remaining Balance: {}", order.getOrderAmount(), order.getAmountPaid(), remainingAmount);
        Orders orders = Orders.builder()
                .orderDate(new Date())
                .amountPaid(order.getAmountPaid())
                .orderDescription(order.getOrderDescription())
                .orderAmount(order.getOrderAmount())
                .remainingBalance(remainingAmount)
                .orderType(order.getOrderType())
                .customerName(order.getCustomerName())
                .customerEmail(order.getCustomerEmail())
                .customerPhone(order.getCustomerPhone())
                .eventLocation(order.getEventLocation())
                .eventDate(order.getEventDate())
                .isClosed(false)
                .isDelivered(false)
                .build();
        return orders;
    }

    public List<Orders> getAllOrders() {
        return weShootRepository.findAll();
    }

    public Optional<Orders> getOrderById(Long id) {
        return weShootRepository.findById(id);
    }

    public Optional<Orders> updateDeliveryStatus(Orders orders) {
        Orders updatedOrder = weShootRepository.save(orders);
        auditHistoryService.addOrderHistory(orders.getOrderId());
        return Optional.of(updatedOrder);
    }
}
