import java.io.*;

public class Ej_2_IA {
    public static void main(String[] args) {
        File archivoBat = new File("Documento/archivo.bat");

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(archivoBat))) {
            String linea;

            while ((linea = bufferedReader.readLine()) != null) {
                // Creo archivo de log
                File log = new File("Documento/log.txt");

                // Creo ProcessBuilder
                ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", linea);

                // Redirigo salida estándar y errores al log
                processBuilder.redirectOutput(ProcessBuilder.Redirect.appendTo(log));
                processBuilder.redirectError(ProcessBuilder.Redirect.appendTo(log));

                // Inicio proceso
                Process proceso = processBuilder.start();

                // Espero que finalize el proceso hasta pasar al siguiente
                proceso.waitFor();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

