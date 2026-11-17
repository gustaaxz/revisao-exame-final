package com.senai.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.senai.api.dto.projeto.ProjetoRequestDTO;
import com.senai.api.dto.projeto.ProjetoResponseDTO;
import com.senai.api.mapper.ProjetoMapper;
import com.senai.api.model.Projeto;
import com.senai.api.repository.ProjetoRepository;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class ProjetoService {
    private final ProjetoRepository repository;
    private final ProjetoMapper mapper;

    public ProjetoResponseDTO post(ProjetoRequestDTO dto){
        if(repository.existsByNome(dto.nome())){
            throw new RuntimeException("Já existe um projeto com esse nome!");
        }

        Projeto projeto = mapper.toEntity(dto);
        Projeto projetoSalvo = repository.save(projeto);
        return mapper.toResponseDTO(projetoSalvo);
    }

    public List<ProjetoResponseDTO> listAll(){
        return repository.findAll()
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    public ProjetoResponseDTO findById(Long id){
        Projeto projeto = repository.findById(id).orElseThrow(
            () -> new RuntimeException("Projeto não encontrado!"));
        return mapper.toResponseDTO(projeto);
    }

    public ProjetoResponseDTO update(Long id, ProjetoRequestDTO dto){
        Projeto projeto = repository.findById(id).orElseThrow(() -> new RuntimeException("projeto não encontrado!"));
        projeto.setNome(dto.nome());
        Projeto projetoAtualizado = repository.save(projeto);
        return mapper.toResponseDTO(projetoAtualizado);
    }

    public void delete(Long id){
        if (!repository.existsById(id)){
            throw new RuntimeException("Projeto não encontrado!");
        }
        repository.deleteById(id);
    }
}