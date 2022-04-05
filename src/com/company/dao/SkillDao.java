/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao;

import com.company.dao.inter.AbstractDao;
import com.company.dao.inter.SkillDaoInter;
import com.company.entity.Skill;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class SkillDao extends AbstractDao implements SkillDaoInter {

    private Skill getSkill(ResultSet rs) {
        try {
            int id = rs.getInt("id");
            String name = rs.getString("name");

            Skill skill = new Skill(id, name);
            return skill;
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public List<Skill> getAllSkill() {
        List<Skill> list = new ArrayList<>();

        try (Connection c = connect()) {
            Statement stmt = c.createStatement();
            stmt.execute("select * from skill");

            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                Skill s = getSkill(rs);

                list.add(s);
            }

        } catch (Exception ex) {

        }
        return list;
    }
    
    @Override
    public boolean addSkill(String skillName) {
        try(Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("insert into skill (name) values (?)");
            stmt.setString(1, skillName);
            
            return stmt.execute();
            
        } catch (Exception e) {
            return false;
        }
    }
    
     @Override
    public boolean removeSkill(String skillName) {
        try(Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("delete from skill where name = ?");
            stmt.setString(1, skillName);
            return stmt.execute();
            
        } catch (Exception e) {
            return false;
        }
    }

}
