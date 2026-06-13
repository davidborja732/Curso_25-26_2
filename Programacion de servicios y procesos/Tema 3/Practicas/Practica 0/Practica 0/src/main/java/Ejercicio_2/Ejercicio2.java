package Ejercicio_2;
import java.io.*;
import java.net.*;
import java.time.LocalDateTime;

public class Ejercicio2 {

    public static void main(String[] args) {
        int puerto = 8080;

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor HTTP en puerto " + puerto);

            while (true) {
                Socket cliente = servidor.accept();

                BufferedReader in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                OutputStream out = cliente.getOutputStream();

                String linea = in.readLine();
                if (linea == null) {
                    cliente.close();
                    continue;
                }

                String[] partes = linea.split(" ");
                if (partes.length < 2) {
                    cliente.close();
                    continue;
                }

                String ruta = partes[1];
                String respuesta = "";
                int codigo = 200;

                if (ruta.equals("/")) {
                    respuesta = "<h1>Bienvenido al servidor HTTP</h1>";
                }
                else if (ruta.equals("/hora")) {
                    respuesta = "Hora actual: " + LocalDateTime.now();
                }
                else if (ruta.equals("/info")) {
                    respuesta = "Equipo: " + InetAddress.getLocalHost().getHostName() +
                            "<br>SO: " + System.getProperty("os.name");
                }
                else {
                    codigo = 404;
                    respuesta = "404 - No encontrado";
                }

                String cabecera =
                        "HTTP/1.1 " + codigo + " OK\r\n" +
                                "Content-Type: text/html; charset=UTF-8\r\n" +
                                "Content-Length: " + respuesta.length() + "\r\n" +
                                "\r\n";

                out.write(cabecera.getBytes());
                out.write(respuesta.getBytes());

                out.flush();
                cliente.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}