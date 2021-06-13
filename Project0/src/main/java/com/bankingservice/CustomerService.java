package com.bankingservice;

import java.util.Scanner;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bankingImpl.CheckingsDAOImpl;
import com.bankingImpl.CustomerDAOImpl;
import com.bankingImpl.SavingsDAOImpl;
import com.bankingImpl.TransactionDAO;
import com.bankingmodels.Checkings;
import com.bankingmodels.Customer;
import com.bankingmodels.Savings;
import com.bankingmodels.Transaction;


@SuppressWarnings("unused")
public class CustomerService {
	
	final static Logger loggy = LogManager.getLogger(CustomerService.class);
    private static Scanner sc = new Scanner(System.in);

    public static Checkings depositCheckings(Customer c) {
        double amount;
        double balance = c.getCheckings().getCheckingsBalance(); //Why does this throw a NullPointerException?

        CheckingsDAOImpl cDao = new CheckingsDAOImpl();
TransactionDAO tDao = new TransactionDAO();

        System.out.println("Please enter the amount you want to deposit into your Checkings");
        amount = sc.nextDouble();
        if (amount >= 0) {
            balance += amount;

            cDao.updateCheckingsBalance(c, balance);
            Transaction transaction = new Transaction(0, null, (int) amount, 0, 0, 0, null);
        tDao.insertTransaction(transaction);
        } else {
            System.out.println("You can not deposit a negative value!!!");
        }
        return new Checkings(0, balance, c.getCustomerId());

    }

    public static Savings depositSavings(Customer c) {
        double amount;
        double balance = c.getSavings().getSavingsBalance();

        SavingsDAOImpl sDao = new SavingsDAOImpl();
        TransactionDAO tDao = new TransactionDAO();
        
        System.out.println("Please enter the amount you want to deposit into your Savings");
        amount = sc.nextDouble();
        if (amount >= 0) {
            balance += amount;

            sDao.updateSavingsBalance(c, balance);
            Transaction transaction = new Transaction(0, null, (int) amount, 0, 0, 0, null);
            tDao.insertTransaction(transaction);
        } else {
            System.out.println("You can not deposit a negative value!!!");
        }
        return new Savings(0, balance, c.getCustomerId());
    }

    public static Checkings withdrawCheckings(Customer c) {
        double amount;
        double balance = c.getCheckings().getCheckingsBalance();

        CheckingsDAOImpl cDao = new CheckingsDAOImpl();
        TransactionDAO tDao = new TransactionDAO();

        System.out.println("Please enter the amount you want to withdraw from your Checkings");
        amount = sc.nextDouble();

        if (amount >= 0 && amount < balance) {
            balance -= amount;
            Transaction transaction = new Transaction(0, null, (int) balance, 0, 0, 0, null);
            tDao.insertTransaction(transaction);
            
        } else if (amount > balance) {
            System.out.println("You can not have a negative balance!!!");
        } else {
            System.out.println("You can not withdraw a negative value!!!");
        }

        cDao.updateCheckingsBalance(c, balance);
        return new Checkings(0, balance, c.getCustomerId());
    }

    public static Savings withdrawSavings(Customer c) {
        double amount;
        double balance = c.getSavings().getSavingsBalance();

        SavingsDAOImpl cDao = new SavingsDAOImpl();
        TransactionDAO tDao = new TransactionDAO();
        
        System.out.println("Please enter the amount you want to withdraw from your Savings");
        amount = sc.nextDouble();
        if (amount >= 0 && amount < balance) {
            balance -= amount;
            Transaction transaction = new Transaction(0, null, (int) balance, 0, 0, 0, null);
            tDao.insertTransaction(transaction);
            
        } else if (amount > balance) {
            System.out.println("You can not have a negative balance!!!");
        } else {
            System.out.println("You can not withdraw a negative value!!!");
        }

        cDao.updateSavingsBalance(c, balance);
        return new Savings(0, balance, c.getCustomerId());
    }

