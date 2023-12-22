package com.bezkoder.spring.jpa.h2.repository;

import com.bezkoder.spring.jpa.h2.entity.Orders;
import com.bezkoder.spring.jpa.h2.model.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeShootRepository extends JpaRepository<Orders, Long> {
  List<Orders> findByPublished(boolean published);

  List<Orders> findByTitleContaining(String title);
}
