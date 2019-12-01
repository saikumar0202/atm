package com.atm.Banking;

import java.sql.Connection;
import java.util.Scanner;
import com.atm.main.main;
import com.atm.main.sqlConnector;
import com.atm.menu.login;

public class banking extends login {
	Scanner ip = new Scanner(System.in);

	Connection con = sqlConnector.connect();

	public void login() {
		if (checkLogin()) {
			AccountType();
		} else {
			System.out.println("\n\tIncorrected userid or pin\n");
			login();
		}
	}
	public void AccountType() {
		System.out.println("\n1. Savings");
		System.out.println("2. Current");
		System.out.println("3. Exit");
		try {
			chooseAccount choose = new chooseAccount();
			int a = Integer.parseInt(ip.nextLine());
			accounts account=choose.getAccount(a);
			switch (a) {
			case 1:
				account.menu();
				break;
			case 2:
				account.menu();
				break;
			case 3:
				main.exit();
				break;
			default:
				System.err.println("\n\tEnter correct Option..!");
				AccountType();
			}
		} catch (Exception e) {
			System.err.println("\n\tinavalid input");
			AccountType();
		}
	}

}
