package project.api.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import project.api.domain.entity.Cliente;

@Repository
public interface ClientesRepository extends JpaRepository<Cliente, Integer> {

    @Modifying
    @Query("INSERT INTO Cliente (nome) VALUES (?1)")
    void salvar(String nome);

    @Modifying
    @Query("UPDATE Cliente c SET c.nome = ?2 WHERE c.id = ?1")
    void atualizarNome(Integer id, String nome);

    @Modifying
    @Query("DELETE FROM Cliente c WHERE c.id = ?1")
    void deletarPorId(Integer id);

    @Query("SELECT c FROM Cliente c")
    List<Cliente> selecionarTodos();

}
