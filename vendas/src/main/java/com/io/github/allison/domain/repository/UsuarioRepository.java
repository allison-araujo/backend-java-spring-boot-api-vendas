package com.io.github.allison.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.io.github.allison.domain.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer>{
    
}
