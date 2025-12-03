package Ej_1_Ficheros.TCP;

import java.io.*;
import java.net.*;

public class ServidorEjer1 {
    private static final int PUERTO = 5000;
    private static final String DIRECTORIO = "ficherosserver"; // carpeta de trabajo

    public static void main(String[] args) {
        File dir = new File(DIRECTORIO);
        if (!dir.exists()) {
            dir.mkdir(); // crea la carpeta si no existe
        }

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
                        File dir = new File(DIRECTORIO);
                        String[] archivos = dir.list();
                        if (archivos != null && archivos.length > 0) {
                            for (String nombre : archivos) {
                                salida.println(nombre);
                            }
                        } else {
                            salida.println("No hay ficheros en el directorio");
                        }
                        salida.println("FIN");
                    } else if (opcion.startsWith("MOSTRAR")) {
                        String[] partes = opcion.split(" ", 2);
                        if (partes.length < 2) {
                            salida.println("Debes indicar el nombre del fichero");
                            salida.println("FIN");
                            continue;
                        }
                        String nombreFichero = partes[1];
                        File fichero = new File(DIRECTORIO, nombreFichero);
                        if (fichero.exists() && fichero.isFile()) {
                            try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
                                String linea;
                                while ((linea = br.readLine()) != null) {
                                    salida.println(linea);
                                }
                            }
                        } else {
                            salida.println("Fichero no encontrado");
                        }
                        salida.println("FIN");
                    } else if (opcion.equalsIgnoreCase("SALIR")) {
                        salida.println("Conexión cerrada");
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
