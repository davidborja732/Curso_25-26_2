import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;
import java.util.Scanner;

public class MailService implements Runnable {

    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final int SMTP_PORT = 587;

    private static final String USER = "tu_correo@gmail.com";
    private static final String PASS = "contraseña_de_aplicacion";

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void run() {

        System.out.println("\n=== MAIL SERVICE ===");

        System.out.println("Destinatario:");
        String to = scanner.nextLine();

        System.out.println("Asunto:");
        String subject = scanner.nextLine();

        System.out.println("Cuerpo:");
        String body = scanner.nextLine();

        sendMail(to, subject, body);
    }

    public void sendMail(String to, String subject, String body) {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", String.valueOf(SMTP_PORT));

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USER, PASS);
            }
        });

        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(USER));
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
