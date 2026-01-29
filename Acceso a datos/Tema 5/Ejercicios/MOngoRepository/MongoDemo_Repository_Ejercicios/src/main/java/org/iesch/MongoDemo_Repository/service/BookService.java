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

    public  List<Book> findByCategoriaConteiningIgnoreCase(String categoriausuario) {
        Query query=new Query();
        // i en options ignora mayusculas y minusculas
        query.addCriteria(Criteria.where("categorias").regex(categoriausuario,"i"));
        return mongoTemplate.find(query, Book.class);
    }

    public List<Book> findByAutorConteiningIgnoreCase(String autorusu) {
        Query query=new Query();
        // i en options ignora mayusculas y minusculas
        query.addCriteria(Criteria.where("autores.nombre").is(autorusu));
        return mongoTemplate.find(query, Book.class);
    }

    public List<Book> findByPrecioRango(Double min, Double max) {
        Query query=new Query();
        // i en options ignora mayusculas y minusculas
        query.addCriteria(Criteria.where("precio").gte(min).lte(max));
        return mongoTemplate.find(query, Book.class);
    }

    public List<Book> findByAnio(int anio) {
        Query query=new Query();
        query.addCriteria(Criteria.where("anioPublicacion").gt(anio));
        return mongoTemplate.find(query, Book.class);
    }

    public List<Book> findByInfeanio(Double infe, int anio) {
        Query query=new Query();
        query.addCriteria(Criteria.where("anioPublicacion").lt(anio).and("precio").lt(infe));
        return mongoTemplate.find(query, Book.class);
    }
}
