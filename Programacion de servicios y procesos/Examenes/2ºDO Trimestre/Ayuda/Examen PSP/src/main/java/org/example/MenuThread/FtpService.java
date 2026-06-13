package org.example.MenuThread;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Scanner;

public class FtpService implements Runnable {

    private static final String HOST = "192.168.50.244";
    private static final int PORT = 21;
    private static final String USER = "smr1";
    private static final String PASS = "VonNeumann1945";

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void run() {

        boolean exit = false;

        while (!exit) {

            System.out.println("\n=== FTP SERVICE ===");
            System.out.println("1. Listar archivos");
            System.out.println("2. Subir archivo");
            System.out.println("3. Descargar archivo");
            System.out.println("0. Salir");
            System.out.print("Opción: ");

            String op = scanner.nextLine().trim();

            switch (op) {

                case "1" -> listarArchivos();

                case "2" -> subirArchivoMenu();

                case "3" -> descargarArchivoMenu();

                case "0" -> exit = true;

                default -> System.out.println("Opción no válida");
            }
        }

        System.out.println("Saliendo del servicio FTP...");
    }

    // ============================
    //      MENÚ: SUBIR ARCHIVO
    // ============================
    private void subirArchivoMenu() {
        System.out.println("Ruta archivo local a subir:");
        Path local = Path.of(scanner.nextLine().trim());

        System.out.println("Nombre archivo remoto:");
        String remote = scanner.nextLine().trim();

        uploadFile(local, remote);
    }

    // ============================
    //    MENÚ: DESCARGAR ARCHIVO
    // ============================
    private void descargarArchivoMenu() {
        System.out.println("Nombre archivo remoto a descargar:");
        String remote = scanner.nextLine().trim();

        System.out.println("Ruta archivo local donde guardar:");
        Path local = Path.of(scanner.nextLine().trim());

        downloadFile(remote, local);
    }

    // ============================
    //       LISTAR ARCHIVOS
    // ============================
    private void listarArchivos() {
        FTPClient ftp = new FTPClient();

        try {
            ftp.connect(HOST, PORT);
            ftp.login(USER, PASS);
            ftp.enterLocalPassiveMode();

            System.out.println("\n=== ARCHIVOS EN EL SERVIDOR ===");
            Arrays.stream(ftp.listFiles())
                    .forEach(System.out::println);

            ftp.logout();
            ftp.disconnect();

        } catch (IOException e) {
            System.err.println("Error listando archivos: " + e.getMessage());
        }
    }

    // ============================
    //         SUBIR ARCHIVO
    // ============================
    public void uploadFile(Path localFile, String remoteFile) {
        FTPClient ftp = new FTPClient();

        try {
            ftp.connect(HOST, PORT);
            ftp.login(USER, PASS);

            ftp.enterLocalPassiveMode();
            ftp.setFileType(FTP.BINARY_FILE_TYPE);

            try (FileInputStream fis = new FileInputStream(localFile.toFile())) {
                boolean ok = ftp.storeFile(remoteFile, fis);
                System.out.println(ok ? "Archivo subido." : "Error subiendo archivo.");
            }

            ftp.logout();
            ftp.disconnect();

        } catch (IOException e) {
            System.err.println("Error FTP: " + e.getMessage());
        }
    }

    // ============================
    //       DESCARGAR ARCHIVO
    // ============================
    public void downloadFile(String remoteFile, Path localFile) {
        FTPClient ftp = new FTPClient();

        try {
            ftp.connect(HOST, PORT);
            ftp.login(USER, PASS);

            ftp.enterLocalPassiveMode();
            ftp.setFileType(FTP.BINARY_FILE_TYPE);

            try (FileOutputStream fos = new FileOutputStream(localFile.toFile())) {
                boolean ok = ftp.retrieveFile(remoteFile, fos);
                System.out.println(ok ? "Archivo descargado." : "Error descargando archivo.");
            }

            ftp.logout();
            ftp.disconnect();

        } catch (IOException e) {
            System.err.println("Error FTP: " + e.getMessage());
        }
    }
}
