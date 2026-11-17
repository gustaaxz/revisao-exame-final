# revisao-exame-final

API REST desenvolvida em **Java** com **Spring Boot** para o gerenciamento de projetos e tarefas, baseada no desafio prático de avaliação da Unidade Curricular de Programação Back-End (SENAI/SC - Turma MI79).

---

## 🛠️ Tecnologias Utilizadas
- **Java 21**
- **Spring Boot 4.1.0**
- **Spring Data JPA**
- **Hibernate**
- **MySQL Driver**
- **Lombok**
- **Maven**

---

## 🏛️ Arquitetura do Projeto
A aplicação segue uma arquitetura em camadas com separação clara de responsabilidades:
- **`model`**: Entidades mapeadas para o banco de dados via JPA.
- **`repository`**: Interfaces de acesso a dados que estendem `JpaRepository`.
- **`dto`**: Records para transferência de dados de entrada (`RequestDTO`) e saída (`ResponseDTO`), evitando vazamento e dependência circular de entidades.
- **`mapper`**: Componentes Spring (`@Component`) responsáveis por converter DTOs em Entidades e vice-versa.
- **`service`**: Camada de lógica de negócio e validações.
- **`controller`**: Controladores REST para exposição dos endpoints HTTP.

---

## 📋 Status de Desenvolvimento

### ✅ O que já foi feito até o momento

1. **Entidades de Domínio (`model`):**
   - [x] `Projeto`: mapeado com `@Entity`, chave primária auto-incrementada e relacionamento `@OneToMany(mappedBy = "projeto")` com `List<Tarefa>`.
   - [x] `Tarefa`: mapeado com `@Entity`, atributos obrigatórios (`titulo`, `codigo`, `custo` como `BigDecimal`, `dataCriacao` como `LocalDate`) e relacionamento `@ManyToOne` com chave estrangeira `projeto_id` (`nullable = false`).

2. **Repositórios (`repository`):**
   - [x] `ProjetoRepository`: estendendo `JpaRepository<Projeto, Long>` com o método `existsByNome(String nome)` para validação de duplicidade.
   - [x] `TarefaRepository`: estendendo `JpaRepository<Tarefa, Long>` com consulta obrigatória via `@Query` em JPQL realizando `JOIN` performático entre Tarefa e Projeto (`findByProjetoId`).

3. **Data Transfer Objects (`dto`):**
   - [x] `ProjetoRequestDTO`: `nome`.
   - [x] `ProjetoResponseDTO`: `id`, `nome`.
   - [x] `TarefaRequestDTO`: `titulo`, `codigo`, `custo`, `projetoId`.
   - [x] `TarefaResponseDTO`: `id`, `titulo`, `codigo`, `custo`, `dataCriacao`, `projetoId`.

4. **Mappers (`mapper`):**
   - [x] `ProjetoMapper`: conversão `toEntity` e `toResponseDTO`.
   - [x] `TarefaMapper`: conversão `toEntity` (com preenchimento automático de `LocalDate.now()` e vínculo de entidade `Projeto`) e `toResponseDTO`.

5. **Serviços (`service`):**
   - [x] `ProjetoService`:
     - Cadastro com regra obrigatória de validação contra duplicidade de nome (`existsByNome`).
     - Listagem de todos os projetos (`listAll`).
     - Busca por ID com lançamento de exceção quando não encontrado (`findById`).
     - Atualização de projeto (`update`).
     - Exclusão de projeto (`exclude`).
   - [x] `TarefaService`: estrutura inicial injetada com `TarefaRepository`, `TarefaMapper` e `ProjetoRepository`.

---

### ⏳ O que falta fazer

1. **Camada de Serviço (`TarefaService`):**
   - [ ] Finalizar método `criar` (validando se o projeto vinculado existe no banco antes de salvar).
   - [ ] Implementar `listarTodas` (retornando lista de `TarefaResponseDTO`).
   - [ ] Implementar `buscarPorId` (com tratamento de tarefa não encontrada).
   - [ ] Implementar `atualizar` (atualização dos dados da tarefa).
   - [ ] Implementar `excluir` (verificação de existência e deleção por ID).
   - [ ] Implementar `listarPorProjetoId` (utilizando a consulta JPQL performática do repository).

2. **Relatório Avançado (Critério de maior pontuação - 2,5 pts):**
   - [ ] Criar consulta com SQL Nativo (`@Query(..., nativeQuery = true)`) no `TarefaRepository` retornando `tituloTarefa`, `custo` e `nomeProjeto`.
   - [ ] Criar DTO ou Projeção de interface para mapear a resposta simplificada do relatório.
   - [ ] Criar método no serviço para expor esse relatório.

3. **Controladores REST (`controller`):**
   - [ ] `ProjetoController`:
     - `POST /api/projetos` (Status 201 Created).
     - `GET /api/projetos` (Status 200 OK).
     - `GET /api/projetos/{id}` (Status 200 OK).
     - `PUT /api/projetos/{id}` (Status 200 OK).
     - `DELETE /api/projetos/{id}` (Status 204 No Content).
     - `GET /api/projetos/{id}/tarefas` (Status 200 OK - listar tarefas do projeto).
   - [ ] `TarefaController`:
     - `POST /api/tarefas` (Status 201 Created).
     - `GET /api/tarefas` (Status 200 OK).
     - `GET /api/tarefas/{id}` (Status 200 OK).
     - `PUT /api/tarefas/{id}` (Status 200 OK).
     - `DELETE /api/tarefas/{id}` (Status 204 No Content).
     - `GET /api/tarefas/relatorio` (Status 200 OK - relatório simplificado).

---

## ⚙️ Configuração do Banco de Dados
Configurado no arquivo `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3356/prova_api?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC&
spring.datasource.username=root
spring.datasource.password=mysqlPW
spring.jpa.hibernate.ddl-auto=update
```
