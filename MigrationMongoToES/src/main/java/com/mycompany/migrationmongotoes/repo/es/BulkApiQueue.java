/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.migrationmongotoes.repo.es;

import java.util.concurrent.LinkedBlockingQueue;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.client.transport.TransportClient;

/**
 *
 * @author Vantu
 */
public class BulkApiQueue {
    
    private final LinkedBlockingQueue<DocWriteRequest> queue = new LinkedBlockingQueue<>();
    private long delayTime = 1000L;
    private BulkRequest bulkRequest = new BulkRequest();
    private TransportClient transportClient;
    private int maximumBumberOfBulkRequest = 5;
    
}
