package com.senai.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.senai.api.dto.projeto.ProjetoRequestDTO;
import com.senai.api.dto.projeto.ProjetoResponseDTO;
import com.senai.api.dto.tarefa.TarefaResponseDTO;
import com.senai.api.service.ProjetoService;
import com.senai.api.service.TarefaService;

import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/api/projetos")
@RequiredArgsConstructor 
public class ProjetoController {
    private final ProjetoService service;
    private final TarefaService tarefaService;

    @PostMapping 
    @ResponseStatus(HttpStatus.CREATED)
    public ProjetoResponseDTO post(@RequestBody ProjetoRequestDTO dto){
        return service.post(dto);
    }

    @GetMapping 
    public List<ProjetoResponseDTO> listAll(){
        return service.listAll();
    }

    @GetMapping("/{id}")
    public ProjetoResponseDTO findById(@PathVariable Long id){
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public ProjetoResponseDTO update(@PathVariable Long id, @RequestBody ProjetoRequestDTO dto){
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

    @GetMapping("/{id}/tarefas")
    public List<TarefaResponseDTO> listTarefasByProject(@PathVariable Long id){
        return tarefaService.listByProject(id);
    }
}
