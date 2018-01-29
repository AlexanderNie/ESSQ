/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.welearn.domain.entities;

import java.util.HashMap;

/**
 *
 * @author weiwei2017
 */
public class UserList {

    /**
     * @return the users
     */
    public  HashMap<String,String> getUsers() {
        return users;
    }

    /**
     * @param aUsers the users to set
     */
    public  void setUsers(HashMap<String,String> aUsers) {
        users = aUsers;
    }
    // user name and password
    private  HashMap<String,String> users =new HashMap<String,String>();

    public UserList() {
        users.put("tom","password1234");
        users.put("alex","password1234");
        users.put("skylar","password1234");
        users.put("justin","password1234");
    }
    
    
}
