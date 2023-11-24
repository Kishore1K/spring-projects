package com.exam.service;

import com.exam.entity.Users;
import com.exam.repository.UsersRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UsersRepos usersRepos;

    public String idGenerator(Users users){

        int id = new Random().nextInt(1000);
        String name = users.getName().toUpperCase().split(" ")[0];
        return  name+id;
    }


    @Override
    public Users register(Users users) {
        users.setUid(idGenerator(users));
        return usersRepos.save(users);
    }
}
