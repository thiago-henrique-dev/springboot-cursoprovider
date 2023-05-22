package project.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import project.api.domain.entity.Cliente;
import project.api.domain.repository.Clientes;

import java.util.List;

@SpringBootApplication
public class ApiApplication {

    @Autowired
    private Clientes clientes;

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner init() {
        return args -> {
            System.out.println("Salvando clientes");
            Cliente cliente = new Cliente("Thiago");
            clientes.salvar(cliente);
            System.out.println("Cliente salvo: " + cliente);

            System.out.println("Atualizando cliente");
            cliente.setNome("Thiago Henrique");
            clientes.atualizar(cliente.getId(), cliente);
            System.out.println("Cliente atualizado: " + cliente);

            System.out.println("Deletando cliente");
            clientes.deletar(cliente.getId());
            System.out.println("Cliente deletado");

            // Outros comandos que você desejar executar no console
        };
    }
}
