package ExplicacionLambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Stringsporlongitud {
    public static void main(String[] args) {
        List<String> lista= Arrays.asList("perro","gato","Elefante","Conejo","Mariposa");
        Collections.sort(lista,(String string1,String string2)->string1.length()-string2.length());
        System.out.println(lista);
    }
}
