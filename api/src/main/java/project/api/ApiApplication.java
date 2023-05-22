package project.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import project.api.domain.entity.Cliente;
import project.api.domain.service.ClientesService;

@SpringBootApplication
public class ApiApplication {

    private final ClientesService clientesService;

    @Autowired
    public ApiApplication(ClientesService clientesService) {
        this.clientesService = clientesService;
    }

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner init() {
        return args -> {
            System.out.println("Salvando clientes");
            clientesService.salvarCliente("Thiago");
            clientesService.salvarCliente("João");


            System.out.println("Atualizando cliente");
            clientesService.atualizarNomeCliente(1, "Thiago Henrique");

            System.out.println("Selecionando todos os clientes");
            List<Cliente> clientes = clientesService.selecionarTodos();
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }

            // System.out.println("Deletando cliente");
            // clientesService.deletarClientePorId(1);

            // Outros comandos que você desejar executar no console
        };
    }
}
