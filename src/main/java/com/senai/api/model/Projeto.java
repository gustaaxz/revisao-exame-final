package com.senai.api.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;
import lombok.*;

@Entity 
@AllArgsConstructor 
@NoArgsConstructor 
@Table(name = "projetos")
@Getter 
@Setter 
public class Projeto {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = true)
    private String nome;

    @OneToMany(mappedBy = "projeto")
    private List<Tarefa> tarefas = new ArrayList<>();
}
