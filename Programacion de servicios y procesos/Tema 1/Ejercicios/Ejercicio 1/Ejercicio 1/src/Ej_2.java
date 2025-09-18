import java.io.*;

public class Ej_2 {
    public static void main(String[] args) {
        File archivoBat = new File("Documento/archivo.bat");

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(archivoBat))) {
            String linea;

            while ((linea = bufferedReader.readLine()) != null) {
                File log = new File("Documento/log.txt");

                ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", linea);

                // Escribo el resultado del CMD en el log
                processBuilder.redirectOutput(ProcessBuilder.Redirect.appendTo(log));
                processBuilder.redirectError(ProcessBuilder.Redirect.appendTo(log));

                Process proceso = processBuilder.start();

                // Espero que finalize el proceso hasta poder pasar al siguiente
                proceso.waitFor();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
