package NRainhasBlock;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class NRainhasComBloqueios {

    private static final Random random = new Random();

    private static boolean solve(int n, boolean[][] blocked) {
        int[] queens = new int[n];
        Arrays.fill(queens, -1);
        int[] nodes = {0};
        long start = System.nanoTime();
        boolean found = dfs(0, n, blocked, queens, nodes, start);
        long end = System.nanoTime();

        System.out.println("Solução encontrada: " + found);
        System.out.println("Número de nós (memória): " + nodes[0]);
        System.out.println("Tempo de processamento: " + ((end - start) / 1_000_000) + " ms");
        if (found) printBoard(queens, blocked, n);
        return found;
    }

    private static boolean dfs(int col, int n, boolean[][] blocked, int[] queens, int[] nodes, long start) {
        // Limite de tempo (5 segundos)
        /*if ((System.nanoTime() - start) > 20_000_000_000L) {
            System.out.println("Tempo excedido.");
            return false;
        }*/

        if (col == n) return true;
        for (int row = 0; row < n; row++) {
            nodes[0]++;
            if (isSafe(row, col, queens, blocked)) {
                queens[col] = row;
                if (dfs(col + 1, n, blocked, queens, nodes, start)) return true;
                queens[col] = -1;
            }
        }
        return false;
    }

    private static boolean isSafe(int row, int col, int[] queens, boolean[][] blocked) {
        if (blocked[row][col]) return false;
        for (int c = 0; c < col; c++) {
            int r = queens[c];
            if (r == row || Math.abs(r - row) == Math.abs(c - col)) return false;
        }
        return true;
    }

    private static boolean[][] gerarBloqueios(int n, double percentualMin, double percentualMax) {
        boolean[][] blocked = new boolean[n][n];
        int total = n * n;
        int min = (int)(percentualMin * total);
        int max = (int)(percentualMax * total);
        int count = random.nextInt(max - min + 1) + min;

        Set<String> usados = new HashSet<>();
        while (usados.size() < count) {
            int i = random.nextInt(n);
            int j = random.nextInt(n);
            String pos = i + "," + j;
            if (usados.add(pos)) blocked[i][j] = true;
        }

        return blocked;
    }

    private static void printBoard(int[] queens, boolean[][] blocked, int n) {
        System.out.println("Tabuleiro:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (blocked[i][j]) System.out.print(" X ");
                else if (queens[j] == i) System.out.print(" '\\u265B' ");
                else System.out.print(" . ");
            }
            System.out.println();
        }
    }

    /*public static void main(String[] args) {
        int[] tamanhos = {8, 16, 32, 64,128};  
        for (int n : tamanhos) {
            System.out.println("======= Testando n = " + n + " =======");
            boolean[][] bloqueados = gerarBloqueios(n, 0.07, 0.13);
            solve(n, bloqueados);
        }
    }*/
}
