package com.senai.api.dto.tarefa;

import java.math.BigDecimal;

public record TarefaRequestDTO(
    String titulo,
    String codigo,
    BigDecimal custo,
    Long projetoId
) {

}
