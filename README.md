# Automação API REST

Este projeto tem como objetivo automatizar os testes de APIs REST utilizando Java, RestAssured e JUnit. O foco é facilitar a criação de testes para endpoints de APIs, permitindo validar comportamentos e garantir a qualidade do serviço.

## Tecnologias Utilizadas

- **Java**: Linguagem principal para o desenvolvimento dos testes.
- **Maven**: Gerenciador de dependências e build.
- **RestAssured**: Framework para facilitar a criação de testes de APIs REST.
- **JUnit**: Framework para execução de testes unitários.

## Estrutura do Projeto

A estrutura do projeto segue o padrão do Maven e está organizada da seguinte maneira:

├── src
│ ├── main
│ │ └── java
│ │ └── br
│ │ └── com
│ │ └── automacao
│ │ └── api
│ │ └── endpoints
│ ├── test
│ │ └── java
│ │ └── br
│ │ └── com
│ │ └── automacao
│ │ └── api
│ │ └── testes
├── pom.xml
└── .gitignore


- **src/main/java/br/com/automacao/api/endpoints**: Contém as classes responsáveis pelas requisições para os endpoints da API.
- **src/test/java/br/com/automacao/api/testes**: Contém as classes que implementam os testes automatizados utilizando JUnit.

## Pré-requisitos

Para rodar este projeto, você precisa ter as seguintes ferramentas instaladas:

- **Java 11 ou superior**.
- **Maven**: Para gerenciar dependências e executar os testes.

## Como Rodar o Projeto

1. **Clone o repositório**:

   ```bash
   git clone https://github.com/pdribeiro/automacao-api-rest.git
   cd automacao-api-rest
