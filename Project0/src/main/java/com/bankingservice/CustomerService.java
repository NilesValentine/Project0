package com.bankingservice;

import java.util.Scanner;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bankingImpl.CheckingsDAOImpl;
import com.bankingImpl.CustomerDAOImpl;
import com.bankingImpl.SavingsDAOImpl;
import com.bankingmodels.Checkings;
import com.bankingmodels.Customer;
import com.bankingmodels.Savings;


@SuppressWarnings("unused")
public class CustomerService {
	
	final static Logger loggy = LogManager.getLogger(CustomerService.class);
    private static Scanner sc = new Scanner(System.in);

    public static Checkings depositCheckings(Customer c) {
        double amount;
        double balance = c.getCheckings().getCheckingsBalance();

        CheckingsDAOImpl cDao = new CheckingsDAOImpl();

        loggy.info("Please enter the amount you want to deposit into your Checkings");
        loggy.info(amount = sc.nextDouble());
        if (amount >= 0) {
            balance += amount;

            cDao.updateCheckingsBalance(c, balance);

        } else {
            System.out.println("You can not deposit a negative value!!!");
        }
        return new Checkings(0, balance, c.getCustomerId());

    }

    public static Savings depositSavings(Customer c) {
        double amount;
        double balance = c.getSavings().getSavingsBalance();

        SavingsDAOImpl sDao = new SavingsDAOImpl();

        loggy.info("Please enter the amount you want to deposit into your Savings");
        loggy.info(amount = sc.nextDouble());
        if (amount >= 0) {
            balance += amount;

            sDao.updateSavingsBalance(c, balance);

        } else {
            System.out.println("You can not deposit a negative value!!!");
        }
        return new Savings(0, balance, c.getCustomerId());
    }

    public static Checkings withdrawCheckings(Customer c) {
        double amount;
        double balance = c.getCheckings().getCheckingsBalance();

        CheckingsDAOImpl cDao = new CheckingsDAOImpl();

        loggy.info("Please enter the amount you want to withdraw from your Checkings");
        loggy.info(amount = sc.nextDouble());

        if (amount >= 0 && amount < balance) {
            balance -= amount;
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

        loggy.info("Please enter the amount you want to withdraw from your Savings");
        loggy.info(amount = sc.nextDouble());
        if (amount >= 0 && amount < balance) {
            balance -= amount;
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

        loggy.info("Please enter the amount you want to transfer from your Checkings to your Savings");
        loggy.info(amount = sc.nextDouble());

        if (amount >= 0 && amount < chBalance) {
            chBalance -= amount;
            sBalance += amount;
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

        loggy.info("Please enter the amount you want to transfer from your Savings to your Checkings");
        loggy.info(amount = sc.nextDouble());

        if (amount >= 0 && amount < sBalance) {
            chBalance += amount;
            sBalance -= amount;
        } else if (amount > sBalance) {
            System.out.println("You can not have a negative balance!!!");
        } else {
            System.out.println("You can not transfer a negative value!!!");
        }

        cDao.updateCheckingsBalance(c, chBalance);
        sDao.updateSavingsBalance(c, sBalance);

        c.setCheckings(c.getCheckings());
        c.setSavings(c.getSavings());

    }

    public static Customer createAccount() {
        String username;
        String password;

        loggy.info("Please enter a new Username");
        loggy.info(username = sc.nextLine());

        loggy.info("Please enter a Password");
        loggy.info(password = sc.nextLine());

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

        loggy.info("Please enter your Username");
        loggy.info(username = sc.nextLine());

        CustomerDAOImpl cDao = new CustomerDAOImpl();
        Customer customer = cDao.selectCustomerByUsername(username);

        loggy.info("Please enter your Password");
        loggy.info(password = sc.nextLine());

        if (customer.getPassword().equals(password)) {
            return customer;
        }
        return null;

    }

}
