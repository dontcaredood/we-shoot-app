package com.weshoot.code.controller;

import com.weshoot.code.entity.User;
import com.weshoot.code.model.UserRequest;
import com.weshoot.code.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static com.weshoot.code.util.GlobalConstants.LOCAL_URL;
import static com.weshoot.code.util.GlobalConstants.WEB_URL;
@RestController
@RequestMapping("/we-shoot/user")
public class UserController {
    @Autowired
    UserService userService;

    @CrossOrigin(origins = {LOCAL_URL, WEB_URL}, methods = {RequestMethod.POST,RequestMethod.OPTIONS})
    @PostMapping("/addUser")
    public ResponseEntity<Long> addUser(@RequestBody UserRequest userRequest) {
        long id = userService.addUser(userRequest);
        // Invalidate the session or perform other logout actions here

        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = {LOCAL_URL, WEB_URL}, methods = {RequestMethod.POST,RequestMethod.OPTIONS})
    @GetMapping("/getUser")
    public ResponseEntity<List<User>> getUsers() {
        List<User> userList =  userService.getActiveUsers();
        return new ResponseEntity<>(userList, HttpStatus.CREATED);
    }
}
