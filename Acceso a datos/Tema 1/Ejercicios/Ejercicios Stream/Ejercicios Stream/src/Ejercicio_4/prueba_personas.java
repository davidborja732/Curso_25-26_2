package Ejercicio_4;

import java.util.List;

public class prueba_personas {
    static void main() {
        List<Persona> personas = List.of(
                new Persona("Ana", 25),
                new Persona("Luis", 30),
                new Persona("María", 22),
                new Persona("Carlos", 28),
                new Persona("Elena", 35),
                new Persona("Jorge", 40),
                new Persona("Lucía", 19),
                new Persona("Pedro", 33),
                new Persona("Sofía", 27),
                new Persona("Raúl", 24)
        );
        personas.stream().sorted((o1, o2) -> Integer.compare(o1.edad,o2.edad)).forEach(System.out::println);
    }
}
