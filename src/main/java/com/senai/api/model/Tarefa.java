package com.senai.api.model;

import java.time.LocalDate;
import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.*;

@Entity 
@AllArgsConstructor 
@NoArgsConstructor 
@Table(name = "tarefas")
@Getter 
@Setter 
public class Tarefa {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", nullable = true)
    private String titulo;

    @Column(name = "codigo", nullable = true)
    private String codigo;

    @Column(name = "custo", nullable = true)
    private BigDecimal custo;

    @Column(name = "dataCriacao", nullable = true)
    private LocalDate dataCriacao;

    @ManyToOne 
    @JoinColumn(name = "projeto_id", nullable = false)
    private Projeto projeto;
}
