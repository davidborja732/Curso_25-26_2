package Ej_3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ej_3_Prueba {
    public static void main(String[] args) {
        ArrayList<Persona> personas=new ArrayList<>(Arrays.asList(
                new Persona("Juan",14),
                new Persona("Pepe",34),
                new Persona("Luis",18),
                new Persona("Antonio",90),
                new Persona("Luisa",23),
                new Persona("Jose",31),
                new Persona("Mario",12)
            )
        );
        List<String> listaEdades30=personas.stream().filter(persona -> persona.getEdad()>30).map(Persona::getNombre).toList();
        System.out.println("La edad media es ");
        personas.stream().mapToInt(Persona::getEdad).average().ifPresent(System.out::println);
        System.out.println("La persona con menos edad es "+personas.stream().min((o1, o2) -> Integer.compare(o1.edad,o2.edad)).toString());
        System.out.println("Las personas mayores de 30 años son \n"+listaEdades30);
    }
}
