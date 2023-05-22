package project.api.domain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import project.api.domain.entity.Cliente;
import project.api.domain.repository.ClientesRepository;

@RestController
public class ClientesController {

    private final ClientesRepository clientesRepository;

    @Autowired
    public ClientesController(ClientesRepository clientesRepository) {
        this.clientesRepository = clientesRepository;
    }

    @PostMapping("/clientes")
    public ResponseEntity<Cliente> adicionarCliente(@RequestBody Cliente cliente) {
        Cliente novoCliente = clientesRepository.save(cliente);
        return ResponseEntity.ok(novoCliente);
    }

    @GetMapping("/clientes")
    public ResponseEntity<List<Cliente>> selecionarTodos() {
        List<Cliente> clientes = clientesRepository.selecionarTodos();
        return ResponseEntity.ok(clientes);
    }

}
