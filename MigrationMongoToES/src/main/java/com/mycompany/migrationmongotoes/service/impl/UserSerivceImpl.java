/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.migrationmongotoes.service.impl;

import com.mycompany.migrationmongotoes.domain.mongo.User;
import com.mycompany.migrationmongotoes.repo.es.ManageESWorker;
import com.mycompany.migrationmongotoes.repo.es.base.UserESRepo;
import com.mycompany.migrationmongotoes.repo.mongodb.base.UserMongoRepo;
import com.mycompany.migrationmongotoes.service.base.UserService;
import com.mycompany.migrationmongotoes.utils.constant.Constant;
import com.mycompany.migrationmongotoes.utils.validator.StringValidator;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.elasticsearch.action.index.IndexRequest;
import org.springframework.stereotype.Service;

/**
 *
 * @author Vantu
 */
@Service
@AllArgsConstructor
public class UserSerivceImpl implements UserService{
    
    private final UserMongoRepo userMongoRepo;
    private final UserESRepo userESRepo;
    
    @Override
    public void insertUser(String userName) {
        if (StringValidator.validString(userName)) {
            userMongoRepo.insert(userName);
        }
    }
    
    @Override
    public void insertThreeMillionUserToMongoDB() {
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

    @Override
    public void insertThreeMillionUserToES() {
        long curentTime = System.currentTimeMillis();
        System.out.println("insert...");
        for (int i = 0; i < Constant.THREE_MILLION_USER; i++) {
            String userName = "user name " + i;
            User user = new User("" + i, userName);
//            System.out.println("number : " + i);
            insertUserToES(user);
        }
        System.out.println("time insert: " + (System.currentTimeMillis() - curentTime));
    }
    
    
    public static final String INDEX = "user";
    public static final String TYPE = "user";
    public static final String USER_NAME = "user_name";
    public void insertUserToES(User user) {
        if (user != null) {
            Map<String, Object> map = new HashMap<>();
            map.put(USER_NAME, user.getUserName());
            IndexRequest request =  new IndexRequest(INDEX, TYPE, user.getId()).source(map);
            ManageESWorker.getInstance().push(request);
        }
    }
    
}
