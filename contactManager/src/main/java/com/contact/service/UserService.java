package com.contact.service;

import com.contact.entities.Contact;
import com.contact.entities.User;
import com.contact.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserName(String name) {
        return  userRepository.getUserByUserName(name);

    }

    public void saveContact(Contact contact, Principal principal, String image) {
        User user = getUserName(principal.getName());
        contact.setImage(image);
        contact.setUser(user);
        user.getContacts().add(contact);
        userRepository.save(user);
        System.out.println("Added to DB");
    }


    public String ProcessImage(MultipartFile file) throws IOException {

        String name  = file.getOriginalFilename();
        String randomId = UUID.randomUUID().toString();
        String fileName= randomId.concat(name.substring(name.lastIndexOf(".")));
        File saveFile = new ClassPathResource("static/imgs").getFile();
        String filePath = saveFile+File.separator+fileName;
        Files.copy(file.getInputStream(), Paths.get(filePath));
        return  fileName;

    }
}
