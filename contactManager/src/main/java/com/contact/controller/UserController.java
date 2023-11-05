package com.contact.controller;

import com.contact.entities.Contact;
import com.contact.entities.User;
import com.contact.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @ModelAttribute
    public  void addCommon(Model m, Principal principal){
        User user = userService.getUserName(principal.getName());
        System.out.println(user.getName());
        m.addAttribute("user", user);
    }
    @GetMapping("/index")
    public  String dashboardHandler(Model m, Principal principal){
        System.out.println("UserController.dashboard");
        m.addAttribute("title", "DashBoard- Contact Manager");
        System.out.println(principal.getName());

        return  "normal/user_dashboard";
    }

    @GetMapping("/add_contact")
    public String openContactForm(Model m){
        m.addAttribute("title", "Add Contact - Contact Manager");
        m.addAttribute("contact", new Contact());
        return "normal/add_contact";

    }


}
