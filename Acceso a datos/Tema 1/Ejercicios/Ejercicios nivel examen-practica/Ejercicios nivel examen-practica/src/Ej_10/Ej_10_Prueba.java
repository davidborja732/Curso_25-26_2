package Ej_10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Ej_10_Prueba {
    public static void main(String[] args) {
        List<Producto> productos=new ArrayList<>();
        Producto productolista=null;
        int contador=-1;
        try(BufferedReader bufferedReader=new BufferedReader(new FileReader("Ficheros Ejercicios/products.csv"))){
            String linea;
            while ((linea= bufferedReader.readLine())!=null){
                contador++;
                if (contador>0){
                    String[] lista=linea.split(",");
                    productolista=new Producto(Integer.parseInt(lista[0]),lista[1],Integer.parseInt(lista[2]),Integer.parseInt(lista[3]),lista[4],Double.valueOf(lista[5]),Integer.parseInt(lista[6]),Integer.parseInt(lista[7]),Integer.parseInt(lista[8]),Integer.parseInt(lista[9]));
                    productos.add(productolista);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(1);
        productos.forEach(System.out::println);
        System.out.println(2);
        productos.forEach(producto -> System.out.println(producto.productName));
        System.out.println(3);
        productos.stream().filter(producto -> producto.unitsInStock<10).forEach(System.out::println);
        System.out.println(4);
        productos.stream().filter(producto -> producto.unitsInStock<10).sorted((o1, o2) -> Integer.compare(o1.unitsInStock,o2.unitsInStock)).forEach(System.out::println);
        System.out.println(5);
        productos.stream().filter(producto -> producto.unitsInStock<10).sorted((o1, o2) -> Integer.compare(o2.unitsInStock,o1.unitsInStock)).forEach(System.out::println);
        System.out.println(6);
        productos.stream().filter(producto -> producto.unitsInStock>10).sorted((o1, o2) -> Integer.compare(o1.unitsInStock,o2.unitsInStock)).sorted(
                (o1, o2) -> o1.productName.compareTo(o2.productName)).forEach(
                        producto -> System.out.println(producto.productName));
        System.out.println(7);
        productos.stream().collect(Collectors.groupingBy(producto -> producto.supplierID,Collectors.counting())).forEach(
                (integer, aLong) -> System.out.println("El proveedor "+integer+" tiene "+aLong+" producto(s)"));
        System.out.println(8);
        Map<Object, Double> sumacondicion=productos.stream().collect(Collectors.groupingBy(o -> o.unitsInStock,Collectors.summingDouble(Producto::getUnitPrice)));
        sumacondicion.forEach((o, aDouble) -> {
            if (aDouble > 100) {
                System.out.println(o+" "+aDouble);
            }
        });
        System.out.println(9);
        double suma= productos.stream().mapToDouble(Producto::getUnitsInStock).sum();
        System.out.println("El promedio de existencias en el almacen es "+suma/productos.size());
        System.out.println(10);
        productos.stream().max((o1, o2) -> Double.compare(o1.unitPrice,o2.unitPrice)).ifPresent(System.out::println);
        System.out.println(11);
        productos.stream().limit(50).forEach(System.out::println);
        System.out.println(12);
        //Imprimir sin mostrar los 10 primeros
        productos.stream().skip(10).forEach(System.out::println);
    }
}
