/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.migrationmongotoes.repo.es;

import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.transport.TransportClient;

/**
 *
 * @author Vantu
 */
public class ManageESWorker {
    
    private static final ManageESWorker INSTANCE = new ManageESWorker();
    private static final int NUMBER_WORKER = 1;
    
    private static final int INDEX_INSERT_WORKER = 0;
    private final Worker[] workers = new Worker[NUMBER_WORKER];
    
    public static ManageESWorker getInstance() {
        return INSTANCE;
    }
    
    public void init(TransportClient client) {
        InsertWorker insertWorker = new InsertWorker(client);
        insertWorker.start();
        workers[INDEX_INSERT_WORKER] = insertWorker;
    }
    
    public void push(DocWriteRequest request) {
        if (request != null) {
            if (request instanceof IndexRequest) {
                workers[INDEX_INSERT_WORKER].push(request);
            }
        }
    }
}
