/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.migrationmongotoes.repo.es.impl;

import com.mycompany.migrationmongotoes.domain.mongo.User;
import org.springframework.stereotype.Repository;
import com.mycompany.migrationmongotoes.repo.es.base.UserESRepo;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.elasticsearch.client.transport.TransportClient;

/**
 *
 * @author Vantu
 */
@Repository
@AllArgsConstructor
public class UserESRepoImpl implements UserESRepo{

    public static final String INDEX = "user";
    public static final String TYPE = "user";
    
    public static final String USER_NAME = "user_name";
    
    private final TransportClient client;
    
    @Override
    public void insert(User user) {
        Map<String, Object> userMap = new HashMap<>();
        userMap.put(USER_NAME, user.getUserName());
        long current = System.currentTimeMillis();
        client.prepareIndex().setIndex(INDEX).setType(TYPE).setId(user.getId()).setSource(userMap).execute().actionGet();
        System.out.println("time inset: " + (System.currentTimeMillis() - current));
    }
    
}
