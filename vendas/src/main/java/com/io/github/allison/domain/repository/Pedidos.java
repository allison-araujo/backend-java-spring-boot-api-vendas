package com.io.github.allison.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.io.github.allison.domain.entity.Cliente;
import com.io.github.allison.domain.entity.Pedido;


public interface Pedidos  extends JpaRepository<Pedido, Integer>{
    
    List<Pedido>  findByCliente(Cliente cliente);

    @Query("select p from Pedido p left join fetch p.itens where p.id = :id ")
    Optional<Pedido> findByIdFetchItens(@Param("id") Integer id);
}
