package com.bankingpresentation;

import com.bankingfrontend.BankingFrontEnd;
import com.bankingutility.ConnectionFactory;

public class MainDriver {
	
	public static void main(String[] args) {
		ConnectionFactory.getConnection();
		
		BankingFrontEnd bFrontEnd = new BankingFrontEnd();
		bFrontEnd.displayMenu(); //Why does this throw an uncaught NullPointerException?
	}

}
