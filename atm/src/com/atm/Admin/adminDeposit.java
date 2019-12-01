package com.atm.Admin;

import java.util.Scanner;

import com.atm.menu.login;

public class adminDeposit extends login {
	
	Scanner ip=new Scanner(System.in);
	public void login() {
		
		if(checkAdmin()) {
			money();
		}else {
			System.out.println("\n\tincorrected userid or pin\n");
			login();
		}
	}
	public void money() {
		System.out.print("\t2000 * ");
		int x=ip.nextInt();
		System.out.print("\t 500 * ");
		int y=ip.nextInt();
		System.out.print("\t 100 * ");
		int z=ip.nextInt();
		int total=(2000*x)+(500*y)+(100*z);
		atmMoney.setmoney(x, y, z, total);
		
	}

}
