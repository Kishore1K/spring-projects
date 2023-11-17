package com.tasks.controller;

import com.tasks.model.JwtResponse;
import com.tasks.model.LoginDTO;
import com.tasks.model.UserDTO;
import com.tasks.security.JWTUtils;
import com.tasks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Repository
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtils jwtUtils;
    @PostMapping("/register")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
       return new ResponseEntity<>(userService.createUser(userDTO), HttpStatus.CREATED);
    }



    @PostMapping("/login")
    public ResponseEntity<JwtResponse> loginUser(@RequestBody LoginDTO loginDTO){
        System.out.println("login");
        Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
                );
        System.out.println(authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtils.generateToken( authentication);
        return new ResponseEntity<>(new JwtResponse(token), HttpStatus.OK);
    }
}
