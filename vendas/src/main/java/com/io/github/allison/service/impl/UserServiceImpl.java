package com.io.github.allison.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements  UserDetailsService{

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
       if(!username.equals("allison")) {
        throw new UsernameNotFoundException("Usuário nao encontrado na base");
       }

        return User
        .builder()
        .username("allison")
        .password(encoder.encode("123"))
        .roles("USER", "ADMIN")
        .build();
    }
    
}
