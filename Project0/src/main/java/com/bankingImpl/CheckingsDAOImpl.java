package com.bankingImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bankingDAO.CheckingsDAO;
import com.bankingmodels.Checkings;
import com.bankingmodels.Customer;
import com.bankingutility.ConnectionFactory;

public class CheckingsDAOImpl implements CheckingsDAO {
	
	public boolean insertCheckings(int c, double checkingsBalance) {
        String sql = "INSERT into checkings (balance,customer_id) values (?,?)";
        try (Connection conn = ConnectionFactory.getConnection()) {

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1, checkingsBalance);
            ps.setInt(2, c);

            boolean success = ps.execute();
            
           if( success) {
           return true;
           }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Checkings selectCheckingByCustomerId(int id) {
        Checkings checkings = null;

        String sql = "SELECT * from checkings WHERE customer_id = ?";
        try (Connection conn = ConnectionFactory.getConnection()) {

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                checkings = new Checkings(rs.getInt(1), rs.getDouble(2), rs.getInt(3));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return checkings;
    }

    public boolean updateCheckingsBalance(Customer c, double checkingsBalance) {
        try (Connection conn = ConnectionFactory.getConnection()) {

            String sql = "UPDATE checkings SET balance = ? WHERE customer_id  = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setDouble(1, checkingsBalance);
            ps.setInt(2, c.getCustomerId());

            ps.execute();

            return true;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

}
