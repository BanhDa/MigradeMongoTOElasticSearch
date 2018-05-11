/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.migrationmongotoes.repo.mongodb.base;

import com.mycompany.migrationmongotoes.domain.AbstractEntity;
import com.mycompany.migrationmongotoes.domain.mongo.User;
import java.util.List;

/**
 *
 * @author Vantu
 * @param <Entity>
 */
public interface UserMongoRepo<Entity extends AbstractEntity> {
    
    public void insert(String userName);
    
    public List<User> getAll();
    
    public long getNumberUser();
    
    public void initToQueue();
}
