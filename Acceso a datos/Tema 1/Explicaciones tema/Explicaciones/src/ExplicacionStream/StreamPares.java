package ExplicacionStream;

import java.util.Arrays;
import java.util.List;

public class StreamPares {
    public static void main(String[] args) {
        List<Integer> lista = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        //Mete los numeros pares a la lista
        /*List<Integer> pares= lista.stream().filter(integer ->  integer%2==0 ).toList();
        pares.forEach(System.out::println);*/

        // Muestra los numeros pares que contiene la lista multiplicados por si mismo
        lista.stream().filter(integer -> integer % 2 == 0).map(n -> n * n).forEach(System.out::println);
    }
}
