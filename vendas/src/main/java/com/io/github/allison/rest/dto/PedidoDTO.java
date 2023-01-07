package com.io.github.allison.rest.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.io.github.allison.validation.NotEmptyList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {

    @NotNull(message = "Informe o codigo do cliente.")
    private Integer cliente;
    @NotNull(message = "campo total do pedido é obrigatório")
    private BigDecimal total;
    @NotEmptyList(message = "Pedido nao pode ser nulo.")    
    private List<ItemPedidoDTO> items;

}
