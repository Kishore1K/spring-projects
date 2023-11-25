package com.exam.Controller;

import com.exam.entity.Exams;
import com.exam.entity.Users;
import com.exam.model.ExamRegister;
import com.exam.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;
    @GetMapping({"", "/"})
    public String welcome(){
        return "hello World";
    }


    @PostMapping("/save")
    public ResponseEntity<Users> saveUser(@RequestBody Users users){
        System.out.println(users);
        return  new ResponseEntity<>(userService.register(users), HttpStatus.CREATED);
    }

    @PostMapping("/exam")
    public ResponseEntity<Exams> saveExam(@RequestBody ExamRegister exams){
        Users users = userService.getUsers(exams.getUser());
        Exams exams1 = modelMapper.map(exams, Exams.class);
        exams1.setUser(users);
        return  new ResponseEntity<>(userService.register(users,exams1), HttpStatus.CREATED);
    }




}