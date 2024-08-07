package com.weshoot.code.service;

import com.weshoot.code.entity.User;
import com.weshoot.code.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    UserRepository userRepository;

    public boolean authenticateUser(String username, String password){
        User user = null;
        if(!username.isEmpty()){
            user = userRepository.findByUserName(username);
        }
        if(!password.isEmpty()){
            assert user != null;
            return password.equals(user.getPassword());
        }

        return false;
    }


    // This is just a simple example. In a real application, you would use a database to verify the credentials.
    public boolean authenticate(String username, String password) {
        // Example: static username and password for demonstration purposes
        return "admin".equals(username) && "sandy".equals(password);
    }
}