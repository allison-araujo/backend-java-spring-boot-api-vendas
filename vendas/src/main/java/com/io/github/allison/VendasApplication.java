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

			System.out.println("Salvando Clientes");
			clientes.salvar(new Cliente("Alliosn"));
			clientes.salvar(new Cliente("Outro Cliente Araujo"));

			List<Cliente> todosCliente = clientes.obterAll();
			todosCliente.forEach(System.out::println);
			System.out.println("Atualizando Clientes");


			todosCliente.forEach( c -> {
				c.setNome(c.getNome() + " atualizado");
				clientes.atualizar(c);
			});

			 todosCliente = clientes.obterAll();
			 todosCliente.forEach(System.out::println);			


			System.out.println("Buscando Clientes");
			clientes.buscarPorNome("Cli").forEach(System.out::println);


			// System.out.println("Deletando Clientes");

			// clientes.obterAll().forEach(c -> {
			// 	clientes.deletar(c);
			// });
			
			todosCliente = clientes.obterAll();
			if(todosCliente.isEmpty()){
				System.out.println("Nenhum cliente encontrado");

			}else{
				todosCliente.forEach(System.out::println);			
			}
			
		};
	}
	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
		
	}

}
