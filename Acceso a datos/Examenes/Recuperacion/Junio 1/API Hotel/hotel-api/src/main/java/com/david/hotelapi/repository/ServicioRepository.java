package com.david.hotelapi.repository;

import com.david.hotelapi.model.Servicio;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicioRepository extends MongoRepository<Servicio, String> {
}
