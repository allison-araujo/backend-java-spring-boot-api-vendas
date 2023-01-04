package com.io.github.allison.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.io.github.allison.domain.entity.Cliente;
import com.io.github.allison.domain.entity.Pedido;


public interface Pedidos  extends JpaRepository<Pedido, Integer>{
    
    List<Pedido>  findByCliente(Cliente cliente);

    Optional<Pedido> findByIdFetchItens(Integer id);
}
