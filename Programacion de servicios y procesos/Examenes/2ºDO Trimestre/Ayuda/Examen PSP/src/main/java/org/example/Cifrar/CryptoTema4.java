package org.example.Cifrar;

import javax.crypto.*;
import java.security.*;

public class CryptoTema4 {

    // ============================
    //  GENERAR CLAVE SIMÉTRICA AES
    // ============================
    public static SecretKey generarClaveAES() throws Exception {
        // Crea un generador de claves AES
        KeyGenerator kg = KeyGenerator.getInstance("AES");

        // Configura la clave a 128 bits (tamaño estándar)
        kg.init(128);

        // Genera y devuelve la clave
        return kg.generateKey();
    }

    // ============================
    // CIFRAR CON AES (SIMÉTRICO)
    // ============================
    public static byte[] cifrarAES(byte[] datos, SecretKey clave) throws Exception {

        // Crea un objeto Cipher configurado para AES en modo ECB con padding
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

        // Inicializa el cifrador en modo ENCRYPT con la clave
        cipher.init(Cipher.ENCRYPT_MODE, clave);

        // Cifra los datos y devuelve el resultado
        return cipher.doFinal(datos);
    }

    // ============================
    // DESCIFRAR CON AES (SIMÉTRICO)
    // ============================
    public static byte[] descifrarAES(byte[] datosCifrados, SecretKey clave) throws Exception {

        // Crea el objeto Cipher para descifrar
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

        // Inicializa en modo DECRYPT con la misma clave
        cipher.init(Cipher.DECRYPT_MODE, clave);

        // Descifra los datos y devuelve el resultado
        return cipher.doFinal(datosCifrados);
    }

    // ============================
    // GENERAR PAR DE CLAVES RSA
    // ============================
    public static KeyPair generarClavesRSA() throws Exception {

        // Crea un generador de claves RSA
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");

        // Configura el tamaño de clave (2048 bits recomendado)
        kpg.initialize(2048);

        // Genera el par de claves (pública + privada)
        return kpg.generateKeyPair();
    }

    // ============================
    // CIFRAR CON RSA (ASIMÉTRICO)
    // ============================
    public static byte[] cifrarRSA(byte[] datos, PublicKey clavePublica) throws Exception {

        // Crea un cifrador RSA
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

        // Inicializa en modo ENCRYPT usando la clave pública
        cipher.init(Cipher.ENCRYPT_MODE, clavePublica);

        // Cifra los datos
        return cipher.doFinal(datos);
    }

    // ============================
    // DESCIFRAR CON RSA (ASIMÉTRICO)
    // ============================
    public static byte[] descifrarRSA(byte[] datosCifrados, PrivateKey clavePrivada) throws Exception {

        // Crea un descifrador RSA
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

        // Inicializa en modo DECRYPT usando la clave privada
        cipher.init(Cipher.DECRYPT_MODE, clavePrivada);

        // Descifra los datos
        return cipher.doFinal(datosCifrados);
    }

    // ============================
    // PROGRAMA PRINCIPAL
    // ============================
    public static void main(String[] args) throws Exception {

        // Texto de ejemplo a cifrar
        String mensaje = "Hola David, esto es cifrado simétrico y asimétrico.";
        byte[] datosOriginales = mensaje.getBytes();

        System.out.println("MENSAJE ORIGINAL: " + mensaje);

        // ============================
        // PRUEBA DE CIFRADO SIMÉTRICO AES
        // ============================

        // Genera una clave AES
        SecretKey claveAES = generarClaveAES();
        System.out.println("\n--- CIFRADO SIMÉTRICO (AES) ---");

        // Cifra el mensaje con AES
        byte[] cifradoAES = cifrarAES(datosOriginales, claveAES);
        System.out.println("AES cifrado (bytes): " + cifradoAES.length);

        // Descifra el mensaje con AES
        byte[] descifradoAES = descifrarAES(cifradoAES, claveAES);
        System.out.println("AES descifrado: " + new String(descifradoAES));

        // ============================
        // PRUEBA DE CIFRADO ASIMÉTRICO RSA
        // ============================

        System.out.println("\n--- CIFRADO ASIMÉTRICO (RSA) ---");

        // Genera un par de claves RSA
        KeyPair clavesRSA = generarClavesRSA();

        // Cifra usando la clave pública
        byte[] cifradoRSA = cifrarRSA(datosOriginales, clavesRSA.getPublic());
        System.out.println("RSA cifrado (bytes): " + cifradoRSA.length);

        // Descifra usando la clave privada
        byte[] descifradoRSA = descifrarRSA(cifradoRSA, clavesRSA.getPrivate());
        System.out.println("RSA descifrado: " + new String(descifradoRSA));
    }
}

