/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main;

import com.company.dao.EmploymentHistoryDao;
import com.company.dao.SkillDao;
import com.company.dao.UserDao;
import com.company.dao.UserSkillDao;
import com.company.dao.inter.EmploymentHistoryDaoInter;
import com.company.dao.inter.SkillDaoInter;
import com.company.dao.inter.UserDaoInter;
import com.company.dao.inter.UserSkillDaoInter;

/**
 *
 * @author user
 */
public class Context {
    public static UserDaoInter instanceUserDao(){
        return new UserDao();
    }
    public static UserSkillDaoInter instacnceUserSkillDao(){
        return new UserSkillDao();
    }
    public static EmploymentHistoryDaoInter instanceEmploymentHistoryDao(){
        return new EmploymentHistoryDao();
    }
    public static SkillDaoInter instacnceSkillDao(){
        return new SkillDao();
        
    }
}
