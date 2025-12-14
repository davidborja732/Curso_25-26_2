package Ej_1;

import java.io.*;
import java.net.*;
import java.util.*;

public class ServerNames {
    private static final int PORT = 5000;

    // Lista inicial de nombres de imágenes
    private static final List<String> IMAGES = new ArrayList<>(Arrays.asList(
            "foto1.jpg",
            "foto2.png",
            "logo.png",
            "banner.jpg",
            "icono.png"
    ));

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("[*] Servidor escuchando en puerto " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("[+] Cliente conectado: " + clientSocket.getInetAddress());
                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ClientHandler implements Runnable {
        private Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
            ) {
                String request;
                while ((request = in.readLine()) != null) {
                    if (request.equals("LISTAR")) {
                        out.println(String.join(",", IMAGES));

                    } else if (request.startsWith("SUBIR")) {
                        String[] parts = request.split(" ", 2);
                        if (parts.length == 2) {
                            String filename = parts[1];
                            IMAGES.add(filename);
                            out.println("OK: Imagen añadida -> " + filename);
                        } else {
                            out.println("ERROR: Formato incorrecto");
                        }

                    } else if (request.startsWith("DESCARGAR")) {
                        String[] parts = request.split(" ", 2);
                        if (parts.length == 2) {
                            String filename = parts[1];
                            if (IMAGES.contains(filename)) {
                                out.println("Archivo descargado -> " + filename);
                            } else {
                                out.println("ERROR: Imagen no encontrada");
                            }
                        } else {
                            out.println("ERROR: Formato incorrecto");
                        }

                    } else if (request.equals("SALIR")) {
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                    System.out.println("[-] Cliente desconectado");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
