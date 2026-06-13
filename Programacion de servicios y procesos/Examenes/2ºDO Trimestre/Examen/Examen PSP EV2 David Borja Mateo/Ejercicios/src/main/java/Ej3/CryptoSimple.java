package Ej3;

import java.io.*;
import java.nio.file.*;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;

public class CryptoSimple {

    // Calcula el hash SHA‑256 de un archivo
    public static String sha256(Path file) throws Exception {
        return hash(file, "SHA-256");
    }

    // Función genérica para calcular hashes
    private static String hash(Path file, String alg) throws Exception {

        // Crea el objeto que calcula el hash con el algoritmo indicado
        MessageDigest md = MessageDigest.getInstance(alg);

        // Abre el archivo para leerlo en bloques
        try (InputStream in = Files.newInputStream(file)) {
            byte[] buf = new byte[4096];
            int n;

            // Lee el archivo por partes y actualiza el hash
            while ((n = in.read(buf)) > 0) md.update(buf, 0, n);
        }

        // Convierte el hash (bytes) a texto hexadecimal
        StringBuilder sb = new StringBuilder();
        for (byte b : md.digest()) sb.append(String.format("%02x", b));
        return sb.toString();
    }

    // Cifra un archivo con AES y lo guarda en la carpeta "archivos"
    public static Path aesEncrypt(Path inFile, byte[] key) throws Exception {

        // Carpeta donde se guardarán los archivos cifrados
        Path outDir = Path.of("archivos");

        // Si no existe, la crea
        if (!Files.exists(outDir)) Files.createDirectories(outDir);

        // Nombre del archivo cifrado (añade .enc)
        Path outFile = outDir.resolve(inFile.getFileName().toString() + ".enc");

        // Crea la clave AES a partir del array de bytes
        SecretKeySpec k = new SecretKeySpec(key, "AES");

        // Genera un IV aleatorio de 16 bytes (necesario para AES-CBC)
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // Configura el cifrador AES en modo CBC con padding
        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        c.init(Cipher.ENCRYPT_MODE, k, ivSpec);

        // Abre el archivo original y el archivo cifrado
        try (FileInputStream in = new FileInputStream(inFile.toFile());
             FileOutputStream out = new FileOutputStream(outFile.toFile())) {

            // Escribe el IV al principio del archivo cifrado
            out.write(iv);

            byte[] buf = new byte[4096];
            int n;

            // Cifra el archivo por bloques
            while ((n = in.read(buf)) > 0) {
                out.write(c.update(buf, 0, n));
            }

            // Finaliza el cifrado y escribe los últimos bytes
            out.write(c.doFinal());
        }

        // Devuelve la ruta del archivo cifrado
        return outFile;
    }

    // Descifra un archivo AES y lo guarda en la misma carpeta que el cifrado
    public static Path aesDecrypt(Path encryptedFile, byte[] key) throws Exception {

        // Carpeta donde está el archivo cifrado
        Path outDir = encryptedFile.getParent();

        // Nombre del archivo descifrado
        String originalName = encryptedFile.getFileName().toString().replace(".enc", "");
        Path outFile = outDir.resolve(originalName + "_descifrado.txt");

        // Crea la clave AES
        SecretKeySpec k = new SecretKeySpec(key, "AES");

        // Abre el archivo cifrado y el archivo de salida
        try (FileInputStream in = new FileInputStream(encryptedFile.toFile());
             FileOutputStream out = new FileOutputStream(outFile.toFile())) {

            // Lee el IV que está al principio del archivo cifrado
            byte[] iv = in.readNBytes(16);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);

            // Configura el descifrador AES
            Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            c.init(Cipher.DECRYPT_MODE, k, ivSpec);

            byte[] buf = new byte[4096];
            int n;

            // Descifra el archivo por bloques
            while ((n = in.read(buf)) > 0) {
                out.write(c.update(buf, 0, n));
            }

            // Finaliza el descifrado
            out.write(c.doFinal());
        }

        // Devuelve la ruta del archivo descifrado
        return outFile;
    }

    // Programa principal que ejecuta todo
    public static void main(String[] args) throws Exception {

        // Archivo original a cifrar
        Path archivo = Path.of("archivos/nombre_cifrado.txt");

        // Clave AES de 16 bytes (AES-128)
        byte[] key = "1234567890123456".getBytes();

        // Calcula el SHA del archivo original
        String shaOriginal = sha256(archivo);
        System.out.println("SHA-256 original: " + shaOriginal);

        // Cifra el archivo
        Path cifrado = aesEncrypt(archivo, key);
        System.out.println("Archivo cifrado en: " + cifrado);

        // Descifra el archivo cifrado
        Path descifrado = aesDecrypt(cifrado, key);
        System.out.println("Archivo descifrado en: " + descifrado);

        // Calcula el SHA del archivo descifrado
        String shaDescifrado = sha256(descifrado);
        System.out.println("SHA-256 descifrado: " + shaDescifrado);

        // Compara ambos SHA para verificar integridad
        if (shaOriginal.equals(shaDescifrado)) {
            System.out.println("El archivo descifrado es idéntico al original");
        } else {
            System.out.println("El archivo descifrado NO coincide con el original");
        }
    }
}
