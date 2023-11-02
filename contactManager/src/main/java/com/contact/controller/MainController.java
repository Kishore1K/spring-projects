package com.contact.controller;

import com.contact.entities.Contact;
import com.contact.entities.User;
import com.contact.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private  UserRepository userRepository;
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
        m.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/register")
    public  String registerHandler(@ModelAttribute User user, @RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model m){


        user.setRole("ROLE_USER");
        user.setEnabled(true);
        user.setImgUrl("default.png");
        System.out.println("user = " + user);
        System.out.println("Agreement"+agreement);

        if(!agreement){
            System.out.println("Check the Terms and Conditions");
            m.addAttribute("user", user);
            return  "signup";
        }else{
            this.userRepository.save(user);
        }
        return "signup";

    }





}
