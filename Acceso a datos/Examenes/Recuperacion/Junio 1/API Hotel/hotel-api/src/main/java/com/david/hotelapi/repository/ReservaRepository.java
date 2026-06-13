package com.david.hotelapi.repository;

import com.david.hotelapi.model.Reserva;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends MongoRepository<Reserva, String> {

    // Método para detectar solapamientos de reservas
    boolean existsByNumeroHabitacionAndFechaEntradaLessThanEqualAndFechaSalidaGreaterThanEqual(
            String numeroHabitacion,
            String fechaEntrada,
            String fechaSalida
    );
}
