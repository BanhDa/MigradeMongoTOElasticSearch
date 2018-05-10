/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.migrationmongotoes.utils.validator;

/**
 *
 * @author Vantu
 */
public class StringValidator {
    
    public static boolean validString(String param) {
        return param != null && !param.trim().isEmpty();
    }
    
    public static boolean validStrings(String... params) {
        for (String param : params) {
            if (!validString(param)) {
                return false;
            }
        }
        return true;
    }
}
