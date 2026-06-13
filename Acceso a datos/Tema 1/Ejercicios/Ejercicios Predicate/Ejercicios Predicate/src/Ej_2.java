import java.util.List;
import java.util.function.Consumer;

/*Imprimir todos los elementos de una lista: Crea una lista de cadenas. Utiliza un
“Consumer” para imprimir cada cadena en la lista.*/
public class Ej_2 {
    static void main() {
        List<String> lista=List.of("Juan","Andres","Ana","Adrian","Carlos","Lucia");
        Consumer<String> imprimir= System.out::println;
        lista.forEach(imprimir);
        //lista.forEach(imprimir::accept);
    }
}
