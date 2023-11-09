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
        try{
            if(file.isEmpty()){
                System.out.println("File is empty");
                contact.setImage("contact.png");

            }else{

                String image= userService.ProcessImage(file);
                if(!Objects.equals(image, "")){
                    userService.saveContact(contact, principal, image);
                    session.setAttribute("message", new Message("contact Saved Successfully", "success"));
                }else{
                    session.setAttribute("message", new Message("contact not saved", "danger"));
                }
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
    public  String deleteContact(@PathVariable("id") Long id, Model m, Principal principal){
        userService.deleteContact(id);
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
        m.addAttribute("contact", contact);
        return "normal/update_contact";
    }

}
