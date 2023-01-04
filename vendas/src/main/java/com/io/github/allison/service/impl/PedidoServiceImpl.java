package com.io.github.allison.service.impl;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.io.github.allison.domain.entity.Cliente;
import com.io.github.allison.domain.entity.Pedido;
import com.io.github.allison.domain.repository.Clientes;
import com.io.github.allison.domain.repository.ItemsPedido;
import com.io.github.allison.domain.repository.Pedidos;
import com.io.github.allison.domain.repository.Produtos;
import com.io.github.allison.exception.RuleBusinessException;
import com.io.github.allison.rest.dto.PedidoDTO;
import com.io.github.allison.service.PedidoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final Pedidos repository;
    private final Clientes clientesRepository;
    private final Produtos produtosRepository;
    private final ItemsPedido itemsPedidoRepository;



    
     @Override 
     @Transactional
     public Pedido salvar(PedidoDTO dto){
        Integer idCliente = dto.getCliente(); 
        Cliente cliente = clientesRepository
            .findById(idCliente)
            .orElseThrow(() -> new RuleBusinessException("Codigo de Cliente invalido!!"));


        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);

      return null;
    }
    





}
