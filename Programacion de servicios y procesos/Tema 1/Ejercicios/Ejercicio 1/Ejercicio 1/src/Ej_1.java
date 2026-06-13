import java.io.File;
import java.io.IOException;

public class Ej_1 {
    public static void main(String[] args) {
        //String carpeta="C:\\Users\\david\\Documents";
        String carpeta = "C:\\Users\\dam2\\Documents";
        File carpetadocumento = new File(carpeta);
        if (carpetadocumento.isDirectory()) {
            File[] documento = carpetadocumento.listFiles();
            assert documento != null;
            for (File archivo : documento) {
                if (archivo.isFile() && archivo.getName().endsWith(".txt")) {
                    System.out.println(archivo.getPath());
                    ProcessBuilder proceso = new ProcessBuilder("C:\\Program Files\\Notepad++\\notepad++.exe", archivo.getPath());
                    try {
                        proceso.start();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}

