package com.io.github.allison;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.io.github.allison.domain.entity.Cliente;
import com.io.github.allison.domain.entity.Pedido;
import com.io.github.allison.domain.repository.Clientes;
import com.io.github.allison.domain.repository.Pedidos;
@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(
            @Autowired Clientes clientes,
            @Autowired Pedidos pedidos
    ) {
        return args -> {
            System.out.println("Salvando clientes");
            Cliente fulano = new Cliente("Fulano");
            clientes.save(fulano);

            Pedido p = new Pedido();
            p.setCliente(fulano);
            p.setDataPedido(LocalDate.now());
            p.setTotal(BigDecimal.valueOf(100));

            pedidos.save(p);

        //    Cliente cliente = clientes.findClienteFetchPedidos(fulano.getId());
        //     System.out.println(cliente);
        //     System.out.println(cliente.getPedidos());

             pedidos.findByCliente(fulano).forEach(System.out::println);



        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
