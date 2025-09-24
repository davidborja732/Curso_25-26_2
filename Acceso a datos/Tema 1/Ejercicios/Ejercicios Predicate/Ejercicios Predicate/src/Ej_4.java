import java.util.function.Supplier;

/*Generar una secuencia de números aleatorios: Utiliza un “Supplier” para generar e
imprimir una secuencia de diez números aleatorios.*/
public class Ej_4 {
    static void main() {
        int contador=0;
        while (contador<10){
            Supplier<Integer> aleatorio = () -> (int) (Math.random()*10);
            System.out.println(aleatorio.get());
            contador+=1;
        }
    }
}
