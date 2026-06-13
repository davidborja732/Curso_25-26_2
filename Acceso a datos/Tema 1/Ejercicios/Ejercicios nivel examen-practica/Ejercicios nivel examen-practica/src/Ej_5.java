import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Ej_5 {
    /*Crea un String con un contenido bastante largo. Ahora trabaja con dicho String para obtener
    por medio de stream el número de ocurrencias de cada palabra.
            Consejo: Si quieres simplificarlo mira los métodos de la clase Collectors*/
    static void main(String[] args) {
        String texto="El sol brilla, brilla sin parar, la brisa suave acaricia el mar. El mar canta, el mar sueña, el mar vive, y en su canto, una historia se escribe.".replaceAll("[,.]", "").toLowerCase();
        List<String> lista= Arrays.stream(texto.split(" ")).toList();
        lista.stream().collect(Collectors.groupingBy(s -> s,Collectors.counting())).forEach((s, aLong) -> System.out.println("La palabra "+s.toUpperCase()+" aparece "+aLong+" vez(es)."));
    }
}
