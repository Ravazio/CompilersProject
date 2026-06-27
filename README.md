# CompilersProject

Projeto desenvolvido para a disciplina **Compiladores e Interpretadores** da **UFABC**, com implementação de um compilador baseado em **ANTLR** que traduz uma linguagem fonte própria para **Java**.

## Sobre o projeto

Este projeto implementa um compilador para a linguagem **Isi**, definida por uma gramática ANTLR e acompanhada de regras léxicas, sintáticas e semânticas. O fluxo principal lê um arquivo de entrada, realiza a análise léxica e sintática, executa validações semânticas e gera como saída um arquivo `.java` correspondente ao programa compilado.

A gramática foi construída para suportar declaração de variáveis, atribuições, entrada e saída, expressões aritméticas, condicionais e estruturas de repetição. Além disso, o compilador mantém tabela de símbolos, checagem de tipos, rastreamento de inicialização de variáveis e emissão de avisos para variáveis declaradas e não utilizadas.

## Funcionalidades

- Análise léxica e sintática com **ANTLR** 
- Geração de código-alvo em **Java** 
- Declaração de variáveis com tipos `number`, `real` e `text` 
- Comandos de leitura e escrita (`leia`, `escreva`, `escrevaln`) 
- Atribuições com verificação semântica 
- Estruturas condicionais `se / senao` 
- Estruturas de repetição `enquanto` e `faca... enquanto` 
- Detecção de erros semânticos, como variável não declarada ou uso sem inicialização 
- Geração de warnings para variáveis não utilizadas 

## Estrutura da linguagem fonte

A linguagem compilada neste projeto segue uma estrutura geral como:

```text
programa nomeDoPrograma
    declare variavel1, variavel2: number;
    declare mensagem: text;
inicio
   ... comandos...
fim
fimprog
```

A regra principal da gramática define um programa com bloco de declarações, bloco principal de comandos e encerramento explícito por `fimprog`.

## Tipos suportados

A gramática define três tipos primitivos:

- `number`
- `real`
- `text` 

## Comandos suportados

Com base na gramática, o compilador aceita comandos como:

- Leitura: `leia(id);` 
- Escrita com quebra de linha: `escrevaln(...);` 
- Escrita sem quebra de linha: `escreva(...);` 
- Atribuição: `id := expr;` 
- Condicional: `se (...) entao... fimEntao... fimse` 
- Repetição `while`: `enquanto (...) {... }` 
- Repetição `do while`: `faca {... } enquanto (...) fimDW` 

## Análise semântica

O projeto implementa verificações semânticas diretamente na gramática e em estruturas auxiliares do compilador. Entre as validações observadas estão verificação de declaração prévia de identificadores, checagem de compatibilidade de tipos em atribuições, bloqueio de uso de variáveis sem valor atribuído e geração de avisos para símbolos declarados e não utilizados.

Erros semânticos são lançados com mensagens explícitas, como `Undeclared Variable`, `Type Mismatching on Assignment` e indicação de variável sem valor atribuído.

## Geração de código

A classe principal instancia o lexer e o parser, processa um arquivo de entrada e, ao final da compilação, obtém um objeto `Program` que gera o código Java de saída. Esse código é salvo automaticamente em um arquivo com o nome do programa seguido da extensão `.java`.

## Como executar

### Pré-requisitos

- **Java**
- **ANTLR 4**
- Classes auxiliares do projeto, como AST, tipos, runtime e exceções, referenciadas pela gramática e pela classe principal 

### Entrada

A execução principal usa um arquivo chamado `input.in` como programa-fonte de entrada.

### Execução

O fluxo principal é:

1. Ler o arquivo-fonte.
2. Executar o lexer e o parser.
3. Chamar a regra principal `prog()`.
4. Exibir warnings semânticos.
5. Gerar o código Java correspondente.

## Exemplo de pipeline

```text
Arquivo.in -> Lexer ANTLR -> Parser ANTLR -> Análise semântica -> AST/Program -> Arquivo.java
```

## Contexto acadêmico

Este projeto foi desenvolvido como trabalho da disciplina **Compiladores e Interpretadores** da **UFABC**, com foco na construção de um compilador educacional capaz de traduzir uma linguagem fonte simplificada para Java. O projeto demonstra conceitos clássicos de compiladores, como análise léxica, análise sintática, tabela de símbolos, checagem semântica e geração de código.


## Autores

Joel da Silva Junior 
Victor Finotti Pássaro
Victor Ravazio de Lima

Projeto desenvolvido para a disciplina de Compiladores e Interpretadores da UFABC, ministrada pelo professor Francisco Isidro Massetto. 
Ele contém um compilador desenvolvido baseado no ANTLR que converte uma liguagem fonte para JAVA.

Link para o video da explicação no Youtube: https://youtu.be/sK-w19HRX5M
![Critérios atingidos da Checklist](https://github.com/user-attachments/assets/0f8975be-5cfa-4f6f-b12d-3bdb2b53a09b)
