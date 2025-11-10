package org.iesch.ad.DemoJPA_coches.repositorio;

import org.iesch.ad.DemoJPA_coches.modelo.Coche;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CocheRepositorio extends JpaRepository<Coche, Long> {

}
