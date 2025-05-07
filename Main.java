package NRainhasBlock;
import java.util.Random;

public class Main {

    public static double percentualAleatorio(Integer seed, double minPerc, double maxPerc) {
        Random rng = seed != null ? new Random(seed) : new Random();
        return minPerc + (maxPerc - minPerc) * rng.nextDouble();
    }

    public static void testarTamanho(int n, Integer seed) {
        System.out.println("\nTestando tabuleiro " + n + "x" + n + " com bloqueios aleatórios");

        double perc = percentualAleatorio(seed, 0.07, 0.09);
        int totalCasas = n * n;
        int numBloqueios = (int) (perc * totalCasas);

        System.out.println("Percentual de bloqueios: " + String.format("%.2f", perc * 100) + "% (" + numBloqueios + " casas)");

        long startTabuleiro = System.nanoTime();
        Object[] tabuleiroResult = Tabuleiro.gerarTabuleiroComBloqueiosMelhorado(n, numBloqueios, seed, 0.2);
        char[][] tabuleiro = (char[][]) tabuleiroResult[0];
        long endTabuleiro = System.nanoTime();
        System.out.println("Tempo para gerar tabuleiro com bloqueios: " + ((endTabuleiro - startTabuleiro) / 1_000_000) + " ms");

        if (n <= 256) {
            Tabuleiro.imprimirTabuleiro(tabuleiro, 256);
        }

        long startSolver = System.nanoTime();
        Object[] resultado = Solver.resolverRainhasComBloqueios(tabuleiro);
        long endSolver = System.nanoTime();
        boolean solucao = (boolean) resultado[0];
        long tempo = (long) resultado[1];
        int nos = (int) resultado[2];

        if (solucao) {
            System.out.println("Solução encontrada em " + tempo + " ms");
        } else {
            System.out.println("Nenhuma solução encontrada.");
        }
        System.out.println("Número de nós: " + nos);
        System.out.println("Tempo total do solver: " + ((endSolver - startSolver) / 1_000_000) + " ms");
    }

    public static void main(String[] args) {
        int[] tamanhos = {8, 16, 32, 64,70,84,99,100, 128};
        Integer seed = 42;

        for (int n : tamanhos) {
            testarTamanho(n, seed);
        }
    }
}
