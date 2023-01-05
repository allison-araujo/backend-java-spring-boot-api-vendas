package com.io.github.allison.service;

import java.util.Optional;

import com.io.github.allison.domain.entity.Pedido;
import com.io.github.allison.domain.enums.StatusPedido;
import com.io.github.allison.rest.dto.PedidoDTO;

public interface PedidoService {

    Pedido salvar(PedidoDTO dto );
    Optional <Pedido> obterDetailsPedido(Integer id);
    void updateStatus(Integer id, StatusPedido statusPedido);
    
}
