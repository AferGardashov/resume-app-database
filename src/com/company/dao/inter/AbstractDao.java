/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.inter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author user
 */
public abstract class AbstractDao {

    public Connection connect() {
        try {
//        Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/resume";
            String username = "root";
            String password = "tele4vizor";
            Connection c = DriverManager.getConnection(url, username, password);
            return c;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
