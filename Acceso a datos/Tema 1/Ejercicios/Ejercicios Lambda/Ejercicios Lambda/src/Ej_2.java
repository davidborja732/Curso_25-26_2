import java.util.List;

public class Ej_2 {
    public static void main(String[] args) {
        List<String> listapalabras= new java.util.ArrayList<>(List.of("Montaña", "Aventura", "Cielo", "Abeja", "Estrella",
                "Bosque", "Amanecer", "Fuego", "Guitarra", "Amigo",
                "Jardín", "Dibujo", "Historia", "Acorde", "Luna"));
        System.out.println("Todas =");
        System.out.println(listapalabras);
        listapalabras.removeIf(s -> !s.toLowerCase().startsWith("a"));
        System.out.println("Solo con a =");
        System.out.println(listapalabras);
        /*listapalabras.stream().filter(s -> s.toLowerCase().startsWith("a")).toList();
        soloconA.forEach(System.out::println);*/
    }
}
