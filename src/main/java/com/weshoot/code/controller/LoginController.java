package com.weshoot.code.controller;

import com.weshoot.code.model.LoginRequest;
import com.weshoot.code.model.LoginResponse;
import com.weshoot.code.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/we-shoot/auth")
public class LoginController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        boolean authenticated = authService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        System.out.println(loginRequest);
        if (authenticated) {
            return new LoginResponse(true, "Login successful","hwhnjsou");
        } else {
            return new LoginResponse(false, "Invalid username or password",null);
        }
    }

    @PostMapping("/logout")
    public LoginResponse logout() {
        // Invalidate the session or perform other logout actions here
        return new LoginResponse(true, "Logout successful","");
    }
}
