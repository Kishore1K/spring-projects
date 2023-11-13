package com.tasks.repository;

import com.tasks.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    @Query("from Users as u where u.email=:email")
    Optional<Users> findByEmail(@PathVariable("email") String email);
}
