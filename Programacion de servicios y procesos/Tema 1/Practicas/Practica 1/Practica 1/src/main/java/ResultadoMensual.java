public class ResultadoMensual {
    private RegistroClima maximo;
    private RegistroClima minimo;

    public synchronized void actualizar(RegistroClima registro) {
        double nuevaMax = Double.parseDouble(registro.getTmax().replace(",", "."));
        double nuevaMin = Double.parseDouble(registro.getTmin().replace(",", "."));

        if (maximo == null || nuevaMax > Double.parseDouble(maximo.getTmax().replace(",", "."))) {
            maximo = registro;
        }
        if (minimo == null || nuevaMin < Double.parseDouble(minimo.getTmin().replace(",", "."))) {
            minimo = registro;
        }
    }

    public RegistroClima getMaximo() { return maximo; }
    public RegistroClima getMinimo() { return minimo; }
}
