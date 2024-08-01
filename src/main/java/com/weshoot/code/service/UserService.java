package com.weshoot.code.service;

import com.weshoot.code.entity.User;
import com.weshoot.code.model.UserRequest;
import com.weshoot.code.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public Long addUser(UserRequest userRequest) {
        Long userId = userRepository.save(mapRequestToEntity(userRequest)).getUserId();
        return userId;
    }

    private User mapRequestToEntity(UserRequest userRequest) {
        return User.builder().userName(userRequest.getName())
                .isAdmin(userRequest.isAdmin())
                .isActive(Boolean.TRUE)
                .lastLogin(new Date())
                .password(userRequest.getPassword()).build();
    }

    public List<User> getActiveUsers() {
        return userRepository.findAllByIsActive(Boolean.TRUE);
    }
}
