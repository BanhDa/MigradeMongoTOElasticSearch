/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.migrationmongotoes.repo.mongodb;

import com.mongodb.DB;
import com.mongodb.DBCollection;

/**
 *
 * @author Vantu
 */
public abstract class AbstractMongoDB {
    
    protected DBCollection getCollection() {
        String collectionName = getCollectionName();
        return getDB().getCollection(collectionName);
    }
    
    protected abstract DB getDB();
    
    protected abstract String getCollectionName();
}
