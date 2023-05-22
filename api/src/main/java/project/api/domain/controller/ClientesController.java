package project.api.domain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping("/clientes/{id}")
    public ResponseEntity<Cliente> selecionarPorId(@PathVariable Integer id) {
        Cliente cliente = clientesRepository.findById(id).orElse(null);
        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/clientes/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Integer id, @RequestBody Cliente clienteAtualizado) {
        Cliente clienteExistente = clientesRepository.findById(id).orElse(null);
        if (clienteExistente != null) {
            clienteExistente.setNome(clienteAtualizado.getNome());
            Cliente clienteAtualizadoNoRepositorio = clientesRepository.save(clienteExistente);
            return ResponseEntity.ok(clienteAtualizadoNoRepositorio);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
