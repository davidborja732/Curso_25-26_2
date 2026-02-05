package org.iesch.ad.DocumentosReferenciados.repositorio;

import org.iesch.ad.DocumentosReferenciados.modelo.BookRef;
import org.jspecify.annotations.Nullable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRefRepository extends MongoRepository<BookRef,String> {
    List<BookRef> findByAutoresId(String id);

    // Consulta con @Query
    // Buscar libros ocn precio inferior a x y año de publicacion a y
    @Query("{'precio':{$lt:?0}, 'anioPublicacion':{$gte:?1}}")
    List<BookRef> buscarPorPrecioInferiorYAnioSuperior(Double precio,Integer anio);

    @Query("{$or :[{'precio':{$lt:?0}}, {'anioPublicacion':{$lt:?1}}]}")
    List<BookRef> buscarPorPrecioInferiorYAnioInferior(Double precio, Integer anio);
}
