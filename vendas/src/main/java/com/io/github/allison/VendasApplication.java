package com.io.github.allison;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.io.github.allison.domain.entity.Cliente;
import com.io.github.allison.domain.repositorio.Clientes;


@SpringBootApplication


public class VendasApplication {

	@Bean
	public CommandLineRunner init(@Autowired Clientes clientes){
		return args -> {
			clientes.salvar(new Cliente("Alliosn"));
			clientes.salvar(new Cliente("Outro Araujo"));

			List<Cliente> todosCliente = clientes.obterAll();

			todosCliente.forEach(System.out::println);
			
			

		};
	}
	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
		
	}

}
