package Map_3;

import java.util.HashMap;
import java.util.Map;

public class Ej_1 {
    public static void main(String[] args) {
        Map<String, String> capitales = new HashMap<>();
        capitales.put("España", "Madrid");
        capitales.put("Francia", "París");
        capitales.put("Italia", "Roma");
        capitales.put("Alemania", "Berlín");
        capitales.put("Japón", "Tokio");
        capitales.forEach((key, value) -> {
            System.out.println(key+" su capital es "+value);
        });
    }
}
