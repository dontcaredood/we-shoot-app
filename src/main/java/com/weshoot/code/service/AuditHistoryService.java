package com.weshoot.code.service;

import com.weshoot.code.entity.OrderHistory;
import com.weshoot.code.entity.Orders;
import com.weshoot.code.model.TrackOrderRequest;
import com.weshoot.code.repository.HistoryAuditRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class AuditHistoryService {
    @Autowired
    HistoryAuditRepository historyAuditRepository;

    private final String addOrderAction = "ORDER CREATED";
    private final String smsSent = "SMS SENT";
    private final String updateOrderAction = "ORDER UPDATED";
    private final String updateTrackOrderAction = "ORDER TRACKED";

    public Optional<List<OrderHistory>> getOrderHistoryById(Long orderId) {
        return historyAuditRepository.findAllByOrderId(orderId);
    }

    public void addOrderHistory(long orderId, String data) {
        OrderHistory orderHistory = mapOrderToOrderHistory(orderId, data, addOrderAction);
        historyAuditRepository.save(orderHistory);
    }

    public void updateOrderHistory(Orders save) {
        OrderHistory orderHistory = mapOrderToOrderHistory(save.getOrderId(), save.toString(), updateOrderAction);
        historyAuditRepository.save(orderHistory);
    }
    public void updateTrackOrderHistory(TrackOrderRequest save) {
        OrderHistory orderHistory = mapOrderToOrderHistory(save.getOrderId(), save.toString(), updateTrackOrderAction);
        historyAuditRepository.save(orderHistory);
    }
    public void addSMSHistory(long orderId) {
        OrderHistory orderHistory = mapOrderToSMSHistory(orderId, smsSent);
        historyAuditRepository.save(orderHistory);
    }


    private OrderHistory mapOrderToOrderHistory(long id, String data, String action) {
        OrderHistory orderHistory = OrderHistory.builder().orderId(id)
                .action(action)
                .data(data)
                .userName("Sandy").timeStamp(new Date()).build();
        log.info(orderHistory);
        return orderHistory;
    }

    private OrderHistory mapOrderToSMSHistory(long id, String action) {
        OrderHistory orderHistory = OrderHistory.builder().orderId(id)
                .action(action)
                .userName("Sandy").timeStamp(new Date()).build();
        log.info(orderHistory);
        return orderHistory;
    }


}
