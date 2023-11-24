package com.exam.repository;

import com.exam.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepos extends JpaRepository<Users, Long> {
    Users findByUid(String user);
}
