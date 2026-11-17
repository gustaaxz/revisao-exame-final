package com.senai.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.senai.api.dto.tarefa.TarefaRelatorioDTO;
import com.senai.api.model.Tarefa;

@Repository 
public interface TarefaRepository extends JpaRepository<Tarefa, Long>{
    @Query("SELECT t FROM Tarefa t JOIN t.projeto p WHERE p.id = :projetoId")
    List<Tarefa> findTarefasByProjetoId(@Param("projetoId") Long projetoId);

    @Query(value = "SELECT t.titulo AS tituloTarefa, t.custo AS custo, p.nome AS nomeProjeto" + " FROM tarefas t INNER JOIN projetos p ON t.projeto_id = p.id", nativeQuery = true)
    List<TarefaRelatorioDTO> relatorioTarefas();
}
