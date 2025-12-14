package Set_2;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Ej_3 {
    public static Set<String> palabrasunicas(List<String> lista){
        Set<String> hashSet=new HashSet<>();
        hashSet.addAll(lista);
        return hashSet;
    }
    public static void main(String[] args) {
        List<String> lista=List.of("Hola","Adios","Buenas","Hasta luego","Que tal","Adios","Buenas","Hola");
        System.out.println(palabrasunicas(lista));


    }
}
