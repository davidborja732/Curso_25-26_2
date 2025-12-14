package Set_2;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Ej_2 {
    public static Set<Integer> numerosunicos(List<Integer> lista){
        Set<Integer> hashSet=new HashSet<>();
        hashSet.addAll(lista);
        return hashSet;
    }
    public static void main(String[] args) {
        List<Integer> lista=List.of(1,4,4,4,6,7,8,9,0,10,11,12,13,5,6,8,9,10,11,23,25,24);
        System.out.println(numerosunicos(lista));
    }
}
