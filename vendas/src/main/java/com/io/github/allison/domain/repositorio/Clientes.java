package com.io.github.allison.domain.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.io.github.allison.domain.entity.Cliente;

public interface Clientes extends JpaRepository<Cliente, Integer> {


    
    @Query(value ="select c from Cliente c where c.nome like :nome ")
    List<Cliente> findByNomeLike(@Param("nome") String nome);



    boolean existsByNome(String string);

   
}
