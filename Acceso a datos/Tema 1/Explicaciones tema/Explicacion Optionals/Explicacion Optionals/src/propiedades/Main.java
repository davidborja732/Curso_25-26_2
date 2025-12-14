package propiedades;

import java.io.*;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        try {
            FileReader reader=new FileReader("config.properties");
            Properties properties=new Properties();
            properties.load(reader);
            String conexion=(properties.get("ip")+":"+properties.get("puerto"));
            System.out.println(conexion);
            properties.setProperty("nota","notable");
            properties.store(new BufferedWriter(new FileWriter("config.properties")),"");
            String nota= properties.get("nota").toString();
            System.out.println(nota);
            System.out.println();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
