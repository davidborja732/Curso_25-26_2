
package Ejercicio_1;

import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPSClient;

import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.security.cert.X509Certificate;
import java.util.Scanner;

public class ClienteFTP {

    public static void main(String[] args) {
        FTPSClient ftp = new FTPSClient("TLS", false);

        try (Scanner sc = new Scanner(System.in)) {

            ftp.setTrustManager(new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] xcs, String s) {}
                public void checkServerTrusted(X509Certificate[] xcs, String s) {}
                public X509Certificate[] getAcceptedIssuers() { return null; }
            });

            ftp.connect("eu-central-1.sftpcloud.io", 21);
            //ftp.connect("192.168.50.244", 21);
            if (!FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
                System.out.println("Error al conectar");
                return;
            }

            //ftp.login("smr1", "VonNeumann1945");
            ftp.login("f3a2949fad2048d6b27f873e42f59e11", "95PuTAZlKkWuFH6poZwZfglMw7eDktz8");

            ftp.execPBSZ(0);
            ftp.execPROT("P");
            ftp.enterLocalPassiveMode();

            while (true) {
                System.out.println("\n--- Menú FTPS ---");
                System.out.println("1) Listar archivos de una carpeta");
                System.out.println("2) Descargar archivo de una carpeta");
                System.out.println("3) Subir archivo a una carpeta");
                System.out.println("0) Salir");
                System.out.print("Opción: ");

                String op = sc.nextLine().trim();

                switch (op) {
                    case "1":
                        String carpetaListar = pedirCarpeta(sc, ftp, "Carpeta remota para LISTAR");
                        if (cambiarADirectorio(ftp, carpetaListar)) listar(ftp);
                        else System.out.println("No se pudo acceder a: " + carpetaListar);
                        break;

                    case "2":
                        String carpetaDescarga = pedirCarpeta(sc, ftp, "Carpeta remota para DESCARGAR");
                        if (cambiarADirectorio(ftp, carpetaDescarga)) {
                            listar(ftp);
                            System.out.print("Nombre del archivo remoto a descargar: ");
                            String nombre = sc.nextLine().trim();
                            if (!nombre.isEmpty()) descargar(ftp, nombre);
                            else System.out.println("Nombre vacío.");
                        } else System.out.println("No se pudo acceder a: " + carpetaDescarga);
                        break;

                    case "3":
                        System.out.print("Ruta LOCAL del archivo a subir: ");
                        String rutaLocal = sc.nextLine().trim();
                        File fLocal = new File(rutaLocal);
                        if (!fLocal.exists() || !fLocal.isFile()) {
                            System.out.println("El archivo local no existe.");
                            break;
                        }

                        System.out.print("Nombre remoto: ");
                        String nombreRemoto = sc.nextLine().trim();
                        if (nombreRemoto.isEmpty()) nombreRemoto = fLocal.getName();

                        String carpetaSubida = pedirCarpeta(sc, ftp, "Carpeta remota DESTINO para SUBIR");
                        if (cambiarADirectorio(ftp, carpetaSubida)) subir(ftp, rutaLocal, nombreRemoto);
                        else System.out.println("No se pudo acceder a: " + carpetaSubida);
                        break;

                    case "0":
                        ftp.logout();
                        ftp.disconnect();
                        System.out.println("Cerrado.");
                        return;

                    default:
                        System.out.println("Opción incorrecta.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            try { if (ftp.isConnected()) ftp.disconnect(); } catch (IOException ignored) {}
        }
    }

    private static String pedirCarpeta(Scanner sc, FTPSClient ftp, String titulo) throws IOException {
        System.out.println("\n== " + titulo + " ==");
        System.out.println("Directorio actual: " + ftp.printWorkingDirectory());
        System.out.println("Subdirectorios disponibles:");
        listarDirectorios(ftp);
        System.out.print("Carpeta: ");
        return sc.nextLine().trim();
    }

    private static boolean cambiarADirectorio(FTPSClient ftp, String dir) throws IOException {
        if (dir == null || dir.isEmpty()) return true;
        return ftp.changeWorkingDirectory(dir);
    }

    private static void listar(FTPSClient ftp) throws IOException {
        FTPFile[] files = ftp.listFiles();
        System.out.println("\nContenido de " + ftp.printWorkingDirectory() + ":");
        if (files == null || files.length == 0) {
            System.out.println("(vacío)");
            return;
        }
        for (FTPFile f : files) {
            String tipo = f.isDirectory() ? "[DIR] " : f.isFile() ? "[FILE]" : "[OTRO]";
            System.out.println(" - " + tipo + f.getName());
        }
    }

    private static void listarDirectorios(FTPSClient ftp) throws IOException {
        FTPFile[] files = ftp.listFiles();
        boolean hayDirs = false;
        if (files != null) {
            for (FTPFile f : files) {
                if (f.isDirectory()) {
                    hayDirs = true;
                    System.out.println("   " + f.getName());
                }
            }
        }
        if (!hayDirs) System.out.println("   (sin subdirectorios)");
    }

    private static void descargar(FTPSClient ftp, String nombre) throws IOException {
        String destinoLocal = "C:/Users/dam2/Downloads/" + nombre;
        try (FileOutputStream fos = new FileOutputStream(destinoLocal)) {
            if (ftp.retrieveFile(nombre, fos)) System.out.println("Descargado en: " + destinoLocal);
            else System.out.println("Error descargando (código " + ftp.getReplyCode() + ").");
        }
    }

    private static void subir(FTPSClient ftp, String rutaLocal, String destinoRemoto) throws IOException {
        File fLocal = new File(rutaLocal);
        try (FileInputStream fis = new FileInputStream(fLocal)) {
            // Asegúrate de que el tipo sea Binario antes de la operación
            ftp.setFileType(FTPSClient.BINARY_FILE_TYPE);

            if (ftp.storeFile(destinoRemoto, fis)) {
                System.out.println("Subido con éxito.");
            } else {
                // Esto imprimirá el mensaje exacto del servidor (ej: "Permission denied")
                System.out.println("Error 550 detectado. Mensaje del servidor: " + ftp.getReplyString());
            }
        }
    }

}
