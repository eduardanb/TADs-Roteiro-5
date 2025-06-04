# TADS-LEDA-ROTEIRO-5

Projeto desenvolvido para a disciplina de Estruturas de Dados e Algoritmos (LEDA) do curso de Tecnologia em Análise e Desenvolvimento de Sistemas (TADS) da UEPB.

## 📚 Sobre

Este repositório contém implementações em Java de estruturas de dados clássicas, incluindo listas encadeadas, listas duplamente encadeadas, pilhas, filas e conjuntos dinâmicos. O objetivo é servir como material de estudo, prática e avaliação para a disciplina.

---

## 🏗️ Estruturas Implementadas

- **Lista Encadeada**  
  Inserção, remoção, busca, impressão em ordem e reversa, conversão para array.

- **Lista Duplamente Encadeada**  
  Inserção/remoção nas extremidades, busca, predecessor, sucessor, impressão em ordem e reversa.

- **Pilha Encadeada**  
  Empilhar, desempilhar, consultar topo, verificar se está vazia, multitop.

- **Fila Encadeada**  
  Enfileirar, desenfileirar, consultar cabeça e cauda, verificar se está cheia ou vazia.

- **Conjunto Dinâmico**  
  Inserção, remoção, busca, mínimo, máximo, sucessor e predecessor.


## 🚀 Como Executar

### Pré-requisitos

- Java JDK 8 ou superior
- JUnit 5 (para testes)
- IDE recomendada: Visual Studio Code ou Eclipse

### Passos

1. Clone o repositório:
   ```sh
   git clone <url-do-repositorio>

## Exemplos de Uso

### Lista Encadeada
ListaEncadeadaIF<Integer> lista = new ListaEncadeadaImpl<>();
lista.inserir(10);
lista.inserir(20);
lista.remover(10);
System.out.println(Arrays.toString(lista.toArray())); // [20]

### Pilha Encadeada
ListaEncadeadaIF<Integer> lista = new ListaEncadeadaImpl<>();
lista.inserir(10);
lista.inserir(20);
lista.remover(10);
System.out.println(Arrays.toString(lista.toArray())); // [20]

### Fila Encadeada
FilaIF<Integer> fila = new MinhaFilaEncadeada<>();
fila.enfileirar(5);
fila.enfileirar(8);
System.out.println(fila.cabeca()); // 5
fila.desenfileirar();
System.out.println(fila.cabeca()); // 8

### Conjunto Dinâmico
ConjuntoDinamicoIF conjunto = new ConjuntoDinamicoImpl();
conjunto.inserir(7);
conjunto.inserir(3);
System.out.println(conjunto.minimo()); // 3
System.out.println(conjunto.maximo()); // 7

## Autores
Desenvolvido por Adrielly Carla Ferreira de Melo, João Victor da Silva Almeida Guimarãres e Mari Eduarda da Nóbrega.