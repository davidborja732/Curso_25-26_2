package Ejercicio_1_y_2;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class prueba_fruta {
    static void main() {
        List<Fruta> fruteria = List.of(
                new Fruta("Manzana", "Rojo", "España"),
                new Fruta("Plátano", "Amarillo", "Ecuador"),
                new Fruta("Fresa", "Rojo", "Huelva"),
                new Fruta("Kiwi", "Verde", "Nueva Zelanda"),
                new Fruta("Uva", "Morado", "Italia"),
                new Fruta("Limón", "Amarillo", "México"),
                new Fruta("Naranja", "Naranja", "Valencia"),
                new Fruta("Pera", "Verde", "Argentina"),
                new Fruta("Cereza", "Rojo", "Chile"),
                new Fruta("Papaya", "Naranja", "Brasil")
        );

        List<String> nombresfruta=fruteria.stream().map(fruta -> fruta.getNombre()).toList();
        nombresfruta.forEach(System.out::println);
        // Ejercicio 2
        Set<String> coloresfruta=fruteria.stream().map(fruta -> fruta.getColor()).collect(Collectors.toSet());
        coloresfruta.forEach(System.out::println);

    }
}
