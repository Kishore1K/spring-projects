package com.contact.controller;

import com.contact.entities.Contact;
import com.contact.entities.User;
import com.contact.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    @GetMapping("/")
    public  String home(Model m){
        m.addAttribute("title", "Home - Contact manager");
        System.out.println("MainController.home");
        return "home";
    }

    @GetMapping("/about")
    public  String about(Model m){
        m.addAttribute("title", "About - Contact manager ");
        return "about";
    }

    @GetMapping("/signup")
    public  String signUp(Model m){
        m.addAttribute("title", "Register - Contact manager ");
        return "signup";
    }





}
