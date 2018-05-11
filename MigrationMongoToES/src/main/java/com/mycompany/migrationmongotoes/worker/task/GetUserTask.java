/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.migrationmongotoes.worker.task;

import com.mycompany.migrationmongotoes.repo.mongodb.base.UserMongoRepo;
import lombok.AllArgsConstructor;

/**
 *
 * @author Vantu
 */
@AllArgsConstructor
public class GetUserTask implements Runnable{

    private final UserMongoRepo userMongoRepo;
    
    @Override
    public void run() {
        long current = System.currentTimeMillis();
        userMongoRepo.initToQueue();
        System.out.println("time get user: " + (System.currentTimeMillis() - current));
    }
    
}
