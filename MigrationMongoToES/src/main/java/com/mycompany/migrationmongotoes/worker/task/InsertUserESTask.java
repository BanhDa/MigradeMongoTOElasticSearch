/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.migrationmongotoes.worker.task;

import com.mycompany.migrationmongotoes.domain.mongo.User;
import com.mycompany.migrationmongotoes.repo.es.base.UserESRepo;
import com.mycompany.migrationmongotoes.worker.container.UserContainer;
import lombok.AllArgsConstructor;

/**
 *
 * @author Vantu
 */
@AllArgsConstructor
public class InsertUserESTask implements Runnable{

    private final UserESRepo userESRepo;
    private final String name;
    
    @Override
    public void run() {
        long current  = System.currentTimeMillis();
        while (true) {            
            User user = UserContainer.getInstance().dequeue();
            if (user != null) {
                if (user.getUserName() != null) {
                    userESRepo.insert(user);
                    System.out.println("InsertUserESTask number: " + name +" user :" + user.getUserName());
                } else {
                    break;
                }
            }
        }
        System.out.println("time : " + (System.currentTimeMillis() - current));
    }
    
}
