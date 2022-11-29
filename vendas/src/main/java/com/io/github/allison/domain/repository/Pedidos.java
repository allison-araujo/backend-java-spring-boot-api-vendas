package com.io.github.allison.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.io.github.allison.domain.entity.Pedido;

public interface Pedidos  extends JpaRepository<Pedido, Integer>{
    
}
