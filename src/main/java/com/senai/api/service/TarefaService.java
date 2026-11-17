package com.senai.api.service;

import org.springframework.stereotype.Service;

import com.senai.api.mapper.TarefaMapper;
import com.senai.api.repository.ProjetoRepository;
import com.senai.api.repository.TarefaRepository;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class TarefaService {
    private final TarefaRepository repository;
    private final TarefaMapper mapper;
    private final ProjetoRepository projetoRepository;

}
