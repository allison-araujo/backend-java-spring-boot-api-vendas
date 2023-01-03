package com.io.github.allison.service;

import com.io.github.allison.domain.entity.Pedido;
import com.io.github.allison.rest.dto.PedidoDTO;

public interface PedidoService {

    Pedido salvar(PedidoDTO dto );
    
}
