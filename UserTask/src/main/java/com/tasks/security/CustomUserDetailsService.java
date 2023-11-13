package com.tasks.security;

import com.tasks.entity.Users;
import com.tasks.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       Users user = userRepository.findByEmail(email).orElseThrow(
               ()-> new UsernameNotFoundException("User Doesnt exists")
       );
        Set<String> roles = new HashSet<String>();
        roles.add("ROLE_ADMIN");
        return new User(user.getEmail(), user.getPassword(), userAuthorities(roles));
    }

    private Collection<? extends GrantedAuthority > userAuthorities(Set<String> roles){
        return  roles.stream().map(
            role -> new SimpleGrantedAuthority(role)
        ).collect(Collectors.toList());


    }
}
