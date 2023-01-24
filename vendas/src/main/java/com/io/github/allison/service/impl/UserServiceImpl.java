package com.io.github.allison.service.impl;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.io.github.allison.domain.entity.Usuario;
import com.io.github.allison.domain.repository.UsuarioRepository;
import com.io.github.allison.exception.PasswordValidException;


@Service
public class UserServiceImpl implements  UserDetailsService{

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UsuarioRepository repository;

    @Transactional
    public Usuario salvar(Usuario usuario){
        return repository.save(usuario);
    }


    public UserDetails authentication(Usuario usuario){
      UserDetails user =  loadUserByUsername(usuario.getLogin());
     boolean valuePassword =  encoder.matches(usuario.getSenha(), user.getPassword());
        if(valuePassword){
            return user;
        }

        throw new PasswordValidException();

        

    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario =  repository.findByLogin(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario nao encontrado na base de dados"));
      
        String[] roles = usuario.isAdmin() ? new String[] {"ADMIN","USER"} : new String[]{"USER"};
         
            
        return User    
                .builder()
                .username(usuario.getLogin())
                .password(usuario.getSenha())
                .roles(roles)
                .build();
    }
    
}