    public static void transferCheckings(Customer c) {
        double amount;
        double chBalance = c.getCheckings().getCheckingsBalance();
        double sBalance = c.getSavings().getSavingsBalance();

        CheckingsDAOImpl cDao = new CheckingsDAOImpl();
        SavingsDAOImpl sDao = new SavingsDAOImpl();
        TransactionDAO tDao = new TransactionDAO();

        System.out.println("Please enter the amount you want to transfer from your Checkings to your Savings");
        amount = sc.nextDouble();

        if (amount >= 0 && amount < chBalance) {
            chBalance -= amount;
            sBalance += amount;
            Transaction transaction = new Transaction(0, null, (int) chBalance, 0, 0, 0, null);
            tDao.insertTransaction(transaction);
            
        } else if (amount > chBalance) {
            System.out.println("You can not have a negative balance!!!");
        } else {
            System.out.println("You can not transfer a negative value!!!");
        }

        cDao.updateCheckingsBalance(c, chBalance);
        sDao.updateSavingsBalance(c, sBalance);

        c.setCheckings(c.getCheckings());
        c.setSavings(c.getSavings());

    }

    public static void transferSavings(Customer c) {
        double amount;
        double chBalance = c.getCheckings().getCheckingsBalance();
        double sBalance = c.getSavings().getSavingsBalance();

        CheckingsDAOImpl cDao = new CheckingsDAOImpl();
        SavingsDAOImpl sDao = new SavingsDAOImpl();
        TransactionDAO tDao = new TransactionDAO();

        System.out.println("Please enter the amount you want to transfer from your Savings to your Checkings");
        amount = sc.nextDouble();

        if (amount >= 0 && amount < sBalance) {
            chBalance += amount;
            sBalance -= amount;
            Transaction transaction = new Transaction(0, null, (int) chBalance, 0, 0, 0, null);
            tDao.insertTransaction(transaction);
            
        } else if (amount > sBalance) {
            System.out.println("You can not have a negative balance!!!");
        } else {
            System.out.println("You can not transfer a negative value!!!");
        }

        cDao.updateCheckingsBalance(c, chBalance);
        sDao.updateSavingsBalance(c, sBalance);

        c.setCheckings(c.getCheckings());
        c.setSavings(c.getSavings());
sc.nextLine();
    }

    public static Customer createAccount() {
        String username;
        String password;

        System.out.println("Please enter a new Username");
        username = sc.nextLine();

        System.out.println("Please enter a Password");
        password = sc.nextLine();

        Customer customer = new Customer(0, username, password, null, null);

        CustomerDAOImpl cDao = new CustomerDAOImpl();
         
        cDao.insertCustomer(customer);

        return customer;

    }

    public static Savings createSavings(Customer c) {

        SavingsDAOImpl sDao = new SavingsDAOImpl();
        boolean didInsert = sDao.insertSavings(c.getCustomerId(), 50);

        if (didInsert) {
            System.out.println("Thank you for opening up a Savings Account!");
            System.out.println("Your starting balance is $50!");

            return new Savings(0, 50, c.getCustomerId());
        } else {
            System.out.println("Failed to open a savings");
        }

        return null;

    }

    public static Checkings createCheckings(Customer c) {

        CheckingsDAOImpl chDao = new CheckingsDAOImpl();
        boolean didInsert = chDao.insertCheckings(c.getCustomerId(), 50);

        if (didInsert) {
            System.out.println("Thank you for opening up a Checkings Account!");
            System.out.println("Your starting balance is $50!");

            return new Checkings(0, 50, c.getCustomerId());
        } else {
            System.out.println("Failed to open a checkings");
        }

        return null;

    }

    public static void checkSavingsAccount(Customer c) {

        SavingsDAOImpl sDao = new SavingsDAOImpl();
        sDao.selectSavingsByCustomerId(c.getCustomerId());
        System.out.println(c.getSavings());

    }

    public static void checkCheckingsAccount(Customer c) {

        CheckingsDAOImpl chDao = new CheckingsDAOImpl();
        chDao.selectCheckingByCustomerId(c.getCustomerId());
        System.out.println(c.getCheckings());

    }

    public static Customer customerLogin() {
        String username;
        String password;

        System.out.println("Please enter your Username");
        username = sc.nextLine();

        CustomerDAOImpl cDao = new CustomerDAOImpl();
        Customer customer = cDao.selectCustomerByUsername(username);

        System.out.println("Please enter your Password"); //Why does this throw an uncaught NullPointerException?
        password = sc.nextLine();

        if (customer.getPassword().equals(password)) { //Why does this throw an uncaught NullPointerException?
            return customer;
        } else {
        return null;

    }

}
}
