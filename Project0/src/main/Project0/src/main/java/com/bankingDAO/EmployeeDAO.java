package com.bankingDAO;

import com.bankingmodels.Employee;

public interface EmployeeDAO {
boolean insertEmployee (Employee E);

    
    Employee selectEmployeeByUsername(String username);

    
    boolean deleteEmployee(Employee E);
}
