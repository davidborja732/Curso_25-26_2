package org.iesch.MongoDemo_Repository;

import org.iesch.MongoDemo_Repository.modelo.Book;
import org.iesch.MongoDemo_Repository.repositorio.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class MongoDemoRepositoryApplication  {


	public static void main(String[] args) {
		SpringApplication.run(MongoDemoRepositoryApplication.class, args);
	}


}
