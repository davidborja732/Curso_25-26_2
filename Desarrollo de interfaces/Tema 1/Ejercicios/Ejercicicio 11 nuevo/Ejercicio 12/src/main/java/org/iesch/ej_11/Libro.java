package org.iesch.ej_11;

public class Libro {
    Integer Id;
    String Titulo;
    String Autor;
    String ISBN;
    Integer Paginas;
    int Genero;
    int Disponible;

    public Libro(Integer id, String titulo, String autor, String ISBN, Integer paginas, int genero, int disponible) {
        Id = id;
        Titulo = titulo;
        Autor = autor;
        this.ISBN = ISBN;
        Paginas = paginas;
        Genero = genero;
        Disponible = disponible;
    }

    public Libro() {
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getAutor() {
        return Autor;
    }

    public void setAutor(String autor) {
        Autor = autor;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public Integer getPaginas() {
        return Paginas;
    }

    public void setPaginas(Integer paginas) {
        Paginas = paginas;
    }

    public int getGenero() {
        return Genero;
    }

    public void setGenero(int genero) {
        Genero = genero;
    }

    public int getDisponible() {
        return Disponible;
    }

    public void setDisponible(int disponible) {
        Disponible = disponible;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "Id=" + Id +
                ", Titulo='" + Titulo + '\'' +
                ", Autor='" + Autor + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", Paginas=" + Paginas +
                ", Genero='" + Genero + '\'' +
                ", Disponible='" + Disponible + '\'' +
                '}';
    }
}
