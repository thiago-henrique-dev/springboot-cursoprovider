package project.api.domain.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import project.api.domain.entity.Cliente;

import java.util.List;

@Repository
public class Clientes {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Cliente salvar(Cliente cliente) {
        entityManager.persist(cliente);
        return cliente;
    }

    @Transactional
    public Cliente atualizar(Integer id, Cliente cliente) {
        Cliente clienteExistente = entityManager.find(Cliente.class, id);
        if (clienteExistente != null) {
            clienteExistente.setNome(cliente.getNome());
            entityManager.merge(clienteExistente);
            return clienteExistente;
        } else {
            throw new IllegalArgumentException("Cliente não encontrado com o ID: " + id);
        }
    }

    @Transactional
    public void deletar(Cliente cliente) {
        if (!entityManager.contains(cliente)) {
            cliente = entityManager.merge(cliente);
        }
        entityManager.remove(cliente);
    }

    @Transactional()
    public void deletar(Integer id) {
        Cliente cliente = entityManager.find(Cliente.class, id);
        deletar(cliente);
    }

    @Transactional(readOnly = true)
    public List<Cliente> buscarPorNome(String nome) {
        String jqpl = " select c from Cliente c where c.nome like :nome ";
        TypedQuery<Cliente> query = entityManager.createQuery(jqpl, Cliente.class);
        query.setParameter("nome", "%" + nome + "%");
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public List<Cliente> obterTodos() {
        return entityManager
                .createQuery("from cliente", Cliente.class)
                .getResultList();
    }
}
