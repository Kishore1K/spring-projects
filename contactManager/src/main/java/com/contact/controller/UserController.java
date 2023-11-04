package com.contact.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@Controller
public class UserController {

    @GetMapping("/index")
    public  String dashboardHandler(){
        System.out.println("UserController.dashboard");
        return  "normal/user_dashboard";
    }


}
