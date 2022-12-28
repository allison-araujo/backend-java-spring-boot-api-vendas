package com.io.github.allison.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.io.github.allison.domain.entity.Produto;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private Produto produtos;

    public ProdutoController(Produto produto) {
        this.produtos = produto;
    }    
    



   
    
}
