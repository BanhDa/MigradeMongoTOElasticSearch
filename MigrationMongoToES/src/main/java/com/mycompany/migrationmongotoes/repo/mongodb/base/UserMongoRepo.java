/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.migrationmongotoes.repo.mongodb.base;

import com.mycompany.migrationmongotoes.domain.AbstractEntity;

/**
 *
 * @author Vantu
 * @param <Entity>
 */
public interface UserMongoRepo<Entity extends AbstractEntity> {
    
    public void insert(Entity entity, Class<Entity> typeClass);
}
