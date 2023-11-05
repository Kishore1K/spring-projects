package com.contact.service;

import com.contact.entities.Contact;
import com.contact.entities.User;
import com.contact.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserName(String name) {
        return  userRepository.getUserByUserName(name);

    }

    public void saveContact(Contact contact, Principal principal) {
        User user = getUserName(principal.getName());
        contact.setUser(user);
        user.getContacts().add(contact);
        userRepository.save(user);
        System.out.println("Added to DB");
    }
}
