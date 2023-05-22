package project.api.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.api.domain.entity.Cliente;
import project.api.domain.repository.ClientesRepository;

@Service
public class ClientesService {

    private final ClientesRepository clientesRepository;

    @Autowired
    public ClientesService(ClientesRepository clientesRepository) {
        this.clientesRepository = clientesRepository;
    }

    @Transactional
    public void salvarCliente(String nome) {
        Cliente cliente = new Cliente(nome);
        clientesRepository.save(cliente);
        System.out.println("Cliente salvo: " + cliente);
    }

    @Transactional
    public void atualizarNomeCliente(Integer id, String nome) {
        Cliente cliente = clientesRepository.findById(id).orElse(null);
        if (cliente != null) {
            cliente.setNome(nome);
            clientesRepository.save(cliente);
            System.out.println("Cliente atualizado: " + cliente);
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    @Transactional
    public void deletarClientePorId(Integer id) {
        clientesRepository.deleteById(id);
        System.out.println("Cliente deletado.");
    }
}
