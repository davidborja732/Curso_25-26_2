import java.io.*;

public class Ej_4 {
    public static void main(String[] args) {
        File file=new File("Archivos Ejercicios");
        File[] archivostxt=file.listFiles();
        assert archivostxt != null;
        for (File archivo :archivostxt){
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(archivo));
                 BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("Archivo_Salida.txt",true))
            ) {
                String linea;
                while ((linea= bufferedReader.readLine())!=null){
                    bufferedWriter.write(linea+"\n");
                }
                bufferedWriter.write("------------------------------"+"\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
