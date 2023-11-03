package com.contact.service;

import com.contact.config.CustomUserDetail;
import com.contact.entities.User;
import com.contact.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailServiceImpl  implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUserName(username);
        if(user==null){
            throw  new UsernameNotFoundException("User Doesnt Exist");
        }

        return new CustomUserDetail(user);
    }
}
