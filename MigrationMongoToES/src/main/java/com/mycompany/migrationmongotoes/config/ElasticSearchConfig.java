/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.migrationmongotoes.config;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author Vantu
 */
@Configuration
public class ElasticSearchConfig {
    
    @Value("${application.elasticsearch.host}")
    private String host;
    
    @Value("${application.elasticsearch.port}")
    private int port;
    
    @Bean
    public TransportClient getTransportClient() throws UnknownHostException {
        TransportClient client = null;
        try {
            client = new PreBuiltTransportClient(Settings.EMPTY)
                    .addTransportAddress(new TransportAddress(InetAddress.getByName(host), port));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return client;
    }
}
