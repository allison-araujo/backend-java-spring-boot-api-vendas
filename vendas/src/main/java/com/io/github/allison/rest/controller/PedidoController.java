package com.io.github.allison.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.io.github.allison.service.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    
    private PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }


    

    
}
