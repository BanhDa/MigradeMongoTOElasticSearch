/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.migrationmongotoes;

import com.mycompany.migrationmongotoes.service.base.MigrationService;
import com.mycompany.migrationmongotoes.service.base.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author Vantu
 */
@SpringBootApplication
@AllArgsConstructor
public class Main implements CommandLineRunner{
    
    private final UserService userService;
    private final MigrationService migrationService;
    
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
//        userService.insertThreeMillionUser();
        System.out.println("start migrade");
//        migrationService.migradeSynchronize();
        migrationService.migradeAsynchronize();
    }
}
