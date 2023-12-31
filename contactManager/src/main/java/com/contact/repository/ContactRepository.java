package com.contact.repository;

import com.contact.entities.Contact;
import com.contact.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    @Query("from Contact as c where c.user.id =:id")
    public  Page<Contact> getContactDetails(@PathVariable("id") Long id, Pageable pageable);

    @Query("from Contact  as c where c.cId=:id")
    Contact getContact(@PathVariable("id") Long id);



    List<Contact> findByNameContainingAndUser(String name, User user);
}
