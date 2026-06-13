package ExplicacionComparatorYCollections.modelo;

public class Teacher{
    int id;
    String Nombre;

    public Teacher() {
    }

    public Teacher(int id, String nombre) {
        this.id = id;
        Nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", Nombre='" + Nombre + '\'' +
                '}';
    }

}
