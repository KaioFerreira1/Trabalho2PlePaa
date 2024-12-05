package algoritmoguloso;

import java.util.Arrays;
import java.util.Comparator;

public class KnapsackGuloso {
    public static double calcularMochilaGulosa(int[] pesos, int[] valores, int capacidadeMaxima) {
        int quantidadeItens = pesos.length;
        Item[] itens = new Item[quantidadeItens];

        for (int i = 0; i < quantidadeItens; i++) {
            itens[i] = new Item(pesos[i], valores[i]);
        }

        Arrays.sort(itens, Comparator.comparingDouble(Item::getRazaoValorPeso).reversed());

        double valorTotal = 0;
        int pesoAtual = 0;

        for (Item item : itens) {
            if (pesoAtual + item.peso <= capacidadeMaxima) {
                pesoAtual += item.peso;
                valorTotal += item.valor;
            } else {
                int pesoDisponivel = capacidadeMaxima - pesoAtual;
                valorTotal += item.valor * ((double) pesoDisponivel / item.peso);
                break;
            }
        }

        return valorTotal;
    }

    static class Item {
        int peso;
        int valor;

        Item(int peso, int valor) {
            this.peso = peso;
            this.valor = valor;
        }

        double getRazaoValorPeso() {
            return (double) valor / peso;
        }
    }

    public static void main(String[] args) {
        int[] pesos = {12, 2, 1, 1, 4};
        int[] valores = {4, 2, 2, 1, 10};
        int capacidadeMaxima = 15;

        double valorMaximo = calcularMochilaGulosa(pesos, valores, capacidadeMaxima);
        System.out.println("Valor máximo (Guloso): " + valorMaximo);
    }
}
