package org.iesch.ad.NconsultasYmas.servicio;

import jakarta.transaction.Transactional;
import org.iesch.ad.NconsultasYmas.modelo.Autor;
import org.iesch.ad.NconsultasYmas.modelo.Libro;
import org.iesch.ad.NconsultasYmas.repositorio.AutorRespositorio;
import org.iesch.ad.NconsultasYmas.repositorio.LibroRespositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Nmas1DemoServicio {
    @Autowired
    private AutorRespositorio autorRespositorio;
    @Autowired
    private LibroRespositorio libroRespositorio;

    @Transactional
    public void mostrarProblemaNMas1() {
        System.out.println("Mostrar los autores y sus libros");
        List<Autor> autores=autorRespositorio.findAll();
        System.out.println("autores: "+autores.size());
        System.out.println("Accedemos a los libros");
        for (Autor autor:autores){
            System.out.println("Autor "+autor.getNombre()+" Apellidos "+autor.getLibros());
            List<Libro> libros=autor.getLibros();
            System.out.println(" Tiene "+libros.size());
            for (Libro libro:libros){
                System.out.println(libro.getTitulo()+" "+libro.getId()+" "+libro.getIsbn());
            }
        }
    }
    @Transactional
    public void solucionFetchJoin() {
        // Consulta que se traiga los autores y los libros
        System.out.println("Consulta con fecth");
        List<Autor> autores=autorRespositorio.findAllConLibros();
        System.out.println("autores: "+autores.size());
        System.out.println("Accedemos a los libros");
        for (Autor autor:autores){
            System.out.println("Autor "+autor.getNombre()+" Apellidos "+autor.getLibros());
            List<Libro> libros=autor.getLibros();
            System.out.println(" Tiene "+libros.size());
            for (Libro libro:libros){
                System.out.println(libro.getTitulo()+" "+libro.getId()+" "+libro.getIsbn());
            }
        }
    }
}
