package com.aragon.temps;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Programa principal.
 *
 * Uso:
 *   java -jar ... [path/to/items.zip]
 *
 * Si no se proporciona path, usa "items.zip" en el directorio de ejecución.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        String zipPath = args.length >= 1 ? args[0] : "items.zip";

        ContenedorDatos contenedor = new ContenedorDatos();
        int threads = 1000;
        System.out.printf("Procesando '%s' con %d hilos...%n", zipPath, threads);

        ExecutorService pool = Executors.newFixedThreadPool(threads);
        ZipProcessor processor = new ZipProcessor(contenedor);

        try (InputStream is = new FileInputStream(zipPath)) {
            processor.processZip(is, pool);
        }

        // mostrar resultados por mes
        System.out.println("\n=== Temperaturas por mes ===");
        for (Map.Entry<String, RegistroMensual> e : contenedor.getMapa().entrySet()) {
            RegistroMensual r = e.getValue();
            System.out.println("\nMes: " + e.getKey());
            System.out.println(r.hasMax()
                    ? String.format(" Máxima: %.1f °C el %s a las %s en %s",
                    r.getTempMax(), r.getFechaMax(), r.getHoraMax(), r.getEstacionMax())
                    : " Máxima: no disponible");
            System.out.println(r.hasMin()
                    ? String.format(" Mínima: %.1f °C el %s a las %s en %s",
                    r.getTempMin(), r.getFechaMin(), r.getHoraMin(), r.getEstacionMin())
                    : " Mínima: no disponible");
        }

        // buscar absolutas
        double maxAbs = Double.NEGATIVE_INFINITY, minAbs = Double.POSITIVE_INFINITY;
        String infoMax = "", infoMin = "";
        for (RegistroMensual r : contenedor.getMapa().values()) {
            if (r.hasMax() && r.getTempMax() > maxAbs) {
                maxAbs = r.getTempMax();
                infoMax = String.format("%s %s %s", r.getFechaMax(), r.getHoraMax(), r.getEstacionMax());
            }
            if (r.hasMin() && r.getTempMin() < minAbs) {
                minAbs = r.getTempMin();
                infoMin = String.format("%s %s %s", r.getFechaMin(), r.getHoraMin(), r.getEstacionMin());
            }
        }

        System.out.println("\n=== Absolutas ===");
        System.out.println(maxAbs != Double.NEGATIVE_INFINITY
                ? String.format(" Máxima absoluta: %.1f °C (%s)", maxAbs, infoMax)
                : " Máxima absoluta: no disponible");
        System.out.println(minAbs != Double.POSITIVE_INFINITY
                ? String.format(" Mínima absoluta: %.1f °C (%s)", minAbs, infoMin)
                : " Mínima absoluta: no disponible");
    }
}
