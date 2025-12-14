package Set_2;

import java.awt.*;
import java.util.HashSet;
import java.util.List;

public class Ej_1 {
    public static void main(String[] args) {
        HashSet<String> hashSet= new HashSet<>();
        List<String> lista=List.of("Hola","Adios","Buenas","Hasta luego","Que tal");
        hashSet.addAll(lista);
        hashSet.forEach(System.out::println);
    }
}
