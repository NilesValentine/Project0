package com.bankingservice;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

import com.bankingImpl.CustomerDAOImpl;
import com.bankingmodels.Customer;
import com.bankingmodels.Employee;

;

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

    public static void printLog() throws Exception {

        File file = new File("/Users/nilsev/jdbc:postgresql://database-project0.cgjdktnpvary.us-east-2.rds.amazonaws.com:5432/postgressrc/main/log4j-application.log");
        
		@SuppressWarnings("resource")
		FileInputStream fis = new FileInputStream(file);

        int oneByte;
        while ((oneByte = fis.read()) != -1) {
            System.out.write(oneByte);
          
        }
        System.out.flush();



 System.out.println("1. Approve or reject an account registration by a user");
 System.out.println("2. View a customer's bank accounts");
 System.out.println("3. View a log of all transactions");
 
 }
 
 }



