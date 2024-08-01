package com.weshoot.code.repository;

import com.weshoot.code.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByIsActive(Boolean aTrue);

    User findByUserName(String username);
}
