import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/*Filtrar números impares: Crea una lista de números enteros. Utiliza un “Predicate”
para filtrar la lista y eliminar todos los números impares.*/
public class Ej_1 {
    static void main() {
        List<Integer> lista= new ArrayList<>(List.of(1, 3, 5, 6, 89, 90, 76, 45, 32, 12, 45, 67, 89));
        Predicate<Integer> pares=integer ->integer%2!=0;
        System.out.println(lista);
        lista.removeIf(pares);
        //lista.removeIf(integer -> !pares.test(integer));
        System.out.println(lista);

    }
}
