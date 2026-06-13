package org.iesch.MongoDemo_Repository.service;


import org.iesch.MongoDemo_Repository.modelo.Book;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookService {
    @Autowired
    MongoTemplate mongoTemplate;

    public List<Book> findByTituloConteiningIgnoreCase(String titulo) {
        // Esta es la importacion correcta import org.springframework.data.mongodb.core.query.Query;
        Query query=new Query();
        // i en options ignora mayusculas y minusculas
        query.addCriteria(Criteria.where("titulo").regex(titulo,"i"));
        return mongoTemplate.find(query, Book.class);
    }
}
