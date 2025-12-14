import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class Ej_2 {
    public static void main(String[] args) {
        try {
            Files.copy(new File("Archivos Ejercicios/EJ_origen.txt").toPath(), new File("Archivos Ejercicios/EJ_destino.txt").toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
