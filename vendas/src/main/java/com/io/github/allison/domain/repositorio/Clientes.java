package com.io.github.allison.domain.repositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.io.github.allison.domain.entity.Cliente;

@Repository
public class Clientes {

    private static String INSERT = "insert into cliente (nome) values (?) ";


    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Cliente salvar(Cliente cliente){

        jdbcTemplate.update(INSERT, new Object[]{cliente.getNome()});

        return cliente;

    }
    
}
