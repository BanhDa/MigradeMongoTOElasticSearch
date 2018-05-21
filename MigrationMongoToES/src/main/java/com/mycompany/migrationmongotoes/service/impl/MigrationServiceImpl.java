/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.migrationmongotoes.service.impl;

import com.mycompany.migrationmongotoes.domain.mongo.User;
import com.mycompany.migrationmongotoes.repo.es.base.UserESRepo;
import com.mycompany.migrationmongotoes.repo.mongodb.base.UserMongoRepo;
import com.mycompany.migrationmongotoes.service.base.MigrationService;
import com.mycompany.migrationmongotoes.worker.task.GetUserManager;
import com.mycompany.migrationmongotoes.worker.task.InsertUserESManager;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *
 * @author Vantu
 */
@Service
@AllArgsConstructor
public class MigrationServiceImpl implements MigrationService{

    private final UserMongoRepo userMongoRepo;
    private final UserESRepo userESRepo;
    
    @Override
    public void migradeSynchronize() {
        System.out.println("migrade synchronize ");
        long currentTime = System.currentTimeMillis();
        List<User> users = userMongoRepo.getAll();
        System.out.println("time get user from mongodb: " + (System.currentTimeMillis() - currentTime));
        currentTime = System.currentTimeMillis();
        int count = 0;
        System.out.println("start insert to ES");
        for (User user : users) {
            userESRepo.insert(user);
//            count++;
//            System.out.println("insert number user: " + count);
        }
        System.out.println("time insert user to ES: " + (System.currentTimeMillis() - currentTime));
    }

    @Override
    public void migradeAsynchronize() {
        System.out.println("migrade asynchronize ");
        long current = System.currentTimeMillis();
        
        GetUserManager.start(userMongoRepo);
        System.out.println("time done: " + (System.currentTimeMillis() - current));
        
    }
    
}
