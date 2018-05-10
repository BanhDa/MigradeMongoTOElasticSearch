/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.migrationmongotoes.config;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Vantu
 */
@Configuration
public class MongoConfig {
    
    @Value("${application.mongo.host}")
    private String host;
    
    @Value("${application.mongo.port}")
    private int port;
    
    @Bean
    public MongoClient getMongoClient() {
        return new MongoClient(host, port);
    }
    
}
