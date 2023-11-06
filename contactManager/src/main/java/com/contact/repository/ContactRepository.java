package com.contact.repository;

import com.contact.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    @Query("from Contact  where user.id=:id")
    List<Contact> getContactDetails(@PathVariable("id") Long id);
}
