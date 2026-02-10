package org.iesch.dgt.Practica_DGT.repository;

import org.iesch.dgt.Practica_DGT.modelos.Vehiculo;
import org.iesch.dgt.Practica_DGT.modelos.enums.EstadoVehiculo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface VehiculoRepository extends MongoRepository<Vehiculo, String> {

    Optional<Vehiculo> findByMatricula(String matricula);

    boolean existsByMatricula(String matricula);

    boolean existsByBastidor(String bastidor);

    List<Vehiculo> findByTitular_Dni(String dni);

    List<Vehiculo> findBySituacionAdministrativa_Estado(EstadoVehiculo estado);

    List<Vehiculo> findByItv_EnVigorFalseOrItv_FechaCaducidadBefore(java.time.LocalDateTime fecha);

    List<Vehiculo> findByMultas_Estado(String estado); // luego podemos refinar
}
