package com.weshoot.code.service;

import com.weshoot.code.entity.OrderHistory;
import com.weshoot.code.model.Order;
import com.weshoot.code.repository.HistoryAuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuditHistoryService {
    @Autowired
    HistoryAuditRepository historyAuditRepository;
    public void addOrderHistory(long orderId) {
        OrderHistory orderHistory = mapOrderToOrderHistory(orderId);
        historyAuditRepository.save(orderHistory);
    }

    private OrderHistory mapOrderToOrderHistory(long id) {
        OrderHistory orderHistory = new OrderHistory();
        orderHistory.setUserName("SANDY");
        orderHistory.setOrderId(id);
        orderHistory.setTimeStamp(new Date());
        return orderHistory;
    }

}
