/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao;

import com.company.dao.inter.UserSkillDaoInter;
import com.company.dao.inter.AbstractDao;
import com.company.entity.Skill;
import com.company.entity.User;
import com.company.entity.UserSkill;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class UserSkillDao extends AbstractDao implements UserSkillDaoInter {
    
    private UserSkill getUserSkill(ResultSet rs) {
        try {
            int id = rs.getInt("id");
            int userId = rs.getInt("user_id");
            String skillName = rs.getString("skill_name");
            int skillId = rs.getInt("skill_id");
            int power = rs.getInt("power");

            User user = new User(userId);
            Skill skill = new Skill(skillId, skillName);

            return new UserSkill(id, user, skill, power);

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<UserSkill> getAllSkillbyUserId(int userId) {
        List<UserSkill> list = new ArrayList<>();
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("SELECT "
                     + "	u.id,"
                     + "	us.skill_id,"
                     + "        us.user_id,"
                     + "	s.NAME AS skill_name,"
                     + "	us.power "
                     + "        FROM "
                     + "	user_skill us "
                     + "	LEFT JOIN user u ON us.user_id = u.id "
                     + "	LEFT JOIN skill s ON us.skill_id = s.id"
                     + "        WHERE us.user_id = ?");

            stmt.setInt(1, userId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                UserSkill u = getUserSkill(rs);
                list.add(u);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

}
