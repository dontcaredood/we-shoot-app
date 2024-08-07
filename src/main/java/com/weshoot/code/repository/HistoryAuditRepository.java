package com.weshoot.code.repository;

import com.weshoot.code.entity.OrderHistory;
import com.weshoot.code.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface HistoryAuditRepository extends JpaRepository<OrderHistory, Long> {
    Optional<List<OrderHistory>> findAllByOrderId(Long orderId);
}
