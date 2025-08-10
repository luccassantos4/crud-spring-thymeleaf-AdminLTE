
# CRUD Spring Boot + Thymeleaf + AdminLTE

Projeto completo de CRUD (Create, Read, Update, Delete) para gerenciamento de produtos e categorias, utilizando Spring Boot, Thymeleaf, AdminLTE e banco de dados MySQL.

---

## :rocket: Visão Geral

Este sistema web permite o cadastro, listagem, edição e exclusão de produtos e categorias, com interface moderna baseada no template AdminLTE. O projeto é ideal para estudos de arquitetura MVC, integração com banco de dados relacional e uso de templates dinâmicos com Thymeleaf.

---

## :hammer_and_wrench: Tecnologias Utilizadas

- **Java 11**
- **Spring Boot 2.6.12** (Web, Data JPA, Validation, Thymeleaf)
- **MySQL** (pode ser adaptado para H2 ou PostgreSQL)
- **Thymeleaf**
- **Bootstrap 5**
- **AdminLTE** (dashboard e layout)
- **Maven**

---

## :books: Funcionalidades

- CRUD completo de **Categorias**
- CRUD completo de **Produtos**
- Relacionamento N:N entre Produtos e Categorias
- Listagem paginada e ordenada
- Validação de formulários
- Interface responsiva e moderna
- Dashboard com AdminLTE

---

## :file_folder: Estrutura do Projeto

```
src/main/java/com/github/luccassantos4/dscatalog/
├── entities/         # Entidades JPA (Product, Category)
├── repositories/     # Repositórios Spring Data JPA
├── services/         # Camada de serviço e regras de negócio
├── dto/              # Data Transfer Objects
└── resources/
    └── templates/    # Templates Thymeleaf (views)
        ├── layout_admin/  # Layout base AdminLTE
        └── views/        # Telas de produto e categoria
```

---

## :gear: Como Executar

1. **Pré-requisitos:**
   - Java 11+
   - Maven
   - MySQL (ou H2/PostgreSQL, adaptando o `application.properties`)

2. **Configuração do Banco:**
   - Configure o acesso ao banco em `src/main/resources/application-test.properties`:
     ```
     spring.datasource.url=jdbc:mysql://localhost:3306/crudspring_v2?useTimezone=true&serverTimezone=UTC
     spring.datasource.username=SEU_USUARIO
     spring.datasource.password=SUA_SENHA
     ```
   - O script `import.sql` popula o banco com dados de exemplo automaticamente.

3. **Build e Execução:**
   ```bash
   ./mvnw spring-boot:run
   ```
   Ou rode a classe `DscatalogApplication.java` pela sua IDE.

4. **Acesso:**
   - Acesse: [http://localhost:8080](http://localhost:8080)

---

## :mag: Exemplos Visuais

<div align="center">
  <h4>Página Inicial (Dashboard)</h4>
  <img src="https://user-images.githubusercontent.com/62127980/196528275-2fdbc318-0019-4dc6-9dae-d01510f742e7.jpg" width="700">
</div><br>

<div align="center">
  <h4>Lista de Produtos</h4>
  <img src="https://user-images.githubusercontent.com/62127980/196528281-59a6b462-02c7-48f6-8e36-23e143d171ad.jpg" width="700">
</div><br>

<div align="center">
  <h4>Edição de Produto</h4>
  <img src="https://user-images.githubusercontent.com/62127980/196528295-5643db0a-9ead-430a-8529-e95b8714ecbe.jpg" width="700">
</div><br>

<div align="center">
  <h4>Edição de Categoria</h4>
  <img src="https://user-images.githubusercontent.com/62127980/196528301-132d3662-980a-4630-8d67-1b53b7b74438.jpg" width="700">
</div><br>

<div align="center">
  <h4>Lista de Categorias</h4>
  <img src="https://user-images.githubusercontent.com/62127980/196528305-9b6b0259-c78f-4caf-b05a-dcf5d5938cef.jpg" width="700">
</div><br>

---

## :page_facing_up: Observações

- O projeto pode ser facilmente expandido para incluir autenticação, autorização, upload de imagens, etc.
- Para ambiente de testes, utilize o banco H2 (já configurado no projeto, basta ajustar o profile).


