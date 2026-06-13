package org.iesch.ad.NconsultasYmas.repositorio;

import org.iesch.ad.NconsultasYmas.modelo.Autor;
import org.iesch.ad.NconsultasYmas.modelo.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRespositorio extends JpaRepository<Libro,Long> {

}
