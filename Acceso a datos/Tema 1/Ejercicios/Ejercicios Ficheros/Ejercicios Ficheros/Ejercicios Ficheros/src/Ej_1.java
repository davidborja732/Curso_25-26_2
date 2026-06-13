import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;

public class Ej_1 {
    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("Archivos Ejercicios/Ej_1.txt"))) {
            int contador=0;
            while ((bufferedReader.readLine()) != null) {
                contador+=1;
            }
            System.out.println(contador);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
