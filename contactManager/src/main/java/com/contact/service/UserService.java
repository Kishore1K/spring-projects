package com.contact.service;

import com.contact.entities.Contact;
import com.contact.entities.User;
import com.contact.repository.ContactRepository;
import com.contact.repository.UserRepository;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;
import java.util.Objects;
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
        String originalFilename=null, filename=null,extension=null,fileNameWithExtension=null,fullPathWithFileName=null;
        File saveFile = new ClassPathResource("static/imgs").getFile();
        originalFilename = file.getOriginalFilename();
        filename = UUID.randomUUID().toString();
        extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        fileNameWithExtension = filename + extension;
        fullPathWithFileName = saveFile +File.separator+ fileNameWithExtension;
        System.out.println(originalFilename);
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



    public Page<Contact> getContacts(String name, Pageable pageable) {
        User user = getUserName(name);
        return contactRepository.getContactDetails(user.getId(), pageable);

    }

    public  boolean isVerfied(Long id, String name){
        Contact contact = contactRepository.getReferenceById(id);
        User user = userRepository.getUserByUserName(name);
        return Objects.equals(user.getId(), contact.getUser().getId());
    }
    public boolean deleteContact(Long id, String name) throws IOException {
       if(!isVerfied(id, name)){
           return false;
       }
       Contact contact = contactRepository.getContact(id);
       deleteImage(contact.getImage());
       contactRepository.deleteById(id);
       return true;

    }

    public Contact getDetails(Long id, String name) {
        User user = userRepository.getUserByUserName(name);
        Contact contact = contactRepository.getReferenceById(id);
        if(Objects.equals(user.getId(), contact.getUser().getId())){
            return contact;
        }
        return null;
    }

    public void updateContact(Contact contact, Principal principal, String image) {
        System.out.println(isVerfied(contact.getcId(),principal.getName()));
        if(!isVerfied(contact.getcId(),principal.getName())){
            System.out.println("Not Updated");
        }else {
            User user = getUserName(principal.getName());
            contact.setImage(image);
            contact.setUser(user);
            contactRepository.save(contact);
        }
    }

    public String getPrevDetails(Long id) {
        Contact contact = contactRepository.getContact(id);
        return contact.getImage();
    }

    public boolean deleteImage(String url) throws IOException {
        File saveFile = new ClassPathResource("static/imgs").getFile();
        Path fileLoc=null;
        if(url==null)
            return  true;
        else{
            if(url.equals("contact.png")){
                return true;
            }
            fileLoc = Path.of(saveFile + File.separator + url);
        }

        return  Files.deleteIfExists(fileLoc);
    }

    public void updateProfile(User user, String image) {
        user.setImgUrl(image);
        userRepository.save(user);

    }

    public String getPrevDetails1(Long id) {
        User user = userRepository.getReferenceById(id);
        return  user.getImgUrl();
    }

    public List<Contact> searchContact(String query, Principal principal) {
        User user = userRepository.getUserByUserName(principal.getName());

        return   contactRepository.findByNameContainingAndUser(query, user);
    }
}
