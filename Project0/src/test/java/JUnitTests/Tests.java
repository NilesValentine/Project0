package JUnitTests;

import static org.junit.Assert.assertEquals; 

import org.junit.BeforeClass;
import org.junit.Test;
import com.bankingImpl.CheckingsDAOImpl;
import com.bankingImpl.CustomerDAOImpl;
import com.bankingImpl.SavingsDAOImpl;
import com.bankingmodels.Customer;
import com.bankingservice.CustomerService;

public class Tests {

	static CustomerService customerService;

    
    static CheckingsDAOImpl chDao;
    static SavingsDAOImpl sDao;
    static CustomerDAOImpl cDao;

    @BeforeClass
    public static void initialSetup() {
        customerService = new CustomerService();
        @SuppressWarnings("unused")
		Customer customer = new Customer();
        
       // Mockito.when(customer.getCustomerId(1).thenReturn(true));
      //  Mockito.when(customer.getUsername("user").thenReturn(true));
       // Mockito.when(customer.getPassword("password").thenReturn(true));
      //  Mockito.when(customer.getCheckings(50).thenReturn(true));
      //  Mockito.when(customer.setSavings().thenReturn(true));


        System.out.println("Initial setup!");
    }

    @Test
    public void testCreateAccount() {
        String username = "user";
        String password = "password";

        Customer customer = new Customer(0, username, password, null, null);
        assertEquals("user", customer.getUsername());

    }

    @Test
    public void testLogin() {
        String username = "user";
        String password = "password";

        Customer customer = new Customer(0, username, password, null, null);
        assertEquals("password", customer.getPassword());

    }

    @Test
    public void testCheckingsAccount() {

    }

    @Test
    public void testSavingsAccount() {

    }

    @Test
    public void testCheckingDeposit() {

    }

    @Test
    public void testSavingsDeposit() {

    }

    @Test
    public void testCheckingsWithdrawal() {

    }

    @Test
    public void testSavingsWithdrawal() {

    }

	
}

