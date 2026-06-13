package Ej_8;

import javax.xml.transform.Source;
import java.util.*;
import java.util.stream.Collectors;

public class Ej_8_Prueba {
    static void main() {
        List<Cancion> canciones = new ArrayList<>(Arrays.asList(
            new Cancion("Livin' on Prayer", "Bon Jovi"),
                new Cancion("Long Hot Summer", "Keith Urban"),
                new Cancion("It's my Life", "Bon Jovi"),
                new Cancion("Dolor Fantasma", "Amadeus"),
                new Cancion("Run To You", "Bryan Adams"),
                new Cancion("Summer of 69", "Bryan Adams"),
                new Cancion("Paranoid", "Black Sabbath"),
                new Cancion("Cherokee", "Europe"),
                new Cancion("River Bank", "Brad Paisley")
        ));
        // Tradicional
        System.out.println("Metodo Tradicional");
        for (Cancion cancion:canciones){
            if (cancion.cantante.equals("Bon Jovi")){
                System.out.println(cancion.toString());
            }
        }
        // Programacion Funcional
        System.out.println("Programacion Funcional");
        canciones.stream().filter(cancion -> cancion.cantante.equals("Bon Jovi")).forEach(System.out::println);
        // Programacion Funcional en lista
        System.out.println("Lista de canciones Bon Jovi");
        List<Cancion> bonjovi=canciones.stream().filter(cancion -> cancion.cantante.equals("Bon Jovi")).toList();
        System.out.println(bonjovi);
        // Conteo canciones
        System.out.println("Conteo de canciones Bon Jovi");
        System.out.println("Bon Jovi tiene "+canciones.stream().filter(cancion -> cancion.cantante.equals("Bon Jovi")).count()+" cancion(es)");
        // Canciones por cantante
        System.out.println("Numero de canciones por cantante");
        canciones.stream().collect(Collectors.groupingBy(cancion -> cancion.cantante,Collectors.counting())).forEach((s, aLong) ->
                System.out.println("El cantante "+s+" tiene "+ aLong+" cancion(es)"));
        // Canciones por cantante sin duplicados
        canciones.add(new Cancion("Summer of 69", "Bryan Adams"));
        System.out.println("Canciones sin duplicado");
        Map<String,String> listasinduplicados=canciones.stream().collect(Collectors.toMap(Cancion::getTitulo,Cancion::getCantante,(cancionoriginal, cancionduplicada) -> cancionoriginal));
        listasinduplicados.forEach((s, s2) -> System.out.println(s+" "+s2));
    }
}
