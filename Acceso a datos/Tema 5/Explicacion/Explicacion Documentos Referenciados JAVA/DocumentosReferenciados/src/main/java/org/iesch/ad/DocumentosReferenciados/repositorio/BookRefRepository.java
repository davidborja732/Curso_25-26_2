package org.iesch.ad.DocumentosReferenciados.repositorio;

import org.iesch.ad.DocumentosReferenciados.modelo.BookRef;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRefRepository extends MongoRepository<BookRef,String> {
}
