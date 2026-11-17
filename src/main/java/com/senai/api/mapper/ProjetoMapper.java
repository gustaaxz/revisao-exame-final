package com.senai.api.mapper;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.senai.api.dto.projeto.ProjetoRequestDTO;
import com.senai.api.dto.projeto.ProjetoResponseDTO;
import com.senai.api.model.Projeto;

@Component 
public class ProjetoMapper {
    public Projeto toEntity(ProjetoRequestDTO requestDTO){
        return new Projeto(
            null, 
            requestDTO.nome(), 
            new ArrayList<>()
        );
    }

    public ProjetoResponseDTO toResponseDTO(Projeto projeto){
        return new ProjetoResponseDTO(
            projeto.getId(),
            projeto.getNome()
        );
    }
}
