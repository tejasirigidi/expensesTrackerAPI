package com.example.expenseTrackerApi.web.controller;

import com.example.expenseTrackerApi.model.entity.Users;
import com.example.expenseTrackerApi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class userController {

    private final UserService userService;

    public userController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signUp")
    public ResponseEntity<Users> userSignUp(@RequestBody Users user) {
        Users user1 = userService.userSignUp(user);
        return new ResponseEntity<>(user1,  HttpStatus.OK);
    }

    @PostMapping("/Login")
    public ResponseEntity<String> userLogin(@RequestBody Users user) {
        String response = userService.findUser(user);
        return new ResponseEntity<>(response,  HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Users> userUpdate(@RequestBody Users user) {
        Users user1 = userService.userSignUp(user);
        return new ResponseEntity<>(user1,  HttpStatus.OK);
    }

    @GetMapping("/getUsers")
    public ResponseEntity<List<Users>> getUsers() {
        List<Users> userDetails = userService.getAllUsers();
        return new ResponseEntity<>(userDetails, HttpStatus.OK);
    }

}
