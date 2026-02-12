package org.example.MenuThread;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.EndOfFileException;
import org.jline.reader.UserInterruptException;

import java.util.Properties;
import java.util.Scanner;

public class MailService implements Runnable {

    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final int SMTP_PORT = 587;

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void run() {

        System.out.println("\n=== MAIL SERVICE ===");

        // Pide el correo del usuario que enviará el mensaje
        System.out.print("Tu correo Gmail: ");
        String user = scanner.nextLine();

        // Pide la contraseña sin mostrar NADA en pantalla
        String pass = leerContrasenaInvisible();

        // Pide el destinatario
        System.out.print("Destinatario: ");
        String to = scanner.nextLine();

        // Pide el asunto
        System.out.print("Asunto: ");
        String subject = scanner.nextLine();

        // Pide el cuerpo del mensaje
        System.out.print("Cuerpo: ");
        String body = scanner.nextLine();

        // Envía el correo usando los datos introducidos
        sendMail(user, pass, to, subject, body);
    }

    // ============================================
    // FUNCIÓN PARA LEER CONTRASEÑA SIN MOSTRAR NADA
    // ============================================
    private String leerContrasenaInvisible() {
        LineReader reader = LineReaderBuilder.builder().build();

        try {
            // (char) 0 → NO muestra nada mientras escribes
            return reader.readLine("Contraseña: ", (char) 0);
        } catch (UserInterruptException | EndOfFileException e) {
            System.out.println("\nEntrada cancelada.");
            return "";
        }
    }

    public void sendMail(String user, String pass, String to, String subject, String body) {

        // Configuración del servidor SMTP de Gmail
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", String.valueOf(SMTP_PORT));

        // Crea la sesión con autenticación usando el correo y contraseña del usuario
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });

        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(user));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

            System.out.println("Correo enviado correctamente.");

        } catch (MessagingException e) {
            System.err.println("Error enviando correo: " + e.getMessage());
        }
    }
}
