package NRainhasBlock;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Solver {

    private static final long TIMEOUT_MILLIS = 500000;

    public static Object[] resolverRainhasComBloqueios(char[][] tabuleiro) {
        int n = tabuleiro.length;
        long tempoInicio = System.currentTimeMillis();
        int[] contadorNos = {0};

        if (!preProcessarTabuleiro(tabuleiro)) {
            System.out.println("Tabuleiro inviável antes mesmo da busca.");
            return new Object[]{false, 0L, 0};
        }

        boolean[] colunasOcupadas = new boolean[n];
        boolean[] diag1Ocupadas = new boolean[2 * n - 1];
        boolean[] diag2Ocupadas = new boolean[2 * n - 1];

        boolean solucao = dfs(tabuleiro, 0, colunasOcupadas, diag1Ocupadas, diag2Ocupadas, contadorNos, tempoInicio);
        long tempoTotal = System.currentTimeMillis() - tempoInicio;

        if (solucao) {
            System.out.println("Tabuleiro com a solução:");
            imprimirTabuleiroComRainhas(tabuleiro, 128);
            salvarTabuleiroEmArquivo(tabuleiro, "solucao_" + n + "x" + n + ".txt");
        } else {
            System.out.println("Busca interrompida por limite de tempo ou não encontrou solução.");
        }

        return new Object[]{solucao, tempoTotal, contadorNos[0]};
    }

    private static boolean dfs(char[][] tabuleiro, int linha, boolean[] colunasOcupadas,
                               boolean[] diag1Ocupadas, boolean[] diag2Ocupadas, int[] contadorNos, long tempoInicial) {
        int n = tabuleiro.length;

        if (System.currentTimeMillis() - tempoInicial > TIMEOUT_MILLIS) return false;
        if (linha == n) return true;

        List<Integer> colunasOrdenadas = ordenarColunasPorOpcoes(tabuleiro);

        for (int coluna : colunasOrdenadas) {
            contadorNos[0]++;
            if (tabuleiro[linha][coluna] == '.' && ehSeguro(colunasOcupadas, diag1Ocupadas, diag2Ocupadas, linha, coluna, n)) {
                colunasOcupadas[coluna] = true;
                diag1Ocupadas[linha - coluna + n - 1] = true;
                diag2Ocupadas[linha + coluna] = true;
                tabuleiro[linha][coluna] = '♛';

                if (dfs(tabuleiro, linha + 1, colunasOcupadas, diag1Ocupadas, diag2Ocupadas, contadorNos, tempoInicial)) {
                    return true;
                }

                colunasOcupadas[coluna] = false;
                diag1Ocupadas[linha - coluna + n - 1] = false;
                diag2Ocupadas[linha + coluna] = false;
                tabuleiro[linha][coluna] = '.';
            }
        }

        return false;
    }

    private static boolean ehSeguro(boolean[] colunasOcupadas, boolean[] diag1Ocupadas, boolean[] diag2Ocupadas,
                                    int linha, int coluna, int n) {
        return !colunasOcupadas[coluna]
                && !diag1Ocupadas[linha - coluna + n - 1]
                && !diag2Ocupadas[linha + coluna];
    }

    private static List<Integer> ordenarColunasPorOpcoes(char[][] tabuleiro) {
        int n = tabuleiro.length;
        int[] opcoesPorColuna = new int[n];

        for (int linha = 0; linha < n; linha++) {
            for (int coluna = 0; coluna < n; coluna++) {
                if (tabuleiro[linha][coluna] == '.') {
                    opcoesPorColuna[coluna]++;
                }
            }
        }

        List<Integer> colunas = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            colunas.add(i);
        }

        colunas.sort(Comparator.comparingInt(c -> opcoesPorColuna[c]));
        return colunas;
    }

    private static boolean preProcessarTabuleiro(char[][] tabuleiro) {
        int n = tabuleiro.length;
        boolean[] linhasValidas = new boolean[n];
        boolean[] colunasValidas = new boolean[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tabuleiro[i][j] == '.') {
                    linhasValidas[i] = true;
                    colunasValidas[j] = true;
                }
            }
        }

        int linhas = 0, colunas = 0;
        for (boolean b : linhasValidas) if (b) linhas++;
        for (boolean b : colunasValidas) if (b) colunas++;

        return linhas >= n && colunas >= n;
    }

    public static void imprimirTabuleiroComRainhas(char[][] tabuleiro, int limite) {
        int n = tabuleiro.length;
        System.out.println("Exibindo tabuleiro com solução (limite: " + limite + "x" + limite + "):");
        for (int i = 0; i < Math.min(n, limite); i++) {
            for (int j = 0; j < Math.min(n, limite); j++) {
                System.out.print(tabuleiro[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void salvarTabuleiroEmArquivo(char[][] tabuleiro, String nomeArquivo) {
        int n = tabuleiro.length;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    writer.write(tabuleiro[i][j]);
                    writer.write(' ');
                }
                writer.newLine();
            }
            System.out.println("Tabuleiro salvo em: " + nomeArquivo);
        } catch (IOException e) {
            System.err.println("Erro ao salvar tabuleiro: " + e.getMessage());
        }
    }
}
