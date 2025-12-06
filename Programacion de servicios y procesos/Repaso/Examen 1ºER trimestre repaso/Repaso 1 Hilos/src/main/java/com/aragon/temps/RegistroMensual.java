package com.aragon.temps;

/**
 * Guarda los máximos y mínimos por mes con exclusión mutua.
 */
public class RegistroMensual {
    private double tempMax = Double.NEGATIVE_INFINITY;
    private String fechaMax = "", horaMax = "", estacionMax = "";

    private double tempMin = Double.POSITIVE_INFINITY;
    private String fechaMin = "", horaMin = "", estacionMin = "";

    public synchronized void actualizarMax(DatosDia d) {
        actualizar(d.getTmax(), true, d.fecha, d.horatmax, d.nombre, d.indicativo);
    }

    public synchronized void actualizarMin(DatosDia d) {
        actualizar(d.getTmin(), false, d.fecha, d.horatmin, d.nombre, d.indicativo);
    }

    private void actualizar(double t, boolean esMax, String fecha, String hora, String nombre, String indicativo) {
        if (Double.isNaN(t)) return;
        String estacion = nombre != null ? nombre : indicativo;

        if (esMax && t > tempMax) {
            tempMax = t; fechaMax = fecha; horaMax = hora; estacionMax = estacion;
        } else if (!esMax && t < tempMin) {
            tempMin = t; fechaMin = fecha; horaMin = hora; estacionMin = estacion;
        }
    }

    // Getters
    public synchronized double getTempMax() { return tempMax; }
    public synchronized String getFechaMax() { return fechaMax; }
    public synchronized String getHoraMax() { return horaMax; }
    public synchronized String getEstacionMax() { return estacionMax; }

    public synchronized double getTempMin() { return tempMin; }
    public synchronized String getFechaMin() { return fechaMin; }
    public synchronized String getHoraMin() { return horaMin; }
    public synchronized String getEstacionMin() { return estacionMin; }

    public synchronized boolean hasMax() { return tempMax != Double.NEGATIVE_INFINITY; }
    public synchronized boolean hasMin() { return tempMin != Double.POSITIVE_INFINITY; }
}
