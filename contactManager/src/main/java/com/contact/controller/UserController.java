package com.contact.controller;

import com.contact.entities.Contact;
import com.contact.entities.User;
import com.contact.helper.Message;
import com.contact.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
import java.util.Objects;


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

    @PostMapping("/process_addContact")
    public String addContactHandler(@ModelAttribute Contact contact, @RequestParam("imageProcess") MultipartFile file, Principal principal, HttpSession session){
        String image=null;
        try{
//            if(file.isEmpty()){
//                System.out.println("File is empty");
//                image = "contact.png";
//            }else {
            image = userService.ProcessImage(file);
            System.out.println(image+"name");

            if(!Objects.equals(image, "")){
                userService.saveContact(contact, principal, image);
                session.setAttribute("message", new Message("contact Saved Successfully", "success"));
            }else{
                session.setAttribute("message", new Message("contact not saved", "danger"));
            }
        }catch (Exception e){
            throw  new UsernameNotFoundException(e.getMessage());
        }

        return  "normal/add_contact";
    }


    @GetMapping("/contacts/{page}")
    public  String showContact(@PathVariable("page") Integer page, Model m, Principal principal){
        m.addAttribute("title", "Contacts - Smart Contact ");
        Pageable pageable=PageRequest.of(page, 5);

        Page<Contact> contactList = userService.getContacts(principal.getName(), pageable);
        m.addAttribute("contact", contactList);
        m.addAttribute("currentPage",page);
        m.addAttribute("totalPages", contactList.getTotalPages());
        return "normal/show_contacts";
    }
    @GetMapping("/contact/{id}")
    public  String deleteContact(@PathVariable("id") Long id, Principal principal, HttpSession session) throws IOException {
        if(userService.deleteContact(id, principal.getName()))
            session.setAttribute("message", new Message("Contact Deleted Successfully...", "success"));
        return "redirect:/user/contacts/0";
    }

    @GetMapping("/{id}/contact")
    public  String viewContact(@PathVariable("id") Long id, Model m, Principal principal){
        Contact contact = userService.getDetails(id, principal.getName());
        if(contact != null)
            m.addAttribute("title", contact.getName());
        m.addAttribute("contact",contact );
        return  "normal/details";
    }

    @GetMapping("/contact/{id}/update")
    public String updateContact(@PathVariable("id") Long id, Model m, Principal principal){
        Contact contact = userService.getDetails(id, principal.getName());
        m.addAttribute("title", "Update - Contact Manager");
        m.addAttribute("contact", contact);
        return "normal/update_contact";
    }

    @PostMapping("/updateContact")
    public  String processUpdate(@ModelAttribute Contact contact, @RequestParam("imageProcess") MultipartFile file, Principal principal, HttpSession session){
        System.out.println(file.getOriginalFilename());
        try{
            String image=null;
            if(file.isEmpty()){
                image = userService.getPrevDetails(contact.getcId());
            }else{
                if(userService.deleteImage(contact.getImage())){
                    image=userService.ProcessImage(file);
                }else{
                    System.out.println("Image Not deleted");
                }
            }
            System.out.println(image);
            if(!Objects.equals(image, "")){
                userService.updateContact(contact, principal, image);
                session.setAttribute("message", new Message("contact Updated Successfully", "success"));
            }else{
                session.setAttribute("message", new Message("contact not Updated", "danger"));
            }
        }catch (Exception e){
            throw  new UsernameNotFoundException(e.getMessage());
        }
        return "redirect:/user/"+contact.getcId()+"/contact";
    }


    @GetMapping("/profile")
    public String profile(Model m){
        m.addAttribute("title", "Profile - Smart Contact ");
        return "normal/profile";
    }
    @GetMapping("/profile/update")
    public  String updateProfile(Model m, Principal principal){
        m.addAttribute("title", principal.getName());
        return  "normal/update_profile";

    }
    @PostMapping("/profile/update")
    public String updateProfileProcess(@ModelAttribute User user, Model m, @RequestParam("imageProcess") MultipartFile file,HttpSession session){
        try{
            String image=null;
            if(file.isEmpty()){
                image = userService.getPrevDetails1(user.getId());
            }else{
                if(userService.deleteImage(user.getImgUrl())){
                    image=userService.ProcessImage(file);
                }else{
                    System.out.println("Image Not deleted");
                }
            }
            System.out.println(image);
            if(!Objects.equals(image, "")){
                userService.updateProfile(user, image);
                session.setAttribute("message", new Message("User Details Updated Successfully", "success"));
            }else{
                session.setAttribute("message", new Message("User Details not Updated", "danger"));
            }
        }catch (Exception e){
            throw  new UsernameNotFoundException(e.getMessage());
        }
        return  "redirect:/user/profile";
    }


}
