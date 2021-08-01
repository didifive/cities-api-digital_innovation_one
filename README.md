# Construindo uma API Rest de consulta de cidades do Brasil do zero até a produção

## Digital Innovation One: Bootcamp everis Quality Assurance Beginner #2

Projeto desenvolvido com instruções de [André Luis Gomes](https://github.com/andrelugomes) na trilha de estudo do Bootcamp everis Quality Assurance Beginner #2 da [Digital Innovation One](https://digitalinnovation.one/).

Link da base utilizada neste projeto: [andrelugomes/digital-innovation-one](https://github.com/andrelugomes/digital-innovation-one)

Abaixo seguem modificações feitas em relação ao projeto base:
* O projeto foi feito do zero, como o nome propõe, copiando, colando e digitando, aproveitando para leve mudança na estrutura dos arquivos e nomes de classes;
* Criada consulta de cidade em que lista a(s) cidade(s) iniciada(s) pelo nome passado (case insensitive);
* Exceptions criadas para tratar quando não localizar país, estado ou cidade e por id ou nome;
* Para os testes funcionarem foi necessário alteração no arquivo "data.sql" para fazer com o que o campo "ddd" da tabela estado tivesse o conteúdo em JSON e a adição do "spring.datasource.sqlScriptEncoding=UTF-8" arquivo application.properties do src/test para funcionalidade correta dos dados com acentuação;

Versões utilizadas no desenvolvimento:
* Java 8 (para compatibilidade com Heroku);
* Gradle 7.1;
* IntelliJ IDEA Community Edition 2021.2.

Este projeto, principalmente os pontos destacados abaixo, ajudou a aprimorar os conhecimentos em Java:
* Construir uma API do zero, sem apenas refatorar ou revisar, aprimorando o conhecimento em spring, gradle, dependências e do próprio java;
* Utilização do Docker para criar banco PostgreSQL e utilização do serviço do Heroku CLI;
* Utilização do PostgreSQL para produção e H2 para teste.

O projeto foi publicado no cloud da [Heroku](https://www.heroku.com/), abaixo o link:
* [Cities API Didi](https://cities-api-didi.herokuapp.com)
* Para testar manualmente os métodos da Cities API, foi incluído o arquivo "Game API.postman_collection.json" na pasta "postman" com coleção que pode ser importada pelo aplicativo [Postman](https://www.postman.com/).

Para executar o projeto no terminal, digite o seguinte comando:
```shell script
gradle bootRun 
```

Após executar o comando acima, basta apenas abrir o seguinte endereço e visualizar a execução do projeto:
```
http://localhost:8080/api/v1/games
```

Para executar a suíte de testes desenvolvida, execute o seguinte comando:
```shell script
gradle clean test
```