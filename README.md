NRainhas com Bloqueios
Este projeto implementa a solução do problema das N-Rainhas com bloqueios no tabuleiro, utilizando a busca em profundidade (DFS) para encontrar uma solução viável onde as rainhas não se ataquem e respeitem as casas bloqueadas.

Descrição
O problema das N-Rainhas consiste em colocar N rainhas em um tabuleiro de NxN de forma que nenhuma rainha se ataque. Cada rainha pode atacar em sua linha, coluna ou diagonal. No entanto, neste projeto, algumas casas do tabuleiro são bloqueadas, e as rainhas não podem ser colocadas nessas casas.

A solução é implementada utilizando DFS para explorar as possíveis colocações das rainhas, com uma verificação de segurança para garantir que não há conflitos. O algoritmo também pode gerar tabuleiros com bloqueios aleatórios e testar o desempenho da solução em diferentes tamanhos de tabuleiro.

Funcionalidades
Gerar tabuleiros com bloqueios aleatórios: O tabuleiro pode ter casas bloqueadas aleatoriamente, de acordo com um percentual definido.

Solução com DFS: Utiliza DFS para resolver o problema das N-Rainhas, levando em consideração as casas bloqueadas.

Heurísticas de busca: O algoritmo de DFS usa heurísticas para ordenar as colunas a serem verificadas, levando em consideração bloqueios próximos e a posição central do tabuleiro.

Limite de tempo: O algoritmo pode ser configurado para ter um limite de tempo, interrompendo a busca se uma solução não for encontrada dentro do período.

Requisitos
Java 11 ou superior

JUnit 5 (para testes unitários)

Estrutura do Projeto
Main: Classe principal que executa o algoritmo, testa o tamanho do tabuleiro e mede o tempo de execução.

NRainhasComBloqueios: Implementação do algoritmo de solução das N-Rainhas com bloqueios, usando DFS.

Solver: Classe responsável por resolver o problema das N-Rainhas, utilizando heurísticas para ordenar as colunas durante a busca.

Tabuleiro: Classe que gera o tabuleiro com bloqueios aleatórios.

Como Rodar
Clone o repositório:

bash
Copiar código
git clone https://github.com/seu-usuario/NRainhasBlock.git
Compile o código:

bash
Copiar código
javac -cp .:junit-platform-console-standalone-1.8.0.jar NRainhasBlock/*.java
Execute o programa principal:

bash
Copiar código
java NRainhasBlock.Main
(Opcional) Para rodar os testes unitários com JUnit 5:

bash
Copiar código
java -jar junit-platform-console-standalone-1.8.0.jar --class-path . --scan-class-path
Exemplo de Saída
O programa imprimirá o tabuleiro e os resultados da busca. Aqui está um exemplo:

bash
Copiar código
Testando tabuleiro 8x8 com bloqueios aleatórios
Percentual de bloqueios: 10.00% (64 casas)
Tempo para gerar tabuleiro com bloqueios: 10 ms
Solução encontrada em 5 ms
Número de nós: 50
Tempo total do solver: 12 ms
Testes
O projeto inclui testes unitários para garantir que o algoritmo funcione corretamente em diferentes tamanhos de tabuleiro. Para executar os testes, utilize o JUnit 5 conforme descrito nas instruções de execução.

Licença
