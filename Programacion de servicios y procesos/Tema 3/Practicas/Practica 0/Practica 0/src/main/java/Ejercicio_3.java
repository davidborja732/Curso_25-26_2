
import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.io.Console;
import java.io.File;
import java.util.Properties;
import java.util.Scanner;

public class Ejercicio_3 {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {

            System.out.println("=== Envío de correo SMTP ===");

            System.out.print("SMTP host: ");
            String smtpHost = sc.nextLine();

            System.out.print("Puerto SMTP: ");
            String smtpPort = sc.nextLine();
            if (smtpPort.isEmpty()) smtpPort = "587";

            System.out.print("Usar SSL (s/n): ");
            boolean useSsl = sc.nextLine().equalsIgnoreCase("s");

            System.out.print("Correo emisor: ");
            String fromEmail = sc.nextLine();

            String password;
            Console console = System.console();
            if (console != null) {
                password = new String(console.readPassword("Contraseña: "));
            } else {
                System.out.print("Contraseña: ");
                password = sc.nextLine();
            }

            System.out.print("Correo destinatario: ");
            String toEmail = sc.nextLine();

            System.out.print("Asunto: ");
            String subject = sc.nextLine();

            System.out.println("Mensaje (escribe EOF para terminar):");
            StringBuilder bodyBuilder = new StringBuilder();
            while (true) {
                String line = sc.nextLine();
                if (line.equals("EOF")) break;
                bodyBuilder.append(line).append("\n");
            }
            String body = bodyBuilder.toString();

            System.out.print("Adjunto (ruta o vacío): ");
            String attach = sc.nextLine();
            File file = attach.isEmpty() ? null : new File(attach);

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.host", smtpHost);
            props.put("mail.smtp.port", smtpPort);
            props.put("mail.smtp.connectiontimeout", "8000");
            props.put("mail.smtp.timeout", "8000");

            if (useSsl) {
                props.put("mail.smtp.ssl.enable", "true");
            } else {
                props.put("mail.smtp.starttls.enable", "true");
            }

            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            });

            try {
                Message msg = new MimeMessage(session);
                msg.setFrom(new InternetAddress(fromEmail));
                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
                msg.setSubject(subject);

                if (file != null && file.exists()) {
                    MimeBodyPart textPart = new MimeBodyPart();
                    textPart.setText(body);

                    MimeBodyPart filePart = new MimeBodyPart();
                    filePart.attachFile(file);

                    Multipart mp = new MimeMultipart();
                    mp.addBodyPart(textPart);
                    mp.addBodyPart(filePart);

                    msg.setContent(mp);
                } else {
                    msg.setText(body);
                }

                Transport.send(msg);
                System.out.println("Correo enviado.");

            } catch (AuthenticationFailedException e) {
                System.out.println("Error: autenticación.");
            } catch (SendFailedException e) {
                System.out.println("Error: dirección inválida.");
            } catch (MessagingException e) {
                System.out.println("Error SMTP.");
            } catch (Exception e) {
                System.out.println("Error general.");
            }
        }
    }
}
