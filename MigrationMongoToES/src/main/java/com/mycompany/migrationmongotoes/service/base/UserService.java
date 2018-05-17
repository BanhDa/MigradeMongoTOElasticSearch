/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.migrationmongotoes.service.base;

/**
 *
 * @author Vantu
 */
public interface UserService {
    
    public void insertUser(String userName);
    
    public void insertThreeMillionUserToMongoDB();
    
    public void insertThreeMillionUserToES();
    
    public long getNumberUser();
}
