/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.migrationmongotoes.repo.es.base;

import com.mycompany.migrationmongotoes.domain.mongo.User;

/**
 *
 * @author Vantu
 */
public interface UserESRepo {
    
    public void insert(User user);
}
