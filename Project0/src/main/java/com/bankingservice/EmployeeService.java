package com.bankingservice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bankingImpl.CustomerDAOImpl;
import com.bankingImpl.TransactionDAO;
import com.bankingmodels.Customer;
import com.bankingmodels.Employee;
import com.bankingmodels.Transaction;

;

@SuppressWarnings("unused")
public class EmployeeService {
	private static Scanner sc = new Scanner(System.in);

    public static Employee employeeLogin() {
        @SuppressWarnings("unused")
		String username;
        String password;

        System.out.println("Please enter your Username");
        username = sc.nextLine();

        System.out.println("Please enter your Password");
        password = sc.nextLine();

        Employee employee = new Employee(0, "admin", "admin");

        if (employee.getPassword().equals(password)) {
            return employee;
        }
        return null;

    }
    
    public static void approveApplications() {
    	String username;
    	System.out.println("Please enter Customer's username you'd like to approve");
    	username = sc.nextLine();
    	
    	CustomerDAOImpl cDao = new CustomerDAOImpl();
    	Customer customer = cDao.selectCustomerByUsername(username);
    	customer.setPending(true);
    	cDao.update(customer);
    	
    }

    public static void rejectApplications() {
        String username;
        System.out.println("Please enter Customer's Username you would like to reject");
        username = sc.nextLine();

        CustomerDAOImpl cDao = new CustomerDAOImpl();
        Customer customer = cDao.selectCustomerByUsername(username);

        cDao.deleteCustomer(customer);
    }

    public static void viewAccount() {
        String username;
        System.out.println("Please enter Customer's Username");
        username = sc.nextLine();

        CustomerDAOImpl cDao = new CustomerDAOImpl();
        Customer customer = cDao.selectCustomerByUsername(username);
        
         if (customer == null) {
            System.out.println("User does not exist");
        } else {
            System.out.println(customer.toString());
        }
    }
    public static final Logger logger = LogManager.getLogger(EmployeeService.class);
    public static void printLog() throws Exception {
    	List<Transaction> list = (new TransactionDAO()).selectAllTransactions();
    	for( Transaction t: list) {
    		System.out.println(t);
    	}
       


 //System.out.println("1. Approve or reject an account registration by a user");
 //System.out.println("2. View a customer's bank accounts");
 //System.out.println("3. View a log of all transactions");
 
 }
 
 }



