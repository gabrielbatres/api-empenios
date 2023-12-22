package com.npm.empenios.configuration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

/***
 * Bean de configuracion para MongoDB
 * @author Gabriel Batres
 */
@Configuration
public class MongoConfig {

    /**
     * Host de la bd
     */
    @Value("${spring.data.mongodb.uri}")
    private String uri;

    /**
     * Nombre de la base de datos
     */
    @Value("${spring.data.mongodb.database}")
    private String nameDB;

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(uri);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), getDatabaseName());
    }

    protected String getDatabaseName() {
        return nameDB;
    }
}
