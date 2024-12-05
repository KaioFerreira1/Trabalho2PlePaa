package programacaodinamica;

public class KnapsackDP {
    public static int calcularMochilaPD(int[] pesos, int[] valores, int capacidadeMaxima) {
        int quantidadeItens = pesos.length;
        int[][] tabelaDP = new int[quantidadeItens + 1][capacidadeMaxima + 1];

        for (int item = 1; item <= quantidadeItens; item++) {
            for (int capacidadeAtual = 0; capacidadeAtual <= capacidadeMaxima; capacidadeAtual++) {
                if (pesos[item - 1] <= capacidadeAtual) {
                    tabelaDP[item][capacidadeAtual] = Math.max(
                            tabelaDP[item - 1][capacidadeAtual],
                            valores[item - 1] + tabelaDP[item - 1][capacidadeAtual - pesos[item - 1]]
                    );
                } else {
                    tabelaDP[item][capacidadeAtual] = tabelaDP[item - 1][capacidadeAtual];
                }
            }
        }

        return tabelaDP[quantidadeItens][capacidadeMaxima];
    }

    public static void main(String[] args) {
        int[] pesos = {12, 2, 1, 1, 4};
        int[] valores = {4, 2, 2, 1, 10};
        int capacidadeMaxima = 15;

        int valorMaximo = calcularMochilaPD(pesos, valores, capacidadeMaxima);
        System.out.println("Valor máximo (Programação Dinâmica): " + valorMaximo);
    }
}
