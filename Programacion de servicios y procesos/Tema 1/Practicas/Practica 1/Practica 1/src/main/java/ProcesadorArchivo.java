import com.google.gson.Gson;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class ProcesadorArchivo implements Runnable {
    private final Path archivo;
    private final Map<String, ResultadoMensual> resultados;
    private final AtomicReference<RegistroClima> maxAbsoluto;
    private final AtomicReference<RegistroClima> minAbsoluto;

    public ProcesadorArchivo(Path archivo, Map<String, ResultadoMensual> resultados,
                             AtomicReference<RegistroClima> maxAbsoluto,
                             AtomicReference<RegistroClima> minAbsoluto) {
        this.archivo = archivo;
        this.resultados = resultados;
        this.maxAbsoluto = maxAbsoluto;
        this.minAbsoluto = minAbsoluto;
    }

    @Override
    public void run() {
        try {
            String content = new String(Files.readAllBytes(archivo));
            RegistroClima registro = new Gson().fromJson(content, RegistroClima.class);
            String mes = registro.getFecha().substring(0, 7);

            resultados.computeIfAbsent(mes, k -> new ResultadoMensual()).actualizar(registro);

            double nuevaMax = Double.parseDouble(registro.getTmax().replace(",", "."));
            double nuevaMin = Double.parseDouble(registro.getTmin().replace(",", "."));

            maxAbsoluto.updateAndGet(actual -> (actual == null || nuevaMax > Double.parseDouble(actual.getTmax().replace(",", "."))) ? registro : actual);
            minAbsoluto.updateAndGet(actual -> (actual == null || nuevaMin < Double.parseDouble(actual.getTmin().replace(",", "."))) ? registro : actual);

        } catch (Exception e) {
            System.err.println("Error en archivo: " + archivo.getFileName() + " → " + e.getMessage());
        }
    }
}
