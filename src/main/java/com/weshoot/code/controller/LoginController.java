package com.weshoot.code.controller;

import com.weshoot.code.entity.User;
import com.weshoot.code.model.LoginRequest;
import com.weshoot.code.model.LoginResponse;
import com.weshoot.code.model.UserRequest;
import com.weshoot.code.service.AuthService;
import com.weshoot.code.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.weshoot.code.util.GlobalConstants.LOCAL_URL;
import static com.weshoot.code.util.GlobalConstants.WEB_URL;

@CrossOrigin(origins = {LOCAL_URL, WEB_URL})
@RestController
@RequestMapping("/we-shoot/auth")
public class LoginController {
    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @CrossOrigin(origins = {LOCAL_URL, WEB_URL},methods = {RequestMethod.POST,RequestMethod.OPTIONS})
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        boolean authenticated = authService.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());
        System.out.println(loginRequest);
        if (authenticated) {
            return new LoginResponse(true, "Login successful", "hwhnjsou");
        } else {
            return new LoginResponse(false, "Invalid username or password", null);
        }
    }

    @CrossOrigin(origins = {LOCAL_URL, WEB_URL}, methods = {RequestMethod.POST,RequestMethod.OPTIONS})
    @PostMapping("/logout")
    public LoginResponse logout() {
        // Invalidate the session or perform other logout actions here
        return new LoginResponse(true, "Logout successful", "");
    }


}
