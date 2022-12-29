package com.io.github.allison.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.io.github.allison.domain.entity.Cliente;
import com.io.github.allison.domain.entity.Produto;

public interface Produtos extends JpaRepository <Produto,Integer>{


    List<Produto> findByProduct (Cliente cliente);
    
}
