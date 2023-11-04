package com.contact.controller;

import com.contact.entities.User;
import com.contact.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public  String dashboardHandler(Model m, Principal principal){
        System.out.println("UserController.dashboard");
        m.addAttribute("title", "DashBoard- Contact Manager");
        System.out.println(principal.getName());
        User user = userService.getUserName(principal.getName());
        System.out.println(user.getName());
        m.addAttribute("user", user);
        return  "normal/user_dashboard";
    }


}
