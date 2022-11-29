package com.io.github.allison.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.io.github.allison.domain.entity.ItemPedido;

public interface ItemsPedido  extends JpaRepository<ItemPedido, Integer>{
    
}
