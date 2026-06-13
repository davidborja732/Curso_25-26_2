package com.aragon.temps;

import com.google.gson.annotations.SerializedName;

/**
 * Representación de cada fichero diario según Metadatos.txt y EjemploDatos.txt.
 * Los campos se mantienen como String para soportar comas decimales y valores vacíos.
 */
public class DatosDia {
    public String fecha, indicativo, nombre, provincia, altitud;
    public String tmed, prec, tmin, horatmin, tmax, horatmax;
    public String dir, velmedia, racha, horaracha;
    @SerializedName(value = "presMax", alternate = {"presmax", "presMAX"})
    public String presMax;
    @SerializedName(value = "presMin", alternate = {"presmin", "presMIN"})
    public String presMin;
    public String horaPresMax, horaPresMin;

    private double parseDouble(String value) {
        if (value == null || value.trim().isEmpty()) return Double.NaN;
        try { return Double.parseDouble(value.trim().replace(',', '.')); }
        catch (Exception e) { return Double.NaN; }
    }

    public double getTmax() { return parseDouble(tmax); }
    public double getTmin() { return parseDouble(tmin); }

    @Override
    public String toString() {
        return "DatosDia{" +
                "fecha='" + fecha + '\'' +
                ", nombre='" + nombre + '\'' +
                ", tmax='" + tmax + '\'' +
                ", horatmax='" + horatmax + '\'' +
                ", tmin='" + tmin + '\'' +
                ", horatmin='" + horatmin + '\'' +
                '}';
    }

}
