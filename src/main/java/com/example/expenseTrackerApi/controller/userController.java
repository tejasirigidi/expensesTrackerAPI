package com.example.expenseTrackerApi.controller;

import com.example.expenseTrackerApi.model.Users;
import com.example.expenseTrackerApi.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class userController {

    private final UserService userService;

    public userController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signUp")
    public Users userSignUp(@RequestBody Users user) {
        Users user1 = userService.userSignUp(user);
        return user1;
    }

    @PostMapping("/Login")
    public Users userLogin(@RequestBody Users user) {
        return userService.findUser(user);
    }

    @PutMapping("/update")
    public Users userUpdate(@RequestBody Users user) {
        Users user1 = userService.userSignUp(user);
        return user1;
    }
}
