import java.util.Scanner;

public class MenuApp implements Runnable {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void run() {

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
                case "1" -> new Thread(new FtpService()).start();
                case "2" -> new Thread(new MailService()).start();
                case "3" -> new Thread(new ApiService()).start();
                case "0" -> exit = true;
                default -> System.out.println("Opción no válida");
            }
        }
    }
}
