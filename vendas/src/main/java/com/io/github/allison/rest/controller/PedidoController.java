package com.io.github.allison.rest.controller;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.io.github.allison.domain.entity.ItemPedido;
import com.io.github.allison.domain.entity.Pedido;
import com.io.github.allison.domain.enums.StatusPedido;
import com.io.github.allison.rest.dto.DetailsItemPedidoDTO;
import com.io.github.allison.rest.dto.DetailsPedidoDTO;
import com.io.github.allison.rest.dto.PedidoDTO;
import com.io.github.allison.rest.dto.UpdateStatusPedidoDTO;
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
    public Integer save(@RequestBody @Valid PedidoDTO dto){
        Pedido pedido = service.salvar(dto);
        return pedido.getId();
    }

 

 
    @GetMapping("{id}")
    public DetailsPedidoDTO getById( @PathVariable Integer id ){
        return service
                .obterDetailsPedido(id)
                .map( p -> converter(p) )
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado."));
    }
    
    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@PathVariable Integer id, @RequestBody UpdateStatusPedidoDTO dto){
        String newStatus = dto.getNewStatus();
        service.updateStatus(id, StatusPedido.valueOf(newStatus));




}

    private DetailsPedidoDTO converter(Pedido pedido){
       return  DetailsPedidoDTO
            .builder().codigo(pedido.getId())
            .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
            .nomeCliente(pedido.getCliente().getNome())
            .cpf(pedido.getCliente().getCpf())
            .total(pedido.getTotal())
            .status(pedido.getStatus().name())
            .items(converter(pedido.getItens()))
            .build();



    }

    public List<DetailsItemPedidoDTO> converter(List<ItemPedido> itens){
        if(CollectionUtils.isEmpty(itens)){
            return Collections.emptyList();
        }

        return itens.stream().map(
            item -> DetailsItemPedidoDTO
                    .builder().descricaoProduto(item.getProduto().getDescricao())
                    .precoUnitario(item.getProduto().getPreco())
                    .quantidade(item.getQuantidade())
                    .build()
        ).collect(Collectors.toList());

    }
    
}
