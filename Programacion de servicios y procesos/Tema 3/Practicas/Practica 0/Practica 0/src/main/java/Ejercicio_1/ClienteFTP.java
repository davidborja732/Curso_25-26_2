package Ejercicio_1;

import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPSClient;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.cert.X509Certificate;
import java.util.Scanner;
public class ClienteFTP {
    public static void main(String[] args) {
        FTPSClient ftp = new FTPSClient("TLS", false);
        try {
            // Aceptamos certificado autofirmado (NO seguro en producción)
            ftp.setTrustManager(new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] xcs,
                                               String string) {}
                public void checkServerTrusted(X509Certificate[] xcs,
                                               String string) {}
                public X509Certificate[] getAcceptedIssuers() { return
                        null; }
            });
            // Conectamos
            ftp.connect("192.168.50.244");
            if (!FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
                ftp.disconnect();
                System.out.println("No se pudo conectar al servidor");
                return;
            }
            // Login
            ftp.login("smr1", "VonNeumann1945");
            ftp.execPBSZ(0);
            ftp.execPROT("P"); // protección de datos
            ftp.enterLocalPassiveMode();
            // Listar directorio raíz
            listDirectory(ftp);
            Scanner sc = new Scanner(System.in);
            // Cambiar de directorio
            System.out.println("¿A qué directorio quieres moverte?");
            String path = sc.nextLine().trim();
            if (!path.isEmpty()) {
                ftp.changeWorkingDirectory(path);
                listDirectory(ftp);
            }
            // Descargar fichero
            System.out.println("¿Qué fichero quieres descargar?");
            String file = sc.nextLine().trim();
            if (!file.isEmpty()) {
                try (FileOutputStream fos =
                             new
                                     FileOutputStream("C:/Users/dam2/Downloads" + file)) {
                    ftp.retrieveFile(file, fos);
                    System.out.println("Hecho");
                }
            }
            ftp.logout();
            ftp.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Mostrar contenido del directorio
    private static void listDirectory(FTPSClient ftp) throws IOException {
        FTPFile[] files = ftp.listFiles();
        for (FTPFile file : files) {
            System.out.println(file.getName());
        }
    }
}

