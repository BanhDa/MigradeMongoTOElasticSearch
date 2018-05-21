/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.migrationmongotoes.repo.mongodb.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mycompany.migrationmongotoes.domain.mongo.User;
import com.mycompany.migrationmongotoes.repo.es.ManageESWorker;
import com.mycompany.migrationmongotoes.repo.mongodb.AbstractMongoDB;
import com.mycompany.migrationmongotoes.repo.mongodb.base.UserMongoRepo;
import static com.mycompany.migrationmongotoes.service.impl.UserSerivceImpl.INDEX;
import static com.mycompany.migrationmongotoes.service.impl.UserSerivceImpl.TYPE;
import static com.mycompany.migrationmongotoes.service.impl.UserSerivceImpl.USER_NAME;
import com.mycompany.migrationmongotoes.worker.container.UserContainer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.elasticsearch.action.index.IndexRequest;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Vantu
 */
@Repository
@AllArgsConstructor
public class UserMongoRepoImpl extends AbstractMongoDB implements UserMongoRepo<User>{

    public static final String DB_NAME = "userdbtest";
    public static final String COLLECTION_NAME = "user";
    public static final String USER_NAME = "user_name";
    public static final String ID = "_id";
    
    private final MongoClient client;
    
    @Override
    public void insert(String userName) {
        DBObject insertObj = new BasicDBObject(USER_NAME, userName);
        getCollection().insert(insertObj);
    }

    @Override
    public List<User> getAll() {
        List<User> results = new ArrayList<>();
        
        try (DBCursor cursor = getCollection().find()) {
            System.out.println("cursor size: " + cursor.size());
            while (cursor.hasNext()) {                
                DBObject dBObject = cursor.next();
                User user = castToObject(dBObject);
                if (user != null) {
                    results.add(user);
                }
            }
        }
        
        return results;
    }
    
    private User castToObject(DBObject dBObject) {
        User result = null;
        
        if (dBObject != null) {
            ObjectId id = (ObjectId) dBObject.get(ID);
            String userName = (String) dBObject.get(USER_NAME);
            result = new User(id.toString(), userName);
        }
        
        return result;
    }
    
    @Override
    public long getNumberUser() {
        return getCollection().count();
    }
    
    @Override
    protected DB getDB() {
        return client.getDB(DB_NAME);
    }

    @Override
    protected String getCollectionName() {
        return COLLECTION_NAME;
    }

    @Override
    public void initToQueue() {
        try (DBCursor cursor = getCollection().find()) {
            System.out.println("cursor size: " + cursor.size());
            while (cursor.hasNext()) {                
                DBObject dBObject = cursor.next();
                User user = castToObject(dBObject);
                if (user != null) {
                    Map<String, Object> map = new HashMap<>();
                    map.put(USER_NAME, user.getUserName());
                    IndexRequest request =  new IndexRequest(INDEX, TYPE, user.getId()).source(map);
                    ManageESWorker.getInstance().push(request);
                }
            }
        }
    }

}
