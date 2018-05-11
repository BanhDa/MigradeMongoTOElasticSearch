/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.migrationmongotoes.worker.task;

import com.mycompany.migrationmongotoes.repo.mongodb.base.UserMongoRepo;

/**
 *
 * @author Vantu
 */
public class GetUserManager {
    
    private static final int NUMBER_THREAD = 1;
    
    public static void start(UserMongoRepo userMongoRepo) {
        for (int i = 0; i < NUMBER_THREAD; i++) {
            Thread inserUser = new Thread( new GetUserTask(userMongoRepo));
            inserUser.start();
        }
    }
}
