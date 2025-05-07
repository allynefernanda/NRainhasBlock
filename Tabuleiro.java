package NRainhasBlock;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Tabuleiro {

    public static Object[] gerarTabuleiroComBloqueiosMelhorado(int n, Integer numBloqueios, Integer seed, double percentualMax) {
        Random random = seed != null ? new Random(seed) : new Random();  

        int totalCasas = n * n;
        int maxBloqueios = Math.min(totalCasas - n, (int) (percentualMax * totalCasas));

        if (numBloqueios == null) {
            numBloqueios = (int) (0.07 * totalCasas);
        } else if (numBloqueios > maxBloqueios) {
            throw new IllegalArgumentException("Número de bloqueios excede o máximo permitido.");
        }

        char[][] tabuleiro = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tabuleiro[i][j] = '.';
            }
        }

        Set<String> bloqueios = new HashSet<>();
        while (bloqueios.size() < numBloqueios) {
            int i = random.nextInt(n);
            int j = random.nextInt(n);

            String posicao = i + "," + j;
            if (!bloqueios.contains(posicao)) {
                tabuleiro[i][j] = 'X';
                bloqueios.add(posicao);
            }
        }

        return new Object[]{tabuleiro, bloqueios};
    }

    public static void imprimirTabuleiro(char[][] tabuleiro, int limite) {
        int n = tabuleiro.length;
        for (int i = 0; i < Math.min(n, limite); i++) {
            for (int j = 0; j < Math.min(n, limite); j++) {
                System.out.print(tabuleiro[i][j] + " ");
            }
            System.out.println();
        }
    }
}
