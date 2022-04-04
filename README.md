# Descrição do projeto desenvolvido

Foi realizado a implementação do projeto utilizando Java 11, Springboot, Mysql, lombok, log4j e para testes unitários o junit.
A estrutura do projeto foi feita sendo MVC e a API pode ser acessada atraves das chamadas REST, os endpoint desenvolvidos são:

https://localhost:8080/estabelecimento/listar - recupera a lista de estabelecimentos no banco
https://localhost:8080/pessoaFisica/listar - endpoint de teste, apenas lista todos os clientes (não é utilzado pelo front)
https://localhost:8080/transacao/salvarLote - Recebe uma string no corpo da requisição com as transações a serem salva no sistema
https://localhost:8080/transacao/buscarTransacao/{id} - Recebe um id de estabelecimento passado no próprio caminho da url e recupera todas as transações do estabelecimento juntamente com o saldo total.

Sobre a regra de négocio é considerado que caso a pessoa e o estabelecimento não existam eles são inseridos no banco antes das transações.

O front foi feito utilizando Angular 9, como o objetivo é avaliar a parte de java foi feito apenas uma tela simples sem se preocupar muito com o css e estrutura da tela. O projeto pode ser encontrado no seguinte repositório: https://github.com/Thiagofs1211/uploadPesquisaTransacao

Na tela temos um botão para realizar o upload de aquivo e outro para realizar a gravação dos dados do arquivo, logo embaixo temos a lista de estabelecimentos onde é possível selecionar qualquer estabelecimento da base e pesquisar suas transações para verificar os dados de transação em uma tabela e no final o saldo total.
