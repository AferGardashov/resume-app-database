package com.company.dao;

import com.company.dao.inter.EmploymentHistoryDaoInter;
import com.company.dao.inter.AbstractDao;
import com.company.entity.EmploymentHistory;
import com.company.entity.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class EmploymentHistoryDao extends AbstractDao implements EmploymentHistoryDaoInter {

    private EmploymentHistory getEmploymentHistroy(ResultSet rs) {
        try {
            int id = rs.getInt("id");
            String header = rs.getString("header");
            Date beginDate = rs.getDate("begin_date");
            Date endDate = rs.getDate("end_date");
            String jobDescription = rs.getString("job_description");
            int userId = rs.getInt("user_id");
            EmploymentHistory eh = new EmploymentHistory(null, header, beginDate, endDate, jobDescription, new User(userId));

            return eh;
        } catch (Exception ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public List<EmploymentHistory> getAllEmploymentHistorybyUserId(int userId) {
        List<EmploymentHistory> list = new ArrayList<>();
        try (Connection c = connect()) {
            
            PreparedStatement stmt = c.prepareStatement("select * from employment_history where user_id = ?");
            stmt.setInt(1, userId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                EmploymentHistory eh = getEmploymentHistroy(rs);
                list.add(eh);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

}
