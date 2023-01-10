package com.io.github.allison.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.io.github.allison.domain.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer>{
    
    Optional<Usuario> findByLogin(String login);
    
}
