# NRainhas com Bloqueios

Este projeto implementa a solução do problema das N-Rainhas com bloqueios no tabuleiro, utilizando a busca em profundidade (DFS) para encontrar uma solução viável onde as rainhas não se ataquem e respeitem as casas bloqueadas.

## Descrição

O problema das N-Rainhas consiste em colocar N rainhas em um tabuleiro de NxN de forma que nenhuma rainha se ataque. Cada rainha pode atacar em sua linha, coluna ou diagonal. No entanto, neste projeto, algumas casas do tabuleiro são bloqueadas, e as rainhas não podem ser colocadas nessas casas.

A solução é implementada utilizando DFS para explorar as possíveis colocações das rainhas, com uma verificação de segurança para garantir que não há conflitos. O algoritmo também pode gerar tabuleiros com bloqueios aleatórios e testar o desempenho da solução em diferentes tamanhos de tabuleiro.

## Funcionalidades

- **Gerar tabuleiros com bloqueios aleatórios**: O tabuleiro pode ter casas bloqueadas aleatoriamente, de acordo com um percentual definido.
- **Solução com DFS**: Utiliza DFS para resolver o problema das N-Rainhas, levando em consideração as casas bloqueadas.
- **Heurísticas de busca**: O algoritmo de DFS usa heurísticas para ordenar as colunas a serem verificadas, priorizando as com menos opções válidas para otimizar o processo.
- **Limite de tempo**: O algoritmo pode ser configurado para ter um limite de tempo, interrompendo a busca se uma solução não for encontrada dentro do período (500 segundos, por padrão).

## Requisitos

- Java 11 ou superior
- JUnit 5 (para testes unitários)

## Estrutura do Projeto

- **Main**: Classe principal que executa o algoritmo, testa o tamanho do tabuleiro e mede o tempo de execução.
- **NRainhasComBloqueios**: Implementação do algoritmo de solução das N-Rainhas com bloqueios, usando DFS.
- **Solver**: Classe responsável por resolver o problema das N-Rainhas, utilizando heurísticas para ordenar as colunas durante a busca.
- **Tabuleiro**: Classe que gera o tabuleiro com bloqueios aleatórios.

## Como Rodar

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/NRainhasBlock.git

2. Compile o código:
   ```bash
   javac -cp .:junit-platform-console-standalone-1.8.0.jar NRainhasBlock/*.java
3. Execute o programa principal:
   ```bash
   java NRainhasBlock.Main
