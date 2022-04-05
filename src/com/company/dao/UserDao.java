/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao;

import com.company.dao.inter.UserDaoInter;
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
public class UserDao extends AbstractDao implements UserDaoInter {

    private User getUser(ResultSet rs) {
        try {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String surname = rs.getString("surname");
            String email = rs.getString("email");
            String phone = rs.getString("phone");
            String desc = rs.getString("profile_description");
            String adress = rs.getString("adress");
            String birthplace = rs.getString("birthplace");
            Date birthdate = rs.getDate("birthdate");

            return new User(id, name, surname, email, phone, desc, adress, birthplace, birthdate);
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<User> getAllUser() {
        List<User> list = new ArrayList<>();
        try (Connection c = connect()) {
            Statement stmt = c.createStatement();
            stmt.execute("select * from user");
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                User u = getUser(rs);
                list.add(u);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;

    }

    @Override
    public User getbyID(int userId) {
        User u = null;
        try (Connection c = connect()) {
            Statement stmt = c.createStatement();
            stmt.execute("select * from user where id=" + userId);
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                u = getUser(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return u;
    }

    @Override
    public boolean removeUser(int id) {
        try (Connection c = connect()) {
            Statement stmt = c.createStatement();
            return stmt.execute("delete from user where id=" + id);

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean addUser(User u) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("insert into user (name, surname, email, phone) values (?,?,?,?)");

            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getEmail());
            stmt.setString(4, u.getPhone());

            return stmt.execute();

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateUser(User u) {
        try (Connection c = connect()) {
            int id = u.getId();
            PreparedStatement stmt = c.prepareStatement("update user set id=?, name=?, surname=?, email=?, phone=? where id=?");
            stmt.setInt(1, u.getId());
            stmt.setString(2, u.getName());
            stmt.setString(3, u.getSurname());
            stmt.setString(4, u.getEmail());
            stmt.setString(5, u.getPhone());
            stmt.setInt(6, u.getId());
            return stmt.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean updateId(int id, int newId) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("update user set id=? where id=?");
            stmt.setInt(1, newId);
            stmt.setInt(2, id);
            return stmt.execute();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

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
