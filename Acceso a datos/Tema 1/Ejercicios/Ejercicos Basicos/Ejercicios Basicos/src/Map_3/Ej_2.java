package Map_3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ej_2 {
    public static void main(String[] args) {
        List<String> lista=List.of("Hola","Adios","Buenas","Hasta luego","Que tal","Adios","Buenas","Hola");
        Map<String,Integer> hashmap=new HashMap<>();
        for (int i = 0; i < lista.size(); i++) {
            hashmap.put(lista.get(i),hashmap.getOrDefault(lista.get(i),0)+1);
        }
        hashmap.forEach((integer, s) -> System.out.println(integer+" "+s));
    }
}
