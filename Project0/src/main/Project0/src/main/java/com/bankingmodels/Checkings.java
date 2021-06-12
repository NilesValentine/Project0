package com.bankingmodels;

public class Checkings {
	
	 private int checkingsId;
	    private double checkingsBalance;
	    private int customerId;

	    public Checkings(int checkingsId, double checkingsBalance, int customerId) {
	        this.checkingsBalance = checkingsBalance;
	        this.setCustomerId(customerId);
	        this.checkingsId = checkingsId;
	    }

	    public int getCheckingsId() {
	        return checkingsId;
	    }

	    public void setCheckingsId(int checkingsId) {
	        this.checkingsId = checkingsId;
	    }

	    public double getCheckingsBalance() {
	        return checkingsBalance;
	    }

	    public void setCheckingsBalance(double checkingsBalance) {
	        this.checkingsBalance = checkingsBalance;
	    }

	    public int getCustomerId() {
	        return customerId;
	    }

	    public void setCustomerId(int customerId) {
	        this.customerId = customerId;
	    }

	    @Override
	    public String toString() {
	        return "[$" + checkingsBalance + "]";

	    }

	}


