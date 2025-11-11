import java.io.File;
import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        File carpeta = new File("Archivos");
        File[] archivos = carpeta.listFiles();
        if (archivos == null || archivos.length == 0) {
            System.out.println("No se encontraron archivos.");
            return;
        }

        Map<String, ResultadoMensual> resultados = new ConcurrentHashMap<>();
        AtomicReference<RegistroClima> maxAbsoluto = new AtomicReference<>();
        AtomicReference<RegistroClima> minAbsoluto = new AtomicReference<>();

        ExecutorService pool = Executors.newFixedThreadPool(2);

        for (File archivo : archivos) {
            pool.execute(new ProcesadorArchivo(archivo.toPath(), resultados, maxAbsoluto, minAbsoluto));
        }

        pool.shutdown();
        pool.awaitTermination(2, TimeUnit.MINUTES);

        System.out.println("📅 Resultados por mes:");
        resultados.forEach((mes, resultado) -> {
            System.out.println("Mes: " + mes);
            System.out.println("  🔴 Máxima: " + resultado.getMaximo());
            System.out.println("  🔵 Mínima: " + resultado.getMinimo());
        });

        System.out.println("\n🏁 Extremas absolutas:");
        System.out.println("🔴 Máxima absoluta: " + maxAbsoluto.get());
        System.out.println("🔵 Mínima absoluta: " + minAbsoluto.get());
    }
}
