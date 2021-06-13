package com.bankingpresentation;

import java.util.Scanner; 

import com.bankingfrontend.BankingFrontEnd;
import com.bankingmodels.Customer;
// import com.bankingutility.ConnectionFactory;

@SuppressWarnings("unused")
public class MainDriver {
	
	public static void main(String[] args) {
			
	
		//ConnectionFactory.getConnection();
		
	BankingFrontEnd bFrontEnd = new BankingFrontEnd();
	bFrontEnd.displayMenu(); //Why does this throw an uncaught NullPointerException?
	}
	
}


