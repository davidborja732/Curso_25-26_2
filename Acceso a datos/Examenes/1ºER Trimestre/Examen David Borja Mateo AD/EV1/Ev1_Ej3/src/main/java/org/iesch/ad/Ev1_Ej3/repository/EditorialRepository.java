package org.iesch.ad.Ev1_Ej3.repository;

import org.iesch.ad.Ev1_Ej3.model.Editorial;
import org.iesch.ad.Ev1_Ej3.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EditorialRepository extends JpaRepository<Editorial, Long>{
    Optional<Editorial> findByEditorial();
}
