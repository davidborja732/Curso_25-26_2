package List_1;

import java.util.List;

public class Ej_1 {
    public static void main(String[] args) {
        List<String> lista=List.of("Hola","Adios","Buenas","Hasta luego","Que tal");
        lista.forEach(System.out::println);
    }
}
