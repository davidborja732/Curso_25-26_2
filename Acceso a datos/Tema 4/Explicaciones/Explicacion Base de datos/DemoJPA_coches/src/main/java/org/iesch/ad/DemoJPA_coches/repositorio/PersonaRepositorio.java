package org.iesch.ad.DemoJPA_coches.repositorio;

import org.iesch.ad.DemoJPA_coches.modelo.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepositorio extends JpaRepository<Persona,Long> {

}
