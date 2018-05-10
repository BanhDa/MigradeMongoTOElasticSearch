/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.migrationmongotoes.repo.mongodb.impl;

import com.mycompany.migrationmongotoes.domain.mongo.User;
import com.mycompany.migrationmongotoes.repo.mongodb.base.UserMongoRepo;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Vantu
 */
@Repository
public class UserMongoRepoImpl implements UserMongoRepo<User>{

    @Override
    public void insert(User entity, Class<User> typeClass) {
        
    }
    
}
