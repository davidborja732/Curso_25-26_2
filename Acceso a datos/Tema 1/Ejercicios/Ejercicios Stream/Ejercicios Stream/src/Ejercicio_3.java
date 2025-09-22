import java.util.List;

public class Ejercicio_3 {
    static void main() {
        List<Integer> lista=List.of(1,4,3,8,9,10,12,20);
        int sumap=lista.stream().map(integer -> integer*integer).reduce(0,Integer::sum);
        System.out.println(sumap);
    }
}
