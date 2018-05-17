/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.migrationmongotoes.repo.es;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.transport.TransportClient;

/**
 *
 * @author Vantu
 */
public class InsertWorker extends Worker<IndexRequest>{
    
    public InsertWorker(TransportClient client) {
        super(client);
    }
    
}
