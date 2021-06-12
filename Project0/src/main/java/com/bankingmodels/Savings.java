package com.bankingmodels;

public class Savings {
	
	private int savingsId;
    private double savingsBalance;
    private int customerId;

    public Savings(int savingsId, double savingsBalance, int customerId) {
        this.savingsId = savingsId;
        this.savingsBalance = savingsBalance;
        this.customerId = customerId;
    }

    public int getSavingsId() {
        return savingsId;
    }
    public void setSavingsId(int savingsId) {
        this.savingsId = savingsId;
    }
    public double getSavingsBalance() {
        return savingsBalance;
    }
    public void setSavingsBalance(double savingsBalance) {
        this.savingsBalance = savingsBalance;
    }
    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    } @Override
    public String toString() {
        return "[$" + savingsBalance + "]";
}


}
