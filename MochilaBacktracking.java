package org.example.juegomochila;

import java.util.ArrayList;
import java.util.List;

public class MochilaBacktracking {

    public static void main(String[] args) {
        int capacidadMochila = 15;
        Elemento[] elementos = {
                new Elemento(1, 1),
                new Elemento(1, 2),
                new Elemento(2, 2),
                new Elemento(4, 10),
                new Elemento(12, 4)
        };

        List<Elemento> mejorCombinacion = obtenerMejorCombinacion(elementos, capacidadMochila);

        System.out.println("-- JUEGO DE LA MOCHILA --");
        System.out.println("Mejor combinación:");
        for (Elemento elemento : mejorCombinacion) {
            System.out.println("Peso: " + elemento.peso + ", Beneficio: " + elemento.beneficio);
        }

        int beneficioTotal = calcularBeneficio(mejorCombinacion);
        System.out.println("Mejor beneficio total: " + beneficioTotal);
    }

    public static List<Elemento> obtenerMejorCombinacion(Elemento[] elementos, int capacidadMochila) {
        List<Elemento> combinacionActual = new ArrayList<>();
        List<Elemento> mejorCombinacion = new ArrayList<>();

        obtenerMejorCombinacionRecursivo(elementos, capacidadMochila, 0, combinacionActual, mejorCombinacion);

        return mejorCombinacion;
    }

    private static void obtenerMejorCombinacionRecursivo(Elemento[] elementos, int capacidadMochila, int indice,
                                                         List<Elemento> combinacionActual, List<Elemento> mejorCombinacion) {
        if (indice == elementos.length) {
            // Verificar si la combinación actual es mejor que la mejor conocida
            if (calcularBeneficio(combinacionActual) > calcularBeneficio(mejorCombinacion)) {
                mejorCombinacion.clear();
                mejorCombinacion.addAll(combinacionActual);
            }
            return;
        }

        // No incluir el elemento en la mochila
        obtenerMejorCombinacionRecursivo(elementos, capacidadMochila, indice + 1, combinacionActual, mejorCombinacion);

        // Incluir el elemento en la mochila si es posible
        if (elementos[indice].peso <= capacidadMochila) {
            combinacionActual.add(elementos[indice]);
            obtenerMejorCombinacionRecursivo(elementos, capacidadMochila - elementos[indice].peso, indice + 1,
                    combinacionActual, mejorCombinacion);
            combinacionActual.remove(combinacionActual.size() - 1);  // Retroceder
        }
    }

    private static int calcularBeneficio(List<Elemento> combinacion) {
        int beneficio = 0;
        for (Elemento elemento : combinacion) {
            beneficio += elemento.beneficio;
        }
        return beneficio;
    }

    static class Elemento {
        int peso;
        int beneficio;

        public Elemento(int peso, int beneficio) {
            this.peso = peso;
            this.beneficio = beneficio;
        }
    }
}
