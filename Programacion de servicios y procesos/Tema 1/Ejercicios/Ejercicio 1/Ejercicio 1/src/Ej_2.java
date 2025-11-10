import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Ej_2 {
    public static void main(String[] args) {
        File archivoBat = new File("Documento/archivo.bat");
        File log = new File("Documento/log.txt");
        File log2 = new File("Documento/log2.txt");

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(archivoBat))) {
            String linea;

            while ((linea = bufferedReader.readLine()) != null) {

                ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", linea);
                // Escribo el resultado del CMD en el log
                processBuilder.redirectOutput(ProcessBuilder.Redirect.appendTo(log));
                processBuilder.redirectError(ProcessBuilder.Redirect.appendTo(log2));
                Process proceso = processBuilder.start();
                // Espero que finalize el proceso hasta poder pasar al siguiente
                proceso.waitFor();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
