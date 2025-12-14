package Ej_5;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class EScritura_Objeto {
    public static void main(String[] args) {
        ArrayList<Producto> productos = new ArrayList<>(List.of(
                new Producto(1, "Teclado", 29.99, true, 'A'),
                new Producto(2, "Ratón", 19.50, false, 'B'),
                new Producto(3, "Monitor", 159.00, true, 'A'),
                new Producto(4, "USB", 8.75, false, 'C'),
                new Producto(5, "Altavoces", 45.20, true, 'B')
        ));

        try (RandomAccessFile archivo = new RandomAccessFile("Aleatorio_fichero.txt", "rw")) {
            archivo.seek(archivo.length()); // Ir al final del archivo

            for (Producto p : productos) {
                archivo.writeUTF(p.toString()+"\n"); // Escribir cada producto
            }
        } catch (IOException e) {
            System.err.println("Error al escribir: " + e.getMessage());
        }

        //Lectura
        try (RandomAccessFile archivo = new RandomAccessFile("Aleatorio_fichero.txt", "rw")) {
            archivo.seek(0);
            String linea;
            while (archivo.getFilePointer()< archivo.length()){
                linea=archivo.readUTF();
                System.out.println(linea);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // mostrar 4º producto
        try (RandomAccessFile archivo = new RandomAccessFile("Aleatorio_fichero.txt", "rw")) {
            archivo.seek(0);
            int contador=0;
            String linea;
            while (archivo.getFilePointer()< archivo.length()){
                linea=archivo.readUTF();
                contador++;
                if (contador==4){
                    System.out.println("4º Producto");
                    System.out.println(linea);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

