package Ej_4;

import Ej_3.Persona;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Lectura {
    public static void main(String[] args) {
        int contador=0;
        try (BufferedReader bufferedReader=new BufferedReader(new FileReader("Ficheros Ejercicios/Ej4.csv"))){
            String linea;
            Producto producto=null;
            List<Producto> productos=new ArrayList<>();
            String[] listaproductos;
            while ((linea=bufferedReader.readLine())!=null){
                contador++;
                if (contador>1){
                    if(linea.contains(",")){
                        listaproductos= linea.replaceAll(",",".").split(";");
                        producto=new Producto(Double.valueOf(listaproductos[1]),listaproductos[2],listaproductos[0]);
                        productos.add(producto);
                    }else {
                        listaproductos= linea.split(";");
                        producto=new Producto(Double.valueOf(listaproductos[1]),listaproductos[2],listaproductos[0]);
                        productos.add(producto);
                    }
                }
            }
            // Collectors.maxBy(Comparator.comparing(Producto::getPrecio)) otra forma de hacerlo
            System.out.println("El producto mas caro de cada categoria es ");
            productos.stream().collect(Collectors.groupingBy(Producto::getCategoria)).forEach((s, productos1) -> System.out.println(s+" el producto mas caro es "+productos1.stream().max((o1, o2) -> o1.getPrecio().compareTo(o2.getPrecio())).toString()));
            System.out.println("Los productos con un precio entre 10 y 20 euros son =");
            productos.stream().filter(producto1 -> producto1.getPrecio()>=10 && producto1.getPrecio()<=20).forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
