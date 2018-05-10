/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.migrationmongotoes.service.impl;

import com.mycompany.migrationmongotoes.repo.mongodb.base.UserMongoRepo;
import com.mycompany.migrationmongotoes.service.base.UserService;
import com.mycompany.migrationmongotoes.utils.validator.StringValidator;
import lombok.AllArgsConstructor;

/**
 *
 * @author Vantu
 */
@AllArgsConstructor
public class UserSerivceImpl implements UserService{
    
    private final UserMongoRepo userMongoRepo;
    
    @Override
    public void createData(String userName) {
        if (StringValidator.validString(userName)) {
            
        }
    }
    
}
