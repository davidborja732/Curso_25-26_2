import java.io.*;

public class Ej_2 {
    static void main() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("Ficheros Ejercicios/fichero original.txt"));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("Ficheros Ejercicios/fichero con reemplazos.txt"))
        ) {
            String linea;
            while ((linea= bufferedReader.readLine())!=null){
                if (linea.contains("juega")){
                    System.out.println(linea);
                    bufferedWriter.write(linea.replaceAll("juega","SUSTITUIDO")+"\n");
                }else {
                    bufferedWriter.write(linea+"\n");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
