package com.io.github.allison.rest.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.io.github.allison.domain.entity.Usuario;
import com.io.github.allison.exception.PasswordValidException;
import com.io.github.allison.rest.dto.CredencialsDTO;
import com.io.github.allison.rest.dto.TokenDTO;
import com.io.github.allison.security.jwt.JwtService;
import com.io.github.allison.service.impl.UserServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    
    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvar(@RequestBody @Valid Usuario usuario){
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        return userService.salvar(usuario);

    }

    @PostMapping("/auth")
    public TokenDTO authentication(@RequestBody CredencialsDTO credential){
        try{
            Usuario user = Usuario.builder()
                .login(credential.getLogin())
                .senha(credential.getSenha())
                .build();

            UserDetails userAuth = userService.authentication(user);
            String token = jwtService.generateToken(user);
            return new TokenDTO(user.getLogin(), token);
        }catch(UsernameNotFoundException | PasswordValidException e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());

      
        }
    }





}
