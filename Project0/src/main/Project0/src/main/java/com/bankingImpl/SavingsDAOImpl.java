package com.bankingImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bankingDAO.SavingsDAO;
import com.bankingmodels.Customer;
import com.bankingmodels.Savings;
import com.bankingutility.ConnectionFactory;

public class SavingsDAOImpl implements SavingsDAO {
	  public boolean insertSavings(int c, double savingsBalance) {
	        String sql = "INSERT into savings (balance,customer_id) values " + "(?,?)";
	        try (Connection conn = ConnectionFactory.getConnection()) {

	            PreparedStatement ps = conn.prepareStatement(sql);
	            ps.setDouble(1, savingsBalance);
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

	    public Savings selectSavingsByCustomerId(int id) {
	        Savings savings = null;

	        String sql = "SELECT * from savings WHERE customer_id = ?";
	        try (Connection conn =  ConnectionFactory.getConnection()) {

	            PreparedStatement ps = conn.prepareStatement(sql);

	            ps.setInt(1, id);

	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	                savings = new Savings(rs.getInt(1), rs.getDouble(2), rs.getInt(3));
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return savings;
	    }

	    public boolean updateSavingsBalance(Customer c, double savingsBalance) {
	        try (Connection conn = ConnectionFactory.getConnection()) {

	            String sql = "UPDATE savings SET balance = ? WHERE customer_id  = ?";

	            PreparedStatement ps = conn.prepareStatement(sql);

	            ps.setDouble(1, savingsBalance);
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
