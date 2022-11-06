package com.io.github.allison.service;

import org.springframework.stereotype.Service;

import com.io.github.allison.model.Cliente;
import com.io.github.allison.repository.ClientesRepository;

@Service
public class ClientesService {

  private ClientesRepository repository;
 
  
    public ClientesService(ClientesRepository repository){
        this.repository = repository;

    }

    public void SalvarCliente(Cliente cliente){
        validarCliente(cliente);
        this.repository.persistir(cliente);
    }

    public void validarCliente(Cliente cliente){
        //aplica validacoes

    }
    
    
}
