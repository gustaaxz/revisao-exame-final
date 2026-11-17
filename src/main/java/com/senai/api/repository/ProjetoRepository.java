package com.senai.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senai.api.model.Projeto;

@Repository 
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
    boolean existsByNome(String nome);
}
