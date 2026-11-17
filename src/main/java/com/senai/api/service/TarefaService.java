package com.senai.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.senai.api.dto.tarefa.TarefaRelatorioDTO;
import com.senai.api.dto.tarefa.TarefaRequestDTO;
import com.senai.api.dto.tarefa.TarefaResponseDTO;
import com.senai.api.mapper.TarefaMapper;
import com.senai.api.model.Projeto;
import com.senai.api.model.Tarefa;
import com.senai.api.repository.ProjetoRepository;
import com.senai.api.repository.TarefaRepository;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class TarefaService {
    private final TarefaRepository repository;
    private final TarefaMapper mapper;
    private final ProjetoRepository projetoRepository;

    public TarefaResponseDTO post(TarefaRequestDTO dto){
        Projeto projeto = projetoRepository.findById(dto.projetoId())
            .orElseThrow(() -> new RuntimeException("Projeto vinculado não encontrado!"));

        Tarefa tarefa = mapper.toEntity(dto, projeto);
        Tarefa tarefaSalva = repository.save(tarefa);
        return mapper.toResponseDTO(tarefaSalva);
    }

    public List<TarefaResponseDTO> listAll(){
        return repository.findAll()
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    public TarefaResponseDTO findById(Long id){
        Tarefa tarefa = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Tarefa não encontrada!"));
        return mapper.toResponseDTO(tarefa);
    }

    public TarefaResponseDTO update(Long id, TarefaRequestDTO dto){
        Tarefa tarefa = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Tarefa não encontrada!"));
        
        Projeto projeto = projetoRepository.findById(dto.projetoId())
            .orElseThrow(() -> new RuntimeException("Projeto vinculado não encontrado!"));

        tarefa.setTitulo(dto.titulo());
        tarefa.setCodigo(dto.codigo());
        tarefa.setCusto(dto.custo());
        tarefa.setProjeto(projeto);

        Tarefa tarefaAtualizada = repository.save(tarefa);
        return mapper.toResponseDTO(tarefaAtualizada);
    }

    public void delete(Long id){
        if (!repository.existsById(id)){
            throw new RuntimeException("Tarefa não encontrada!");
        }
        repository.deleteById(id);
    }

    public List<TarefaResponseDTO> listByProject(Long projetoId){
        if(!projetoRepository.existsById(projetoId)){
            throw new RuntimeException("Projeto não encontrado!");
        }

        return repository.findTarefasByProjetoId(projetoId)
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    public List<TarefaRelatorioDTO> relatorioTarefas(){
        return repository.relatorioTarefas();
    }

}
