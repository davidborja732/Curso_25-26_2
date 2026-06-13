package com.aragon.temps;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Contenedor concurrente de registros por "YYYY-MM".
 */
public class ContenedorDatos {
    private final ConcurrentHashMap<String, RegistroMensual> mapa = new ConcurrentHashMap<>();

    public RegistroMensual obtenerRegistro(String clave) {
        return mapa.computeIfAbsent(clave, k -> new RegistroMensual());
    }

    public ConcurrentHashMap<String, RegistroMensual> getMapa() {
        return mapa;
    }
}
