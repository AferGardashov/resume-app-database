/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.inter;

import com.company.entity.User;
import com.company.entity.UserSkill;
import java.util.List;

/**
 *
 * @author user
 */
public interface  UserDaoInter {
    
    public List<User> getAllUser();
    
    public User getbyID(int userId);
    
    public boolean removeUser(int id);
    
    public boolean updateUser(User u);
    
    public boolean addUser(User u);
    
    public boolean updateId(int id, int newId);
 
}
