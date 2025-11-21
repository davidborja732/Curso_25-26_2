package org.iesch.ad.NconsultasYmas.repositorio;

import org.iesch.ad.NconsultasYmas.modelo.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRespositorio extends JpaRepository<Autor,Long> {
    @Query("SELECT DISTINCT a FROM Autor a LEFT JOIN FETCH a.libros")
    List<Autor> findAllConLibros();
}
