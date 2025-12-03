package Ej_1_Ficheros.TCP.UDP;

import java.io.*;
import java.net.*;

public class ServidorEjer1UDP {
    private static final int PUERTO = 5000;
    private static final String DIRECTORIO = "ficherosserver";

    public static void main(String[] args) {
        File dir = new File(DIRECTORIO);
        if (!dir.exists()) {
            dir.mkdir();
        }

        try (DatagramSocket socket = new DatagramSocket(PUERTO)) {
            System.out.println("Servidor UDP escuchando en puerto " + PUERTO);

            byte[] buffer = new byte[1024];

            while (true) {
                DatagramPacket paqueteEntrada = new DatagramPacket(buffer, buffer.length);
                socket.receive(paqueteEntrada);

                String mensaje = new String(paqueteEntrada.getData(), 0, paqueteEntrada.getLength());
                System.out.println("Petición recibida: " + mensaje);

                String respuesta = procesarPeticion(mensaje);

                byte[] datosRespuesta = respuesta.getBytes();
                DatagramPacket paqueteSalida = new DatagramPacket(
                        datosRespuesta,
                        datosRespuesta.length,
                        paqueteEntrada.getAddress(),
                        paqueteEntrada.getPort()
                );
                socket.send(paqueteSalida);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String procesarPeticion(String mensaje) {
        if (mensaje.equalsIgnoreCase("LISTAR")) {
            File dir = new File(DIRECTORIO);
            String[] archivos = dir.list();
            if (archivos != null && archivos.length > 0) {
                return String.join("\n", archivos);
            } else {
                return "No hay ficheros en el directorio";
            }
        } else if (mensaje.startsWith("MOSTRAR")) {
            String[] partes = mensaje.split(" ", 2);
            if (partes.length < 2) {
                return "Debes indicar el nombre del fichero";
            }
            String nombreFichero = partes[1];
            File fichero = new File(DIRECTORIO, nombreFichero);
            if (fichero.exists() && fichero.isFile()) {
                StringBuilder contenido = new StringBuilder();
                try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
                    String linea;
                    while ((linea = br.readLine()) != null) {
                        contenido.append(linea).append("\n");
                    }
                } catch (IOException e) {
                    return "Error al leer el fichero";
                }
                return contenido.toString();
            } else {
                return "Fichero no encontrado";
            }
        } else if (mensaje.equalsIgnoreCase("SALIR")) {
            return "Conexión cerrada";
        }
        return "Comando no válido";
    }
}

