package org.iesch.ad.Ev2_Hotel.repository;

import org.iesch.ad.Ev2_Hotel.modelo.Reserva;
import org.iesch.ad.Ev2_Hotel.modelo.enums.EstadoReserva;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReservasRepository extends MongoRepository<Reserva,String> {
    List<Reserva> findByNumeroHabitacionAndEstadoIn(String numero, List<EstadoReserva> estadoActivos);
}
