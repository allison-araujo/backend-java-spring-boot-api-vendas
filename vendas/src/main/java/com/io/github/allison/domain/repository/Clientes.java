package com.io.github.allison.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.io.github.allison.domain.entity.Cliente;

public interface Clientes extends JpaRepository<Cliente, Integer> {


    
    @Query(value ="select * from cliente c where c.nome like '%:nome' ", nativeQuery = true)
    List<Cliente> findByNomeLike(@Param("nome") String nome);

    
    @Query(" delete from Cliente c where c.nome = :nome")
    @Modifying
    void deleteByNome(String nome);

    boolean existsByNome(String string);


    @Query(" select c from Cliente c left join fetch c.pedidos where c.id = :id  ")
    Cliente findClienteFetchPedidos( @Param("id") Integer id );
   
}