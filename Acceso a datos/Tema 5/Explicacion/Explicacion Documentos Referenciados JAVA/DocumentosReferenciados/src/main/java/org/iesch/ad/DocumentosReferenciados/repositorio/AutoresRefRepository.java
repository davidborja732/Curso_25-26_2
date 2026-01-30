package org.iesch.ad.DocumentosReferenciados.repositorio;

import org.iesch.ad.DocumentosReferenciados.modelo.AutoresREF;
import org.jspecify.annotations.Nullable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutoresRefRepository extends MongoRepository<AutoresREF,String> {
}
