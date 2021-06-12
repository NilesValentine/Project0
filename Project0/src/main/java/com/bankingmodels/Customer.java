package com.bankingmodels;

public class Customer {
	private int customerId;
    private String username;
    private String password;
    private Checkings checkings;
    private Savings savings;
    public Customer getCustomerId;

    
    public Customer() {
        super();
    }
    public Checkings getCheckings() {
        return checkings;
    }
    public void setCheckings(Checkings checkings) {
        this.checkings = checkings;
    }
    public Savings getSavings() {
        return savings;
    }
    public void setSavings(Savings savings) {
        this.savings = savings;
    }
    public Customer(int customerId, String username, String password, Checkings checkings, Savings savings) {
        this.customerId = customerId;
        this.username = username;
        this.password = password;
        this.checkings = checkings;
        this.savings = savings;
    }
    
    
    
    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }   @Override
    public String toString() {
        return "Customer [customerId= " + customerId + ", username= " + username + ", password= " + password
                + ",  Checkings=" + getCheckings() + ", Savings=" + getSavings() + "]";
    }
	

}
