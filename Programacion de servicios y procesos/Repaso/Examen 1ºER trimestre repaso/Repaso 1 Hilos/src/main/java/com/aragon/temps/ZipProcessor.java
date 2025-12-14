package com.aragon.temps;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipProcessor {

    private final ContenedorDatos contenedor;
    private final Gson gson = new Gson();

    public ZipProcessor(ContenedorDatos contenedor) {
        this.contenedor = contenedor;
    }

    public void processZip(InputStream zipStream, ExecutorService pool) throws Exception {
        try (ZipInputStream zis = new ZipInputStream(zipStream)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.isDirectory()) continue;

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int read;
                while ((read = zis.read(buffer)) != -1) {
                    baos.write(buffer, 0, read);
                }
                byte[] content = baos.toByteArray();

                pool.submit(() -> {
                    try {
                        String json = new String(content, StandardCharsets.UTF_8).trim();
                        if (json.isEmpty()) return;
                        DatosDia d = gson.fromJson(json, DatosDia.class);
                        if (d == null || d.fecha == null) return;

                        String claveMes = d.fecha.length() >= 7 ? d.fecha.substring(0, 7) : d.fecha;
                        RegistroMensual reg = contenedor.obtenerRegistro(claveMes);
                        reg.actualizarMax(d);
                        reg.actualizarMin(d);
                    } catch (Exception e) {
                        System.err.println("Error procesando entrada ZIP: " + e.getMessage());
                    }
                });
            }

        }
        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.HOURS);
    }
}
