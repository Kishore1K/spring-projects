package com.exam.service;

import com.exam.entity.Exams;
import com.exam.entity.Users;
import org.springframework.stereotype.Service;

public interface UserService {

    public Users register(Users users);

    public Exams register(Users users, Exams exams);

    Users getUsers(String user);
}
