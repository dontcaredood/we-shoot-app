package com.weshoot.code.repository;

import com.weshoot.code.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
}
