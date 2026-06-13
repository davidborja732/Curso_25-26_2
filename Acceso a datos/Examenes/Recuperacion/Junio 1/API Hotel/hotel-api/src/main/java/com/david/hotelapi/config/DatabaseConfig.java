package com.david.hotelapi.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class DatabaseConfig {

    // Lee el nombre de la base desde application.properties
    @Value("${spring.data.mongodb.database}")
    private String databaseName;

    // Lee la URI base (sin la base al final)
    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;

    @Bean
    public MongoClient mongoClient() {
        // Fuerza la conexión a la base exacta
        ConnectionString connectionString = new ConnectionString(mongoUri);

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(settings);
    }

    @Bean
    public MongoDatabaseFactory mongoDatabaseFactory() {
        // Aquí se fuerza la base de datos que debe usar Spring
        return new SimpleMongoClientDatabaseFactory(mongoClient(), databaseName);
    }
}
