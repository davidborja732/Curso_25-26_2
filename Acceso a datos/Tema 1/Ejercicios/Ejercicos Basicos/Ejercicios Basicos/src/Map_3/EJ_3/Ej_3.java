package Map_3.EJ_3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ej_3 {
    public static Map<String,Estudiante> añadir(Estudiante estudiante){
        Map<String,Estudiante> hashmap=new HashMap<>();
        hashmap.put(estudiante.getNombre(),estudiante);
        return hashmap;
    }
    public static void main(String[] args) {
        List<Estudiante> estudiantes=new ArrayList<>();
        estudiantes.add(new Estudiante("Lucía", "Martínez", "1º Bachillerato", "17"));
        estudiantes.add(new Estudiante("Carlos", "Gómez", "2º ESO", "14"));
        estudiantes.add(new Estudiante("Ana", "Ruiz", "4º ESO", "16"));
        estudiantes.add(new Estudiante("David", "Fernández", "1º Grado Superior", "19"));
        estudiantes.add(new Estudiante("Sofía", "López", "3º ESO", "15"));
        for (Estudiante estudiante : estudiantes){
            System.out.println(añadir(estudiante));
        }
    }
}
