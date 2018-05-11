/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.migrationmongotoes.worker.task;

import com.mycompany.migrationmongotoes.repo.es.base.UserESRepo;

/**
 *
 * @author Vantu
 */
public class InsertUserESManager {
    
    private static final int NUMBER_THREAD = 10;
    
    public static void start(UserESRepo userESRepo) {
        for (int i = 0; i < NUMBER_THREAD; i++) {
            Thread inserUser = new Thread( new InsertUserESTask(userESRepo, "" + i));
            inserUser.start();
        }
        System.out.println("done start ***************************");
    }
}
