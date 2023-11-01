package com.contact.controller;

import com.contact.entities.Contact;
import com.contact.entities.User;
import com.contact.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/test")
    public  void example(){
        User user = new User("Kishore", "reddyKishore@gmail1.com", "test", "admin", true, "/d/", "ABC");
        Contact contact = new Contact("K", "K", "AB", "jdf", "123456789", "J", "kbd", user);
        Contact contact1 = new Contact("K1", "K1", "AB1", "jdf1", "1231456789", "J1", "kbd1", user);
        user.setContacts(List.of(contact1, contact));
        userRepository.save(user);
//
//        System.out.println(user);
//        System.out.println(contact);

    }


}
