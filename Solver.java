package NRainhasBlock;
import java.util.*;

public class Solver {

    private static final long TIMEOUT_MILLIS = 130000; 

    public static boolean ehSeguro(boolean[] colunasOcupadas, boolean[] diag1Ocupadas, boolean[] diag2Ocupadas, int linha, int coluna, int n) {
        return !colunasOcupadas[coluna]
                && !diag1Ocupadas[linha - coluna + n - 1]
                && !diag2Ocupadas[linha + coluna];
    }

    public static Object[] resolverRainhasComBloqueios(char[][] tabuleiro) {
        int n = tabuleiro.length;
        long tempoInicio = System.currentTimeMillis();
        int[] contadorNos = {0};
        long[] tempoInicial = {tempoInicio};

        boolean[] colunasOcupadas = new boolean[n];
        boolean[] diag1Ocupadas = new boolean[2 * n - 1];
        boolean[] diag2Ocupadas = new boolean[2 * n - 1];

        boolean solucao = dfs(tabuleiro, 0, colunasOcupadas, diag1Ocupadas, diag2Ocupadas, contadorNos, tempoInicial);
        long tempoTotal = System.currentTimeMillis() - tempoInicio;

        if (solucao) {
            System.out.println("Tabuleiro com a solução:");
            imprimirTabuleiroComRainhas(tabuleiro);
        } else {
            System.out.println("Busca interrompida por limite de tempo ou não encontrou solução.");
        }

        return new Object[]{solucao, tempoTotal, contadorNos[0]};
    }

    private static boolean dfs(char[][] tabuleiro, int linha, boolean[] colunasOcupadas, boolean[] diag1Ocupadas,
                               boolean[] diag2Ocupadas, int[] contadorNos, long[] tempoInicial) {
        int n = tabuleiro.length;

        if (System.currentTimeMillis() - tempoInicial[0] > TIMEOUT_MILLIS) return false;
        if (linha == n) return true;

        List<Integer> colunasOrdenadas = ordenarColunasHeuristica(tabuleiro, linha, colunasOcupadas, diag1Ocupadas, diag2Ocupadas);

        for (int coluna : colunasOrdenadas) {
            contadorNos[0]++;
            if (tabuleiro[linha][coluna] == '.' && ehSeguro(colunasOcupadas, diag1Ocupadas, diag2Ocupadas, linha, coluna, n)) {
                colunasOcupadas[coluna] = true;
                diag1Ocupadas[linha - coluna + n - 1] = true;
                diag2Ocupadas[linha + coluna] = true;
                tabuleiro[linha][coluna] = 'R';

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

    private static List<Integer> ordenarColunasHeuristica(char[][] tabuleiro, int linha, boolean[] colunasOcupadas,
                                                          boolean[] diag1Ocupadas, boolean[] diag2Ocupadas) {
        int n = tabuleiro.length;
        List<int[]> colunasComPeso = new ArrayList<>();

        for (int coluna = 0; coluna < n; coluna++) {
            if (tabuleiro[linha][coluna] == '.' && ehSeguro(colunasOcupadas, diag1Ocupadas, diag2Ocupadas, linha, coluna, n)) {
                int peso = 0;

               
                for (int offset = 1; offset <= 2; offset++) {
                    int proximaLinha = linha + offset;
                    if (proximaLinha < n) {
                        for (int c = 0; c < n; c++) {
                            if (tabuleiro[proximaLinha][c] == '.' && !colunasOcupadas[c]
                                    && !diag1Ocupadas[proximaLinha - c + n - 1]
                                    && !diag2Ocupadas[proximaLinha + c]) {
                                peso++;
                            }
                        }
                    }
                }

              
                int bloqueiosProximos = 0;
                for (int offset = 1; offset <= 2; offset++) {
                    int proximaLinha = linha + offset;
                    if (proximaLinha < n) {
                        for (int c = 0; c < n; c++) {
                            if (tabuleiro[proximaLinha][c] == 'X') bloqueiosProximos++;
                        }
                    }
                }

                peso -= bloqueiosProximos;

                
                int centro = n / 2;
                peso -= Math.abs(coluna - centro) / 4;

                colunasComPeso.add(new int[]{coluna, peso});
            }
        }

       
        colunasComPeso.sort((a, b) -> Integer.compare(b[1], a[1]));

        List<Integer> resultado = new ArrayList<>();
        for (int[] par : colunasComPeso) {
            resultado.add(par[0]);
        }

        return resultado;
    }

    public static void imprimirTabuleiroComRainhas(char[][] tabuleiro) {
        int n = tabuleiro.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(tabuleiro[i][j] + " ");
            }
            System.out.println();
        }
    }
}
