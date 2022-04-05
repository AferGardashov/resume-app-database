/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.inter;

import com.company.entity.UserSkill;
import java.util.List;

/**
 *
 * @author user
 */
public interface UserSkillDaoInter {

    public List<UserSkill> getAllSkillbyUserId(int userId);

}
