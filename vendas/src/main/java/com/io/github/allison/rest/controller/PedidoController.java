package com.io.github.allison.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.io.github.allison.domain.entity.Pedido;
import com.io.github.allison.rest.dto.DetailsPedidoDTO;
import com.io.github.allison.rest.dto.PedidoDTO;
import com.io.github.allison.service.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    
    private PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody PedidoDTO dto){
        Pedido pedido = service.salvar(dto);
        return pedido.getId();
    }

    @GetMapping("{id}")
    public DetailsPedidoDTO getById(@PathVariable Integer id){
        return service
                .obterDetailsPedido(id)
                .map(p -> converter(p))
                .orElseThrow(() -> 
                        new ResponseStatusException(NOT_FOUND, "Pedido nao encontrado"));

    }

    private DetailsPedidoDTO converter(Pedido pedido){
        DetailsPedidoDTO
            .builder().codigo(pedido.getId())
            .dataPedido(pedido.getDataPedido());


    }
    
}
