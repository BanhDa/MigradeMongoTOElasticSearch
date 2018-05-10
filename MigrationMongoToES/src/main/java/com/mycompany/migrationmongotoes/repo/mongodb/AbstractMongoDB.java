/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.migrationmongotoes.repo.mongodb;

import com.mongodb.DB;

/**
 *
 * @author Vantu
 */
public abstract class AbstractMongoDB {
    
    /**
     *
     * @param dbName
     * @return
     */
    protected abstract DB getDB(String dbName);
    
}
