import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.Gson;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {
    static void main() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        ZipInputStream zis = null;
        try {
            zis = new ZipInputStream(new FileInputStream("Archivos/items.zip"));
            ZipEntry entrada;
            while ((entrada = zis.getNextEntry()) != null) {
                System.out.println("Leyendo archivo: " + entrada.getName());

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(zis));
                String linea;
                while ((linea = bufferedReader.readLine()) != null) {
                    Temperatura temperatura=mapper.readValue(linea.replaceAll("(?<=\\d),", "."),Temperatura.class);
                    System.out.println(temperatura.toString());
                }
                System.out.println("--------------------------------------------------------------------");
                zis.closeEntry();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
