import java.io.File;

public class Ej_6 {
    public static void main(String[] args) {
        File[] files=new File("Archivos Ejercicios").listFiles();
        if (files != null) {
            for (File archivo:files){
                if (archivo.toString().endsWith(".bat")){
                    System.out.println(archivo.toPath());
                }
            }
        }
    }
}
