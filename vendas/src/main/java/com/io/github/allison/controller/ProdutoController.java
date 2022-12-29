package com.io.github.allison.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.io.github.allison.domain.entity.Produto;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private Produto produtos;

    public ProdutoController(Produto produto) {
        this.produtos = produto;
    }    
    

    @GetMapping("{id")
    public Produto getProdutoByid(@PathVariable Integer id){
        return produtos
                    .findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, reason: "Produto nao encontrado"))
    }        
    

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto save(@RequestBody Produto produto){
        return produtos.save(produto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        produtos.findById(id)
                        .map(produto -> {
                            produtos.delete(produto);
                            return produto;
                        })
    }
  
   
    
}
