package org.example.MenuThread;

import java.io.*;
import java.net.Socket;

public class MenuClientHandler implements Runnable {

    private final Socket client;

    public MenuClientHandler(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {

        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter out = new PrintWriter(client.getOutputStream(), true)
        ) {

            boolean exit = false;

            while (!exit) {

                out.println("\n=== MENÚ PRINCIPAL ===");
                out.println("1. Servidor FTP real");
                out.println("2. Enviar correo Gmail");
                out.println("3. API JSONPlaceholder");
                out.println("0. Salir");
                out.print("Opción: ");

                String op = in.readLine();
                if (op == null) break;

                switch (op.trim()) {

                    case "1" -> {
                        out.println("Iniciando servicio FTP...");
                        Thread t = new Thread(new FtpService());
                        t.start();
                    }

                    case "2" -> {
                        out.println("Iniciando servicio de correo...");
                        Thread t = new Thread(new MailService());
                        t.start();
                    }

                    case "3" -> {
                        out.println("Iniciando servicio API...");
                        Thread t = new Thread(new ApiService());
                        t.start();
                    }

                    case "0" -> {
                        out.println("Cerrando conexión...");
                        exit = true;
                        ConnectionCloser.close(client);
                        /*exit = true;
                        out.println("Saliendo del menú...");*/
                    }

                    default -> out.println("Opción no válida");
                }
            }

            client.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

