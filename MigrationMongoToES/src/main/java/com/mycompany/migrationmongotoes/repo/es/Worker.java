/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.migrationmongotoes.repo.es;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.transport.TransportClient;

/**
 *
 * @author Vantu
 * @param <Request>
 */
public class Worker<Request extends DocWriteRequest> extends Thread{
    
    private final LinkedBlockingQueue<Request> queue = new LinkedBlockingQueue<>();
    
    private long pollQueueDelayTime = 100L;
    private long bulkRequestDelayTime = 10L;
    private long lastTimeExecuteBulkRequest = System.currentTimeMillis();
    private BulkRequest bulkRequest = new BulkRequest();
    private final TransportClient transportClient;
    private int maximumNumberOfBulkRequest = 1000;
    
    public Worker(TransportClient client) {
        this.transportClient = client;
    }
    
    @Override
    public void run() {
        long currentTime = System.currentTimeMillis();
        while (true) {
            try {
                Request request = queue.poll(pollQueueDelayTime, TimeUnit.MILLISECONDS);
                if (request != null) {
                    bulkRequest.add(request);
                }
                if (queue.isEmpty()) {
                    System.out.println("time provess: " + (System.currentTimeMillis() - currentTime));
                    currentTime = System.currentTimeMillis();
                }
                long duration = System.currentTimeMillis() - lastTimeExecuteBulkRequest;
                if ( duration >= bulkRequestDelayTime 
                        || bulkRequest.numberOfActions() >= maximumNumberOfBulkRequest) {
                    if ( bulkRequest.numberOfActions() > 0 ) {
                        System.out.println("estimatedSizeInBytes: " + bulkRequest.estimatedSizeInBytes());
                        System.out.println("numberOfActions: " + bulkRequest.numberOfActions());
                        BulkResponse response = transportClient.bulk(bulkRequest).actionGet();
                        if (response.hasFailures()) {
                            System.out.println("UPDATE ELASTICSEARCH FAILED: " + response.buildFailureMessage());
                        }
                        System.out.println("duration: " + duration);
                        bulkRequest = new BulkRequest();
                        lastTimeExecuteBulkRequest = System.currentTimeMillis();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void push(Request request) {
        if (request != null) {
            queue.add(request);
        }
    }
    
    public void setPollQueueDelayTime(long pollQueueDelayTime) {
        this.pollQueueDelayTime = pollQueueDelayTime;
    }

    public void setBulkRequestDelayTime(long bulkRequestDelayTime) {
        this.bulkRequestDelayTime = bulkRequestDelayTime;
    }

    public void setLastTimeExecuteBulkRequest(long lastTimeExecuteBulkRequest) {
        this.lastTimeExecuteBulkRequest = lastTimeExecuteBulkRequest;
    }

    public void setMaximumNumberOfBulkRequest(int maximumNumberOfBulkRequest) {
        this.maximumNumberOfBulkRequest = maximumNumberOfBulkRequest;
    }

    public long getPollQueueDelayTime() {
        return pollQueueDelayTime;
    }

    public long getBulkRequestDelayTime() {
        return bulkRequestDelayTime;
    }

    public long getLastTimeExecuteBulkRequest() {
        return lastTimeExecuteBulkRequest;
    }

    public BulkRequest getBulkRequest() {
        return bulkRequest;
    }

    public TransportClient getTransportClient() {
        return transportClient;
    }

    public int getMaximumNumberOfBulkRequest() {
        return maximumNumberOfBulkRequest;
    }
    
    
    
}
