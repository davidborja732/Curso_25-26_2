package ExplicacionComparatorYCollections.modelo;

public class Student implements Comparable<Student>{
    int id;
    String Nombre;

    public Student() {
    }

    public Student(int id, String nombre) {
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

    @Override
    public int compareTo(Student o) {
        return this.id - o.id;
    }
}
