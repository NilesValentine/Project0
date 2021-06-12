package com.bankingDAO;

import com.bankingmodels.Customer;
import com.bankingmodels.Savings;

public interface SavingsDAO {
	
    boolean insertSavings(int c, double savingsBalance);

    
    Savings selectSavingsByCustomerId(int id);

    
    boolean updateSavingsBalance(Customer c, double savingsBalance);
}
