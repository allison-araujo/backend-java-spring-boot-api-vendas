package com.io.github.allison.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.io.github.allison.domain.entity.Cliente;
import com.io.github.allison.domain.repository.Clientes;

@Controller
public class ClienteController {
    
    private Clientes clientes;

    
    public ClienteController(Clientes clientes) {
        this.clientes = clientes;
    }


    @GetMapping("/api/clientes/{id}")
    @ResponseBody
    public ResponseEntity getClienteById(@PathVariable Integer id){
      Optional<Cliente> cliente = clientes.findById(id);
      if(cliente.isPresent()){
        return ResponseEntity.ok(cliente.get());
      }
      return  ResponseEntity.notFound().build();

    }


    @PostMapping("/api/clientes")
    @ResponseBody
    public ResponseEntity save(@RequestBody Cliente cliente){
     Cliente clienteSave =  clientes.save(cliente);

      return ResponseEntity.ok(clienteSave);

    }




}
