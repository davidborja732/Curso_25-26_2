public class RegistroClima {
    private String fecha;
    private String nombre;
    private String horatmax;
    private String horatmin;
    private String tmax;
    private String tmin;

    public String getFecha() { return fecha; }
    public String getNombre() { return nombre; }
    public String getHoratmax() { return horatmax; }
    public String getHoratmin() { return horatmin; }
    public String getTmax() { return tmax; }
    public String getTmin() { return tmin; }

    @Override
    public String toString() {
        return fecha + " | " + nombre + " | Tmax: " + tmax + " (" + horatmax + ") | Tmin: " + tmin + " (" + horatmin + ")";
    }
}
