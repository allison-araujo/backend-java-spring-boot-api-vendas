package com.io.github.allison.service.impl;

import org.springframework.stereotype.Service;

import com.io.github.allison.domain.repository.Pedidos;
import com.io.github.allison.service.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService {
    private Pedidos repository;

    public PedidoServiceImpl(Pedidos repository) {
        this.repository = repository;
    }


    
}
