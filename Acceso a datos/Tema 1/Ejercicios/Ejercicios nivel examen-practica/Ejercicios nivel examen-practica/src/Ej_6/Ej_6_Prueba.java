package Ej_6;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Ej_6_Prueba {
    static void main(String[] args) {
        List<Persona> personas = new ArrayList<>();

        personas.add(new Persona("11111111A", "Ana", new ArrayList<>(List.of(
                new Coche("Opel", "Rojo", "AAA111"),
                new Coche("Ford", "Azul", "AAA112")
        ))));

        personas.add(new Persona("22222222B", "Luis", new ArrayList<>(List.of(
                new Coche("Seat", "Rojo", "BBB111"),
                new Coche("BMW", "Negro", "BBB112"),
                new Coche("Toyota", "Rojo", "BBB113")
        ))));

        personas.add(new Persona("33333333C", "Marta", new ArrayList<>(List.of(
                new Coche("Renault", "Blanco", "CCC111")
        ))));

        personas.add(new Persona("44444444D", "Carlos", new ArrayList<>(List.of(
                new Coche("Opel", "Verde", "DDD111"),
                new Coche("Audi", "Rojo", "DDD112"),
                new Coche("Citroen", "Gris", "DDD113"),
                new Coche("Mazda", "Rojo", "DDD114")
        ))));

        personas.add(new Persona("55555555E", "Lucía", new ArrayList<>(List.of(
                new Coche("Peugeot", "Negro", "EEE111"),
                new Coche("Hyundai", "Rojo", "EEE112")
        ))));

        personas.add(new Persona("66666666F", "Javier", new ArrayList<>(List.of(
                new Coche("Kia", "Azul", "FFF111"),
                new Coche("Fiat", "Blanco", "FFF112"),
                new Coche("Honda", "Rojo", "FFF113"),
                new Coche("Tesla", "Negro", "FFF114"),
                new Coche("Skoda", "Gris", "FFF115")
        ))));

        personas.add(new Persona("77777777G", "Sofía", new ArrayList<>(List.of(
                new Coche("Volkswagen", "Verde", "GGG111")
        ))));

        personas.add(new Persona("88888888H", "Pedro", new ArrayList<>(List.of(
                new Coche("Nissan", "Rojo", "HHH111"),
                new Coche("Opel", "Negro", "HHH112")
        ))));

        System.out.println(1);
        personas.stream().filter(persona -> persona.getCoches().stream().anyMatch(coche -> coche.color.equals("Rojo"))).forEach(System.out::println);
        System.out.println(2);
        personas.stream().filter(persona -> persona.getCoches().stream().anyMatch(coche -> coche.marca.equals("Opel"))).forEach(System.out::println);
        System.out.println(3);
        personas.stream().max(Comparator.comparingInt(p -> p.getCoches().size())).ifPresent(
                persona -> System.out.println("La persona con mas coches es "+persona.nombre+" con el dni "+persona.dni+" que tiene "+persona.getCoches().size()+" coches"));
    }
}
