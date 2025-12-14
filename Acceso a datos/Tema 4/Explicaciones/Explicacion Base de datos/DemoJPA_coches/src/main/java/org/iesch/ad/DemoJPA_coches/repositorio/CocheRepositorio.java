package org.iesch.ad.DemoJPA_coches.repositorio;

import org.iesch.ad.DemoJPA_coches.modelo.Coche;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CocheRepositorio extends JpaRepository<Coche, Long> {
    /*
    @Query("SELECT c FROM Coche c where c.color = :color")
    List<Coche> busquedaPorColor(@Param("color") String color);
    Consulta HQL
    */
    /*
    @NativeQuery("SELECT * FROM Coche c where c.color = :color")
    List<Coche> busquedaPorColor(@Param("color") String color);
    Forma de hacerlo con lenguaje sql
     */
    // Forma de hacerlo mas directo
    List<Coche> findbyPotencia(Integer Potencia);
    List<Coche> findByColor(String color);
    List<Coche> findByColorAndMarca(String color,String marca);
    List<Coche> findByColorAndMarcaAndPotenciaLessThan(String color,String marca,int potencia);

}
