package List_1;

import java.util.List;

public class Ej_2 {
    public static void main(String[] args) {
        List<Integer> lista=List.of(1,4,5,6,8,9,10,11,23,25,24);
        List<Integer> listapares= lista.stream().filter(integer -> integer%2==0).toList();
        System.out.println(listapares);
    }
}
