package org.example.pr3.backtracking;


public class Laberinto {

    public char[][] laberinto = {
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
            {'#', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', ' ', '#', ' ', '#', '#', '#', '#', '#', '#'},
            {'#', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', '#'},
            {'#', '#', '#', ' ', ' ', ' ', '#', '#', '#', '#'},
            {'#', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', '#'},
            {'#', '#', '#', '#', '#', ' ', '#', '#', '#', '#'},
            {'#', ' ', ' ', ' ', '#', ' ', '#', ' ', '#', '#'},
            {'#', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
    };

    /* --------------------- PRUEBA DEL ALGORITMO --------------------- */
    public static void main(String[] args) {
        Laberinto m = new Laberinto();                                                // construimos un objeto de la clase Laberinto por defecto
        m.laberinto[8][1] = 'X';                                                    // introducimos en este caso, la salida (X) en las coordenadas (1,1)
        m.resuelve(1, 1);// ahora, introducimos la entrada (S) en las coordenadas (8,1) y llamamos al algoritmo;
        System.out.println(m.imprimirLaberinto(0, 0));  ;// imprimimos el laberinto ya resuelto (si tiene solución)
    }


    /* ----------------- IMPLEMENTACIÓN DEL ALGORITMO ----------------- */
    public void resuelve(int x, int y) {                // permite introducir unas coordenadas (x, y)
        if (paso(x, y)) {                                // intentará resolver el laberinto en estas coordenadas
            laberinto[x][y] = 'S';                        // introduce en las coordenadas (x, y) la entrada
        }
    }

    private boolean paso(int x, int y) {
        if (x < 0 || x > laberinto.length - 1 || y < 0 || y > laberinto[x].length - 1) { //Validaciones para que no se salga de la matriz - El último pedazo es para validar que no será un rango negativo por lo primero
            return false;
        }


        if (laberinto[x][y] == 'X') {
            System.out.println(imprimirLaberinto(0, 0));
            return false; // luego, el algoritmo termina
        }


        if (laberinto[x][y] == '#' || laberinto[x][y] == '*' || laberinto[x][y] == 'f' || laberinto[x][y] < 0) { // si llegamos a una pared o al mismo punto o se sale de los limites//
            return false; // entonces el laberinto no puede resolverse y termina.
        }

        // si no se cumple ninguna de estas dos situaciones, quiere decir que nos encontramos en un
        // caso intermedio, por lo tanto, que empezamos a recorrer o todavía no hemos llegado a nada
        int numRows = laberinto.length;
        int numCols = laberinto[0].length;

        laberinto[x][y] = '*'; // marcamos la posición como visitada (si es la primera, en las coordenadas x e y

        boolean result; // se coloca S de START)

        result = paso(x, y + 1); // intentamos ir hacia la DERECHA. Primera llamada recursiva
        if (result) return true; // si el resultado es el final, entonces el algoritmo termina

        result = paso(x - 1, y); // intentamos ir hacia ARRIBA. Segunda llamada recursiva
        if (result) return true; // si el resultado es el final, entonces el algoritmo termina

        result = paso(x, y - 1); // intentamos ir hacia la IZQUIERDA. Tercera llamada recursiva
        if (result) return true; // si el resultado es el final, entonces el algoritmo termina

        result = paso(x + 1, y); // intentamos ir hacia ABAJO. Cuarta llamada recursiva
        if (result) return true; // si el resultado es el final, entonces el algoritmo termina

        // si no hemos encontrado la solución en estos cuatros movimientos, volvemos atrás, aunque hay que
        // considerar que en este punto, todas las llamadas recursivas han finalizado. Si en ninguna de ellas
        // se ha conseguido el éxito, entonces el algoritmo termina y devuelve false.
        laberinto[x][y] = 'f'; // en el caso de no ser el resultado, se marca con +. Ya fue pisado
        return false; // vuelta atrás si la solución no se encuentra aquí


    }


    // Métodos para imprimir el laberinto
    private String imprimirLaberinto(int x, int y) {// imprimiremos nuestra solución. Debido a que la clase Arrays no tiene implementado

        String salida = "";

        if (x < laberinto.length) {
            if (y < laberinto[x].length) {

                salida += laberinto[x][y] + " ";
                return salida + imprimirLaberinto(x, y + 1);
            } else {

                salida += "\n";
                y = 0;
                return salida + imprimirLaberinto(x + 1, y);
            }
        } else {
            return salida;
        }
    }
}








