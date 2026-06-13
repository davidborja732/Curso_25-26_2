package org.iesch.ad.Ev2_Hotel.repository;

import org.iesch.ad.Ev2_Hotel.modelo.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClienteRepository extends MongoRepository<Cliente,String> {
    static Cliente findByDNI(String dniCliente) {
        return null;
    }
}
