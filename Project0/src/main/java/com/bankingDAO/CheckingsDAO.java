package com.bankingDAO;

import com.bankingmodels.Checkings;
import com.bankingmodels.Customer;

public interface CheckingsDAO {
	boolean insertCheckings(int c, double checkingsBalance);

    Checkings selectCheckingByCustomerId(int id);

    boolean updateCheckingsBalance(Customer c, double checkingsBalance);

}
