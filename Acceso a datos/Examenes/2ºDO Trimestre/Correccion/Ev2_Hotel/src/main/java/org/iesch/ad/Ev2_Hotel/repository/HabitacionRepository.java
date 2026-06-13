package org.iesch.ad.Ev2_Hotel.repository;

import org.iesch.ad.Ev2_Hotel.modelo.Habitacion;
import org.iesch.ad.Ev2_Hotel.modelo.enums.EstadoHabitacion;
import org.iesch.ad.Ev2_Hotel.modelo.enums.TipoHabitacion;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface HabitacionRepository extends MongoRepository<Habitacion,String> {
    List<Habitacion> findByTipoHabitacionAndEstado(TipoHabitacion tipoAsignado, EstadoHabitacion estadoHabitacion);
}
