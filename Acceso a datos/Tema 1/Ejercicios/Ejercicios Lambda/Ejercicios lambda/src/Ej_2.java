import java.util.List;

public class Ej_2 {
    static void main() {
        List<String> listapalabras=List.of("Montaña", "Aventura", "Cielo", "Abeja", "Estrella",
                "Bosque", "Amanecer", "Fuego", "Guitarra", "Amigo",
                "Jardín", "Dibujo", "Historia", "Acorde", "Luna");
        List<String> soloconA=listapalabras.stream().filter(s -> s.toLowerCase().startsWith("a")).toList();
        soloconA.forEach(System.out::println);
    }
}
