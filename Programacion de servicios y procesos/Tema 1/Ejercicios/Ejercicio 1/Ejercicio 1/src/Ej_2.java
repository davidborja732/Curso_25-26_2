import java.io.*;

public class Ej_2 {
    public static void main(String[] args) {
        File file=new File("Documento/archivo.bat");
        try {
            BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
            String linea="";
            while ((linea= bufferedReader.readLine())!=null){
                ProcessBuilder processBuilder=new ProcessBuilder("cmd.exe","/c",linea);
                processBuilder.redirectOutput(new File("Documento/log.txt"));
                processBuilder.start();
                //Process p= processBuilder.start();
                /*BufferedReader reader=new BufferedReader(new InputStreamReader(processBuilder.start().getInputStream()));
                String lineacmd="";
                while ((lineacmd= reader.readLine())!=null){
                    System.out.println(lineacmd);
                }*/
                //p.waitFor();
                //System.out.println("Siguiente");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } /*catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/
    }
}
