package com.bankingImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bankingDAO.EmployeeDAO;
import com.bankingmodels.Employee;
import com.bankingutility.ConnectionFactory;

public class EmployeeDAOImpl implements EmployeeDAO {
		
		public EmployeeDAOImpl() {
			super();
		}
		
		public boolean deleteEmployee (Employee E) {
			
			String sql = "DELETE from employee WHERE username =?";
			try (Connection conn = ConnectionFactory.getConnection()) {
	            PreparedStatement ps = conn.prepareStatement(sql);

	            ps.setString(1, E.getUsername());

	            boolean success = ps.execute();
	            
	            if( success) {
	            return true;
	            }


	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }
		
		public boolean insertEmployee (Employee E) {
			String sql = "INSERT into employee (username, password) values " + "(?,?)";
			try (Connection conn = ConnectionFactory.getConnection()) {

	            PreparedStatement ps = conn.prepareStatement(sql);
	            ps.setString(1, E.getUsername());
	            ps.setString(2, E.getPassword());

	boolean success = ps.execute();
	            
	            if( success) {
	            return true;
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false;
		}
		
		public Employee selectEmployeeByUsername (String username) {
			Employee employee = null;
			
			 try (Connection conn = ConnectionFactory.getConnection()) {

		            String sql = "SELECT * from employee WHERE username = ?";
		            
		            PreparedStatement ps = conn.prepareStatement(sql);

		            ps.setString(1, username);

		            ResultSet rs = ps.executeQuery();     
		            
		            while (rs.next()) {
		                int id = rs.getInt(1);
		                String name = rs.getString(2);
		                String password = rs.getString(3);
		                
		                employee = new Employee(id, name, password);
		}
			 }    catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return employee;
		

			
		}

	

}
