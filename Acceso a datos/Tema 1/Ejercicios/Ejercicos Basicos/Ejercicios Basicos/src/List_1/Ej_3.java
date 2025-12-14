package List_1;

import java.util.List;

public class Ej_3 {
    public static void main(String[] args) {
        List<String> lista=List.of("Hola","Adios","Buenas","Hasta luego","Que tal");
        int listalarga=lista.stream().mapToInt(String::length).max().orElse(0);
        System.out.println(listalarga);
    }
}
