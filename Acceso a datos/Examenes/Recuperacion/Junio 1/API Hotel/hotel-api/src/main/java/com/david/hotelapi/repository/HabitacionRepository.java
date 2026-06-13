package com.david.hotelapi.repository;

import com.david.hotelapi.model.Habitacion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabitacionRepository extends MongoRepository<Habitacion, String> {
}
