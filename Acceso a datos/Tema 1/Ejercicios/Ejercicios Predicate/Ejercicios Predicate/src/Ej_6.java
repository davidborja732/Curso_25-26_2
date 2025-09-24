import java.util.List;
import java.util.function.Consumer;

/*Aplicar una operación a cada elemento de una lista: Crea una lista de números
enteros. Utiliza un “Consumer” para aplicar una operación a cada número en la
lista (por ejemplo, multiplicar cada número por 2).*/
public class Ej_6 {
    static void main() {
        List<Integer> lista=List.of(1,3,5,6,89,90,76,45,32,12,45,67,89);
        Consumer<Integer> suma=integer -> System.out.println(integer+10);
        lista.forEach(suma);
        //lista.forEach(suma::accept);
    }
}
