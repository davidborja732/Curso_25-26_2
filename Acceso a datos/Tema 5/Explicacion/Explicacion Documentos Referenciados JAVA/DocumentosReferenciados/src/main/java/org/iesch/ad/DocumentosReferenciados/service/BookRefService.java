package org.iesch.ad.DocumentosReferenciados.service;

import org.bson.types.ObjectId;
import org.iesch.ad.DocumentosReferenciados.modelo.BookRef;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;


import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookRefService {
    @Autowired
    private MongoTemplate mongoTemplate;
    public @Nullable List<BookRef> buscarTodos() {
        return  mongoTemplate.findAll(BookRef.class);
    }
    public @Nullable List<BookRef> buscarTitulo(String titulo) {
        Query query=new Query();
        query.addCriteria(Criteria.where("titulo").regex(titulo,"i"));
        return mongoTemplate.find(query, BookRef.class);
    }

    public @Nullable Object buscarAutor(String id) {
        Query query=new Query();
        query.addCriteria(Criteria.where("autores.id").is(new ObjectId(id)));
        return mongoTemplate.find(query, BookRef.class);
    }

    public @Nullable Object buscarLibro(String id) {
        return mongoTemplate.findById(id, BookRef.class);
    }

    public @Nullable Object buscarAutorLook(String nombre) {
        // lookUP, luego un match
        LookupOperation lookupOperation=LookupOperation.newLookup()
                .from("autores")
                .localField("autores")
                .foreignField("_id")
                .as("autoresData");
        MatchOperation matchOperation= Aggregation.match(
                Criteria.where("autoresData.nombre").regex(nombre,"i")
        );
        Aggregation aggregation=Aggregation.newAggregation(lookupOperation,matchOperation);
        AggregationResults<BookRef> results=mongoTemplate.aggregate(
                aggregation,"libros_ref", BookRef.class
        );
        return results.getMappedResults();

    }
}
