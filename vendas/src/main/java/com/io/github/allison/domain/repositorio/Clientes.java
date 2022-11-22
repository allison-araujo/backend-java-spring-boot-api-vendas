package com.io.github.allison.domain.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.io.github.allison.domain.entity.Cliente;

public interface Clientes extends JpaRepository<Cliente, Integer> {

    List<Cliente> findByNomeLike(String nome);

    
}
