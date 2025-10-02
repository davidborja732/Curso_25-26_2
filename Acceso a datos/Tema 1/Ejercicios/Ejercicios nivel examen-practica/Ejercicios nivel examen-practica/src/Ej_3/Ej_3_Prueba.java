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
        int menor;
        List<Persona> listaedades=personas.stream().sorted((o1, o2) -> Integer.compare(o1.getEdad(),o2.getEdad())).toList();
        List<Persona> listaedades30=personas.stream().filter(persona -> persona.getEdad()>30).toList();
        int edad_promedio=0;
        for (Persona persona:personas){
            edad_promedio+= persona.getEdad();
        }
        System.out.println("La edad media es "+edad_promedio/ personas.size());
        System.out.println("La persona con menos edad es "+listaedades.getFirst());
        System.out.println("Las personas mayores de 30 años son \n"+listaedades30);
    }
}
