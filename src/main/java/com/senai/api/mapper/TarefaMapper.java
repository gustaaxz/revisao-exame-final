package com.senai.api.mapper;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.senai.api.dto.tarefa.TarefaRequestDTO;
import com.senai.api.dto.tarefa.TarefaResponseDTO;
import com.senai.api.model.Projeto;
import com.senai.api.model.Tarefa;

@Component 
public class TarefaMapper {
    public Tarefa toEntity(TarefaRequestDTO requestDTO, Projeto projeto){
        return new Tarefa(
            null, 
            requestDTO.titulo(), 
            requestDTO.codigo(), 
            requestDTO.custo(),
            LocalDate.now(),
            projeto
        );
    }

    public TarefaResponseDTO toResponseDTO(Tarefa tarefa){
        return new TarefaResponseDTO(
            tarefa.getId(),
            tarefa.getTitulo(),
            tarefa.getCodigo(),
            tarefa.getCusto(),
            tarefa.getDataCriacao(),
            tarefa.getProjeto().getId()
        );
    }
}
