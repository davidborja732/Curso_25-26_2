package Ej_1;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;

public class Ej_1_prueba {
    static void main() {
        ArrayList<Producto> productos = new ArrayList<>(Arrays.asList(
                new Producto(1, "Camiseta", 19.99, true, 'R'),
                new Producto(2, "Portátil", 799.99, false, 'E'),
                new Producto(3, "Libro", 12.50, true, 'C'),
                new Producto(4, "Auriculares", 49.90, false, 'E'),
                new Producto(5, "Zapatos", 89.00, true, 'R'),
                new Producto(6, "Mochila", 35.75, false, 'A')
        ));
        // Escritura en archivo aleatorio
        try (RandomAccessFile archivo = new RandomAccessFile("Aleatorio_fichero.txt", "rw")) {
            // Posicionarse al final del archivo para no sobrescribir contenido anterior
            archivo.seek(archivo.length());
            // Recorrer la lista de productos
            for (Producto p : productos) {
                // Escribir cada producto como cadena usando writeUTF, seguido de salto de línea
                archivo.writeUTF(p.toString() + "\n");
            }
        } catch (IOException e) {
            // Captura y muestra cualquier error de escritura
            System.err.println("Error al escribir: " + e.getMessage());
        }
        // Lectura desde el archivo aleatorio
        try (RandomAccessFile archivo = new RandomAccessFile("Aleatorio_fichero.txt", "rw")) {
            // Posicionarse al inicio del archivo
            archivo.seek(0);
            String linea;
            // Leer mientras no se llegue al final del archivo
            while (archivo.getFilePointer() < archivo.length()) {
                // Leer cada línea codificada con readUTF
                linea = archivo.readUTF();
                // Mostrar la línea por consola
                System.out.println(linea);
            }
        } catch (IOException e) {
            // Captura y relanza cualquier error de lectura como RuntimeException
            throw new RuntimeException(e);
        }
    }
}
