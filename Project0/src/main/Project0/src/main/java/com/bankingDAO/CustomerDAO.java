package com.bankingDAO;

import com.bankingmodels.Customer;

public interface CustomerDAO {
	boolean insertCustomer(Customer c);

    
    Customer selectCustomerByUsername(String username);

    
    boolean deleteCustomer(Customer c);
}
