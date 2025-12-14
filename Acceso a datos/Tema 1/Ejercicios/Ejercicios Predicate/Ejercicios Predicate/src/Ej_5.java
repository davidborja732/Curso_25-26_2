import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/*Filtrar nombres que comienzan con una letra específica: Crea una lista de
nombres. Utiliza un “Predicate” para filtrar la lista y mantener solo los nombres
que comienzan con una letra específica (por ejemplo, "A").*/
public class Ej_5 {
    static void main() {
        List<String> lista= new java.util.ArrayList<>(List.of("Juan", "Andres", "Ana", "Adrian", "Carlos", "Lucia"));
        Predicate<String> nombresA=s -> s.startsWith("a");
        System.out.println(lista);
        lista.removeIf(s -> !nombresA.test(s.toLowerCase()));
        System.out.println(lista);

    }
}
