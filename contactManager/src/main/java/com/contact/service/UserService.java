package com.contact.service;

import com.contact.entities.Contact;
import com.contact.entities.User;
import com.contact.repository.ContactRepository;
import com.contact.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactRepository contactRepository;

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

        File saveFile = new ClassPathResource("static/imgs").getFile();

        String originalFilename = file.getOriginalFilename();
        String filename = UUID.randomUUID().toString();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileNameWithExtension = filename + extension;
        String fullPathWithFileName = saveFile +File.separator+ fileNameWithExtension;


        if (extension.equalsIgnoreCase(".png") || extension.equalsIgnoreCase(".jpg") || extension.equalsIgnoreCase(".jpeg")) {
            File folder = new File(String.valueOf(saveFile));
            if (!folder.exists()) {
                folder.mkdirs();
            }

            // Use try-with-resources to ensure the input stream is closed after copying.
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, Paths.get(fullPathWithFileName));
            } catch (IOException e) {

                e.printStackTrace();
                throw e;
            }

            return fileNameWithExtension;
        } else {
            System.out.println("File with this " + extension + " is not allowed!!");
        }
        return "";
    }

    public List<Contact> getContacts(String name) {
        User user = getUserName(name);
        return contactRepository.getContactDetails(user.getId());

    }
}
