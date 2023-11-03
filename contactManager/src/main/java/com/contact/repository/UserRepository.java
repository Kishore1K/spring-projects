package com.contact.repository;

import com.contact.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("from User where email=:email")
    public User getUserByUserName(@Param("email") String email);
}
