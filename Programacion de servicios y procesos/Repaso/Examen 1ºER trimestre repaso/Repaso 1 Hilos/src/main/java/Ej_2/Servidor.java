package Ej_2;

import java.io.*;
import java.net.*;
import java.util.*;

public class Servidor {
    private static final int PUERTO = 5000;


    private static final Map<String, String[]> contactos = new HashMap<>();

    static {
        contactos.put("juan", new String[]{"666111222", "juan@mail.com"});
        contactos.put("ana", new String[]{"666333444", "ana@mail.com"});
        contactos.put("luis", new String[]{"666555666", "luis@mail.com"});
    }

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            System.out.println("Servidor iniciado en puerto " + PUERTO);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Cliente conectado: " + socket.getInetAddress());
                new Thread(new ManejadorCliente(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ManejadorCliente implements Runnable {
        private Socket socket;

        public ManejadorCliente(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (
                    BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter salida = new PrintWriter(socket.getOutputStream(), true)
            ) {
                String opcion;
                while ((opcion = entrada.readLine()) != null) {
                    if (opcion.equalsIgnoreCase("LISTAR")) {
                        for (Map.Entry<String, String[]> entry : contactos.entrySet()) {
                            salida.println(entry.getKey() + " Telefono: " + entry.getValue()[0] + " Correo: " + entry.getValue()[1]);
                        }
                        salida.println("FIN");
                    } else if (opcion.startsWith("BUSCAR")) {
                        String nombre = opcion.split(" ")[1].toLowerCase();
                        if (contactos.containsKey(nombre)) {
                            String[] datos = contactos.get(nombre);
                            salida.println(nombre.toUpperCase() + " Telefono: " + datos[0] + " Correo: " + datos[1]);
                        } else {
                            salida.println("Contacto no encontrado");
                        }
                    } else if (opcion.equalsIgnoreCase("SALIR")) {
                        salida.println("Conexion cerrada");
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
