package com.io.github.allison.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.io.github.allison.domain.entity.Cliente;
import com.io.github.allison.domain.entity.ItemPedido;
import com.io.github.allison.domain.entity.Pedido;
import com.io.github.allison.domain.entity.Produto;
import com.io.github.allison.domain.enums.StatusPedido;
import com.io.github.allison.domain.repository.Clientes;
import com.io.github.allison.domain.repository.ItemsPedido;
import com.io.github.allison.domain.repository.Pedidos;
import com.io.github.allison.domain.repository.Produtos;
import com.io.github.allison.exception.PedidoNotSearchException;
import com.io.github.allison.exception.RuleBusinessException;
import com.io.github.allison.rest.dto.ItemPedidoDTO;
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
       pedido.setStatus(StatusPedido.REALIZADO);

       List<ItemPedido> itemsPedido = converterItems(pedido, dto.getItems());
       repository.save(pedido);
       itemsPedidoRepository.saveAll(itemsPedido);
       pedido.setItens(itemsPedido);

       return pedido;
   }
   
    

    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items){


      if(items.isEmpty()){
          throw new RuleBusinessException("Nao é possivel realizar um pedido sem itens");
          
      }
      return items
          .stream()
          .map(dto -> {
            Integer idProduto = dto.getProduto();
            Produto produto =   produtosRepository
                  .findById(idProduto)
                  .orElseThrow(
                      () -> new RuleBusinessException("Codigo de produto invalido: " + idProduto
                      ));

              ItemPedido itemPedido = new ItemPedido();
              itemPedido.setQuantidade(dto.getQuantidade());
              itemPedido.setPedido(pedido);
              itemPedido.setProduto(produto);

              return itemPedido;

          }).collect(Collectors.toList());
      
  }

  @Override
  public Optional <Pedido> obterDetailsPedido(Integer id) {      
      return repository.findByIdFetchItens(id);
  }



@Override
@Transactional
public void updateStatus(Integer id, StatusPedido statusPedido) {
    repository
        .findById(id)
        .map(pedido -> {
            pedido.setStatus(statusPedido);
            return repository.save(pedido);
        }).orElseThrow(() -> new PedidoNotSearchException() );
    
}


}
