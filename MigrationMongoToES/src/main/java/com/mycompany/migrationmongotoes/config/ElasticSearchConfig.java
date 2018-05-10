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
import org.elasticsearch.common.transport.InetSocketTransportAddress;
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
    private TransportClient getTransportClient() throws UnknownHostException {
        TransportClient client = null;
        try {
            // un-command this, if you have multiple node
//            TransportClient client1 = new PreBuiltTransportClient(Settings.EMPTY)
//                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("host1"), 9300))
//                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("host1"), 9300));

//            Settings setting = Settings.builder()
//                    .put("cluster.name", elasticPro.getProperty("cluster"))
//                    .put("client.transport.sniff", true).build();

            client = new PreBuiltTransportClient(Settings.EMPTY)
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), port));
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
        return client;
    }
}
