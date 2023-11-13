package com.tasks.controller;

import com.tasks.model.UserDTO;
import com.tasks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Repository
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
       return new ResponseEntity<>(userService.createUser(userDTO), HttpStatus.CREATED);
    }
}
