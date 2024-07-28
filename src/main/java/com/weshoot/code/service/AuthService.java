package com.weshoot.code.service;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

    // This is just a simple example. In a real application, you would use a database to verify the credentials.
    public boolean authenticate(String username, String password) {
        // Example: static username and password for demonstration purposes
        return "admin".equals(username) && "sandy".equals(password);
    }
}