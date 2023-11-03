package com.contact.controller;

import com.contact.entities.Contact;
import com.contact.entities.User;
import com.contact.helper.Message;
import com.contact.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  BCryptPasswordEncoder passwordEncoder;
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
    public  String registerHandler(@Valid  @ModelAttribute User user, BindingResult result, @RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model m, HttpSession session){




        user.setRole("ROLE_USER");
        user.setEnabled(true);
        user.setImgUrl("default.png");
        System.out.println("user = " + user);
        try{
            if(!agreement){
                System.out.println("Agree For Terms and Conditions First Then You Can Submit.");
                throw  new Exception("Agree For Terms and Conditions");
            }

            if(result.hasErrors()){
                m.addAttribute("user", user);
                return "signup";
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User res = this.userRepository.save(user);
            m.addAttribute("user", new User());
            session.setAttribute("message", new Message("Successfully Registered", "alert-success"));

        }catch (Exception e){
            e.printStackTrace();
            m.addAttribute("user",user);
            session.setAttribute("message",new Message(e.getMessage(), "alert-danger"));
            return  "signup";
        }
        if(!agreement){
            System.out.println("Check the Terms and Conditions");
            m.addAttribute("user", user);
            return  "signup";
        }else{
        }
        return "signup";

    }





}
