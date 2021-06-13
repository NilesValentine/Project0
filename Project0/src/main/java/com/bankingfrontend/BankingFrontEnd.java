package com.bankingfrontend;

import java.util.Scanner;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import com.bankingmodels.Checkings;
import com.bankingmodels.Customer;
import com.bankingmodels.Employee;
import com.bankingmodels.Savings;
import com.bankingservice.CustomerService;
import com.bankingservice.EmployeeService;


public class BankingFrontEnd {
	
	final static Logger loggy = (Logger) LogManager.getLogger(BankingFrontEnd.class);

         Scanner sc = new Scanner(System.in);

    public void displayMenu() {
    	 loggy.setLevel(Level.INFO);

    	
        Customer customer = null;
        Employee employee = null;
        String loggedIn = "";

        
        System.out.println("WELCOME TO NILES'S BANK");
       

        while (true) {

            if (loggedIn == "") {
                System.out.println("What would you like to do");
                System.out.println("1. Customer Login");
                System.out.println("2. Employee login");
                System.out.println("3. Create account");
                System.out.println("4. Exit");

                int cmd = Integer.parseInt(sc.nextLine());

                switch (cmd) {
                case 1:
                    customer = CustomerService.customerLogin(); //Why does this thrown an uncaught NullPointerException?
                    if (customer == null) {
                        System.out.println("Failed Login");
                    } else {
                        loggedIn = "customer";
                        System.out.println("Login accepted!");
                    }

                    break;
                case 2:
                	 employee = EmployeeService.employeeLogin();
                     if (employee == null) {
                         System.out.println("Failed Login");
                         break;
                     } else {
                         loggedIn = "employee";
                         System.out.println("Welcome to Work!");
                     }
                     break;

                case 3:
                    customer = CustomerService.createAccount();
                    System.out.println("You have successfully created an Account!");
                    break;
                case 4:
                    System.out.println("Exiting");
                    return;
                }

            } else if (loggedIn == "customer") {
                System.out.println("What would you like to do");
                System.out.println("1. Open savings account");
                System.out.println("2. Open checking account");
                System.out.println("3. Check checking account balance");
                System.out.println("4. Check saving account balance");
                System.out.println("5. Deposit into checkings account");
                System.out.println("6. Deposit into savings account");
                System.out.println("7. Withdraw from checkings account");
                System.out.println("8. Withdraw from savings account");
                System.out.println("9. Transfer from Checkings into Savings account");
                System.out.println("10. Transfer from Savings into Checkings account");
                System.out.println("11. Logout");

                int cmd = Integer.parseInt(sc.nextLine());

                switch (cmd) {
                case 1:
                    System.out.println("You have opened a new Savings Account!");
                    Savings s = CustomerService.createSavings(customer);
                    customer.setSavings(s);
                    break;

                case 2:
                    System.out.println("You have opened a new Checkings Account!");
                    Checkings c = CustomerService.createCheckings(customer);
                    customer.setCheckings(c);
                    break;

                case 3:
                    System.out.println("Your Checkings balance is:");
                    CustomerService.checkCheckingsAccount(customer);
                    break;

                case 4:
                    System.out.println("Your savings balance is:");
                    CustomerService.checkSavingsAccount(customer);
                    break;

                case 5:
                    Checkings ch = CustomerService.depositCheckings(customer); //Why does this throw a NullPointerException?
                    customer.setCheckings(ch);
                    break;

                case 6:
                    Savings sh = CustomerService.depositSavings(customer);
                    customer.setSavings(sh);
                    break;

                case 7:
                    Checkings cw = CustomerService.withdrawCheckings(customer);
                    customer.setCheckings(cw);
                    break;

                case 8:
                    Savings sw = CustomerService.withdrawSavings(customer);
                    customer.setSavings(sw);
                    break;

                case 9:
                    CustomerService.transferCheckings(customer);
                    break;

                case 10:
                    CustomerService.transferSavings(customer);
                    break;

                case 11:
                    loggedIn = "";
                    System.out.println("Have a nice day!");
                    break;
                }

                System.out.println();
            } else if (loggedIn == "employee") {
                System.out.println("What would you like to do");
                System.out.println("1. Approve an account registration by a user");
                System.out.println("2. Reject an account registration by a user");
                System.out.println("3. View a customer's bank accounts");
                System.out.println("4. View a log of all transactions");
                System.out.println("5. Logout");

                int cmd = Integer.parseInt(sc.nextLine());

                switch (cmd) {
                case 1:
                    System.out.println("Approve an account registration by a user");
                    EmployeeService.approveApplications();
                    break;
                    
                case 2:
                    System.out.println("Approve or Reject an account registration by a user");
                    EmployeeService.rejectApplications();
                case 3:
                    System.out.println("View a customer's bank accounts");
                    EmployeeService.viewAccount();
                    break;
                case 4:
                    try {
                        EmployeeService.printLog();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    loggedIn = "";
                    System.out.println("Clocking out!");
                    break;
                }

            }
        }

    }
   

}
