package com.atm.menu;

import java.util.Scanner;

import com.atm.Admin.adminDeposit;
import com.atm.Admin.atmMoney;
import com.atm.Banking.banking;
import com.atm.Deposit.deposit;
import com.atm.MoneyTransfer.transfer;
import com.atm.Register.register;
import com.atm.main.main;

public class Menu {
	Scanner ip=new Scanner(System.in);
	public Menu() {
		System.out.println("\n1.Banking");
		System.out.println("2.Deposit");
		System.out.println("3.Register");
		System.out.println("4.Money Transfer");
		System.out.println("5.Admin");
		System.out.println("6.Change Pin");
		System.out.println("7.exit");
		try {
		int a=Integer.parseInt(ip.nextLine()); 
		
		switch(a) {
		case 1:
			banking b= new banking();
			b.login();
			break;
		case 2:
			deposit d= new deposit();
			d.login();
			break;
		case 3:
			register r=new register();
			r.signup();
			break;
		case 4:
			transfer t= new transfer();
			t.login();
			break;
		case 5:
			adminDeposit ad=new adminDeposit();
			ad.login();
			break;
		case 6:
			pin p= new pin();
			p.changepin();
			break;
		case 7:
			
			System.exit(0);
			break;
			default:
				System.err.println("Enter correct Option");
				new Menu();
		
		}
		}catch(Exception e) {
			System.err.println("invalid input");
			new Menu();
		}
	}
	

}
