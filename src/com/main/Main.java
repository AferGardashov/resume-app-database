/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main;

import com.company.dao.*;
import com.company.dao.inter.AbstractDao;
import com.company.dao.inter.SkillDaoInter;
import com.company.entity.*;
import java.util.List;

/**
 *
 * @author user
 */
public class Main {

    public static void main(String[] args) throws Exception {
        
        SkillDao skill = new SkillDao();
        boolean b = skill.removeSkill("asojdasiod");
        System.out.println(b);
    }

}
