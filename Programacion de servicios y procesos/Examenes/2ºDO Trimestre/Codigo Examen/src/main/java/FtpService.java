import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class FtpService implements Runnable {

    private static final String HOST = "eu-central-1.sftpcloud.io";
    private static final int PORT = 21;
    private static final String USER = "4c5621a82dcf4663b841ef660a814f2b";
    private static final String PASS = "PrId6Bsi36QOv2EdbkNu3a9Siror13hp";

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void run() {

        System.out.println("\n=== FTP SERVICE ===");

        System.out.println("Ruta archivo local a subir:");
        Path local = Path.of(scanner.nextLine().trim());

        System.out.println("Nombre archivo remoto:");
        String remote = scanner.nextLine().trim();

        uploadFile(local, remote);

        System.out.println("Ruta archivo local para descargar:");
        Path download = Path.of(scanner.nextLine().trim());

        downloadFile(remote, download);
    }

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
