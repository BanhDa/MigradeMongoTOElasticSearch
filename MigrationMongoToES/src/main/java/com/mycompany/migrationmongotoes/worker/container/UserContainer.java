/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.migrationmongotoes.worker.container;

import com.mycompany.migrationmongotoes.domain.mongo.User;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author Vantu
 */
public class UserContainer {
    
    private final Queue<User> userQueue = new LinkedBlockingQueue<>();
    
    private static final UserContainer INSTANCE = new UserContainer();
    
    public static UserContainer getInstance() {
        return INSTANCE;
    }
    
    public User dequeue() {
        User result = userQueue.peek();
        if (result != null) {
            if (result.getUserName() != null) {
                    result = userQueue.poll();
            }
        }
        return result;
    }
    
    public void enQueue(User user) {
        if (user != null) {
            System.out.println("enQueue : " + user.getUserName());
//            synchronized (userQueue) {
                userQueue.add(user);
//            }
        }
    }
}
