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
- **`repository`**: Interfaces de acesso a dados que estendem `JpaRepository` com consultas JPQL e SQL Nativo.
- **`dto`**: Records e Interfaces de Projeção para transferência de dados de entrada (`RequestDTO`) e saída (`ResponseDTO`), evitando vazamento e dependência circular de entidades.
- **`mapper`**: Componentes Spring (`@Component`) responsáveis por converter DTOs em Entidades e vice-versa.
- **`service`**: Camada de lógica de negócio e validações de integridade.
- **`controller`**: Controladores REST com mapeamento de endpoints e códigos de status HTTP apropriados (`201`, `200`, `204`).

---

## 📋 Status de Desenvolvimento (100% Concluído)

### ✅ Entidades de Domínio (`model`):
- [x] `Projeto`: mapeado com `@Entity`, chave primária auto-incrementada e relacionamento `@OneToMany(mappedBy = "projeto")` com `List<Tarefa>`.
- [x] `Tarefa`: mapeado com `@Entity`, atributos obrigatórios (`titulo`, `codigo`, `custo` como `BigDecimal`, `dataCriacao` como `LocalDate`) e relacionamento `@ManyToOne` com chave estrangeira `projeto_id` (`nullable = false`).

### ✅ Repositórios (`repository`):
- [x] `ProjetoRepository`: estendendo `JpaRepository<Projeto, Long>` com o método `existsByNome(String nome)` para validação de duplicidade.
- [x] `TarefaRepository`: 
  - Consulta obrigatória via `@Query` em JPQL realizando `JOIN` performático entre Tarefa e Projeto (`findTarefasByProjetoId`).
  - Consulta via SQL Nativo (`nativeQuery = true`) para o Relatório Avançado simplificado de tarefas e projetos (`relatorioTarefas`).

### ✅ Data Transfer Objects (`dto`):
- [x] `ProjetoRequestDTO`: `nome`.
- [x] `ProjetoResponseDTO`: `id`, `nome`.
- [x] `TarefaRequestDTO`: `titulo`, `codigo`, `custo`, `projetoId`.
- [x] `TarefaResponseDTO`: `id`, `titulo`, `codigo`, `custo`, `dataCriacao`, `projetoId`.
- [x] `TarefaRelatorioDTO`: Interface de projeção com `getTituloTarefa()`, `getCusto()` e `getNomeProjeto()`.

### ✅ Mappers (`mapper`):
- [x] `ProjetoMapper`: conversão `toEntity` e `toResponseDTO` com inicialização de lista de tarefas.
- [x] `TarefaMapper`: conversão `toEntity` (preenchimento automático da data atual `LocalDate.now()` e vínculo da entidade `Projeto`) e `toResponseDTO`.

### ✅ Camada de Serviço (`service`):
- [x] `ProjetoService`: CRUD completo (cadastro impedindo duplicações de nome, listagem geral, busca por ID com validação, atualização e exclusão).
- [x] `TarefaService`: CRUD completo (cadastro validando existência do projeto vinculado, listagem geral, busca por ID, atualização, exclusão, listagem de tarefas por projeto com validação prévia de existência do projeto e chamada do relatório nativo).

### ✅ Controladores REST (`controller`):
- [x] `ProjetoController`:
  - `POST /api/projetos` — Criar projeto (`201 Created`).
  - `GET /api/projetos` — Listar todos os projetos (`200 OK`).
  - `GET /api/projetos/{id}` — Buscar projeto por ID (`200 OK`).
  - `PUT /api/projetos/{id}` — Atualizar projeto (`200 OK`).
  - `DELETE /api/projetos/{id}` — Excluir projeto (`204 No Content`).
  - `GET /api/projetos/{id}/tarefas` — Listar tarefas de um projeto (`200 OK`).
- [x] `TarefaController`:
  - `POST /api/tarefas` — Criar tarefa vinculada a projeto existente (`201 Created`).
  - `GET /api/tarefas` — Listar todas as tarefas (`200 OK`).
  - `GET /api/tarefas/{id}` — Buscar tarefa por ID (`200 OK`).
  - `PUT /api/tarefas/{id}` — Atualizar tarefa (`200 OK`).
  - `DELETE /api/tarefas/{id}` — Excluir tarefa (`204 No Content`).
  - `GET /api/tarefas/relatorio` — Relatório Avançado com Native Query (`200 OK`).

---

## ⚙️ Configuração do Banco de Dados
Configurado no arquivo `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3356/prova_api?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC&
spring.datasource.username=root
spring.datasource.password=mysqlPW
spring.jpa.hibernate.ddl-auto=update
```
