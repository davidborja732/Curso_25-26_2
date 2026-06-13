package org.example.MenuThread;

import java.util.Scanner;

public class MenuApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {

            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Servidor FTP real");
            System.out.println("2. Enviar correo Gmail");
            System.out.println("3. API JSONPlaceholder");
            System.out.println("0. Salir");
            System.out.print("Opción: ");

            String op = scanner.nextLine().trim();

            switch (op) {

                case "1" -> {
                    Thread t = new Thread(new FtpService());
                    t.start();
                    try {
                        t.join(); // Espera a que el usuario salga del menú FTP
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }

                case "2" -> {
                    Thread t = new Thread(new MailService());
                    t.start();
                    try {
                        t.join(); // Espera a que el usuario salga del menú Mail
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }

                case "3" -> {
                    Thread t = new Thread(new ApiService());
                    t.start();
                    try {
                        t.join(); // Espera a que el usuario salga del menú API
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }

                case "0" -> exit = true;

                default -> System.out.println("Opción no válida");
            }
        }

        System.out.println("Aplicación finalizada.");
    }
}
