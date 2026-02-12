package Ej2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Ej2Client {
    private static final String HOST = "localhost";
    private static final int PORT = 7001;

    public static void main(String[] args) {

        // Conectar al servidor
        try (
                Socket socket = new Socket(HOST, PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream())
                );
        ) {
            Scanner scanner=new Scanner(System.in);
            Integer message;
            System.out.println("Dime un numero= ");
            int numero=scanner.nextInt();
            if (numero>0 && !String.valueOf(numero).contains(".") && !String.valueOf(numero).contains(",")){
                message= numero;
            }else {
                message= (int) (Math.random()*10);
            }
                // Enviar mensaje al servidor
                out.println(message);
                // Recibir el echo del servidor
                String response = in.readLine();
                System.out.println(response);
        } catch (IOException e) {
            System.err.println("Error");
        }
    }
}