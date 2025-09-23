import java.util.List;
import java.util.function.Function;

/*Transformar una lista de números: Crea una lista de números enteros. Utiliza una
“Function” para transformar cada número en la lista a su cubo.*/
public class Ej_3 {
    static void main() {
        List<Integer> lista=List.of(1,3,5,6,89,90,76,45,32,12,45,67,89);
        Function<Integer,Integer> cubo=integer -> integer*integer*integer;
        lista.forEach(integer -> System.out.println(cubo.apply(integer)));

    }
}
