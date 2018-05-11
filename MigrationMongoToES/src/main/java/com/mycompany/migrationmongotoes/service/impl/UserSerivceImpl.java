/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.migrationmongotoes.service.impl;

import com.mycompany.migrationmongotoes.repo.mongodb.base.UserMongoRepo;
import com.mycompany.migrationmongotoes.service.base.UserService;
import com.mycompany.migrationmongotoes.utils.constant.Constant;
import com.mycompany.migrationmongotoes.utils.validator.StringValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *
 * @author Vantu
 */
@Service
@AllArgsConstructor
public class UserSerivceImpl implements UserService{
    
    private final UserMongoRepo userMongoRepo;
    
    @Override
    public void insertUser(String userName) {
        if (StringValidator.validString(userName)) {
            userMongoRepo.insert(userName);
        }
    }
    
    @Override
    public void insertThreeMillionUser() {
        long curentTime = System.currentTimeMillis();
        System.out.println("insert...");
        for (int i = 0; i < Constant.THREE_MILLION_USER; i++) {
            String userName = "user name " + i;
            insertUser(userName);
        }
        System.out.println("time insert: " + (System.currentTimeMillis() - curentTime));
    }

    @Override
    public long getNumberUser() {
        return userMongoRepo.getNumberUser();
    }
    
}
