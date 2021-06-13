package com.bankingImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bankingDAO.CustomerDAO;
import com.bankingmodels.Checkings;
import com.bankingmodels.Customer;
import com.bankingmodels.Savings;
import com.bankingutility.ConnectionFactory;



public class CustomerDAOImpl implements CustomerDAO {

    public CustomerDAOImpl() {
        super();
    }

    public boolean deleteCustomer(Customer c) {
        String sql = "DELETE from customer WHERE username = ?";
        try (Connection conn = ConnectionFactory.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, c.getUsername());

            ps.execute();
            

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean insertCustomer(Customer c) {
        String sql = "INSERT into customer values (default, ?, ?) returning *; ";
        try (Connection conn = ConnectionFactory.getConnection()) {

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, c.getUsername());
            ps.setString(2, c.getPassword());
ResultSet rs = ps.executeQuery();
if (rs.next()) {
	c.setCustomerId(rs.getInt("customer_id"));
	return true;
}
            

            

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    

    public Customer selectCustomerByUsername(String username) {
        Customer customer = null;

        try (Connection conn = ConnectionFactory.getConnection()) {

            String sql = "SELECT * FROM customer WHERE username = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String password = rs.getString(3);
                CheckingsDAOImpl checkingsDaoImpl = new CheckingsDAOImpl();
                Checkings checkings = checkingsDaoImpl.selectCheckingByCustomerId(id);
                SavingsDAOImpl savingsDaoImpl = new SavingsDAOImpl();
                Savings savings = savingsDaoImpl.selectSavingsByCustomerId(id);

                customer = new Customer(id, name, password, checkings, savings);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customer;
    }
    
    public boolean update(Customer c) {
        String sql = "update customer set pending = ? where customer_id = ?;";
        try (Connection conn = ConnectionFactory.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setBoolean(1, c.getPending());
            ps.setInt(2, c.getCustomerId());
            return ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
}
