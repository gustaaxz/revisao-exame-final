package com.senai.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.senai.api.dto.tarefa.TarefaRelatorioDTO;
import com.senai.api.dto.tarefa.TarefaRequestDTO;
import com.senai.api.dto.tarefa.TarefaResponseDTO;
import com.senai.api.service.TarefaService;

import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/api/tarefas")
@RequiredArgsConstructor 
public class TarefaController {

    private final TarefaService service;

    @PostMapping 
    @ResponseStatus(HttpStatus.CREATED)
    public TarefaResponseDTO post(@RequestBody TarefaRequestDTO dto){
        return service.post(dto);
    }

    @GetMapping
    public List<TarefaResponseDTO> listAll(){
        return service.listAll();
    }

    @GetMapping("/{id}")
    public TarefaResponseDTO findById(@PathVariable Long id){
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public TarefaResponseDTO update(@PathVariable Long id, @RequestBody TarefaRequestDTO dto){
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

    @GetMapping("/relatorio")
    public List<TarefaRelatorioDTO> relatorioTarefas(){
        return service.relatorioTarefas();
    }
}
