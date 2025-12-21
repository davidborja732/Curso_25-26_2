package org.iesch.ad.Ev1_Ej3.repository;

import org.iesch.ad.Ev1_Ej3.model.Cliente;
import org.iesch.ad.Ev1_Ej3.model.Libro;
import org.iesch.ad.Ev1_Ej3.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LibrosRepository extends JpaRepository<Libro, Long> {
    Optional<Libro> findByEditorial(String fecha);

}
