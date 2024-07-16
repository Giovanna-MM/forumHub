# Fórum Hub

Bem-vindos ao desafio Fórum Hub do programa Oracle Next Education!

## Descrição

O Fórum Hub é uma aplicação back-end que simula um fórum de discussão, onde pessoas usuárias podem criar, listar, atualizar e deletar tópicos relacionados a cursos. Cada tópico deve ter um título, uma mensagem, estar associado a um curso e a uma pessoa usuária.

## Funcionalidades

1. **Criação de Tópicos**
   - Apenas pessoas usuárias cadastradas podem criar tópicos.
   - Cada tópico deve ter um título, mensagem e curso associado.
   - A data de criação é gerada automaticamente.

2. **Listagem de Tópicos**
   - Listar todos os tópicos cadastrados.
   - Listar um tópico específico por ID.

3. **Atualização de Tópicos**
   - Apenas a pessoa autora pode atualizar o tópico.

4. **Deleção de Tópicos**
   - Apenas a pessoa autora pode deletar o tópico.

5. **Segurança e Autenticação**
   - Implementar autenticação via token Bearer.
   - Garantir que apenas usuários autenticados possam realizar operações de criação, atualização e deleção de tópicos.

## Tecnologias Utilizadas

- **Java**
- **Spring Boot**
- **Spring Security**
- **Spring Data JPA**
- **PostgreSQL**
- **Insomnia** (para testar os endpoints)

## Configuração do Ambiente

1. Configure o Spring Initializer com as seguintes dependências:
   - Spring Web
   - Spring Data JPA
   - PostgreSQL Driver
   - Spring Security

2. Configure o banco de dados PostgreSQL e ajuste o `application.properties` com suas credenciais.

## Endpoints

- **GET /topicos**: Lista todos os tópicos.
- **GET /topicos/{id}**: Lista um tópico específico.
- **POST /topicos**: Cria um novo tópico (requer autenticação).
- **PUT /topicos/{id}**: Atualiza um tópico existente (requer autenticação).
- **DELETE /topicos/{id}**: Deleta um tópico (requer autenticação).

## Autenticação

Utilize o endpoint de autenticação para obter um token Bearer:
- **POST /auth**: Autentica a pessoa usuária e retorna um token.
