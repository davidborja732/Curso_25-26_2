package Map_3.EJ_3;

public class Estudiante {
    String Nombre;
    String Apellido;
    String Grado;
    String Edad;

    public Estudiante() {
    }

    public Estudiante(String nombre, String apellido, String grado, String edad) {
        Nombre = nombre;
        Apellido = apellido;
        Grado = grado;
        Edad = edad;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getGrado() {
        return Grado;
    }

    public void setGrado(String grado) {
        Grado = grado;
    }

    public String getEdad() {
        return Edad;
    }

    public void setEdad(String edad) {
        Edad = edad;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "Nombre='" + Nombre + '\'' +
                ", Apellido='" + Apellido + '\'' +
                ", Grado='" + Grado + '\'' +
                ", Edad='" + Edad + '\'' +
                '}';
    }
}
