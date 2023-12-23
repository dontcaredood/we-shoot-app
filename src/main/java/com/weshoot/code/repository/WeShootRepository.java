package com.weshoot.code.repository;

import com.weshoot.code.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeShootRepository extends JpaRepository<Orders, Long> {
}
