import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ej_1 {

    public static void main(String[] args) {
        String texto="En la red local, las direcciones 192.168.1.1 y 192.168.1.2 se utilizan para los routers. " +
                "Mientras tanto, los servidores tienen asignadas las IPs 10.0.0.1 y 10.0.0.2, facilitando el acceso a " +
                "archivos compartidos. Finalmente, el dispositivo móvil, con la IP 172.16.0.5, " +
                "se conecta sin problemas a la red Wi-Fi.".replaceAll("[.,]","");
        String regex="\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}";
        Pattern pattern=Pattern.compile(regex);
        Matcher matcher= pattern.matcher(texto);{
            while (matcher.find()){
                System.out.println("IP = "+matcher.group());
            }
        }
    }

}
