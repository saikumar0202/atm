package com.atm.Deposit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.atm.Admin.atmMoney;
import com.atm.Banking.miniStatement;
import com.atm.main.sqlConnector;
import com.atm.menu.login;

public class deposit extends login {
	
	private int total;
	private int balance;
	Scanner ip = new Scanner(System.in);
	Connection con=sqlConnector.connect();

	public void login() {
	
		if(checkLogin()) {
			AccountType();
		}else {
			System.out.println("\n\tincorrected userid or pin\n");
			login();
		}
	}
	public void AccountType() {
		System.out.println("1.Savings");
		System.out.println("2.Current");
		int a=ip.nextInt();
		switch(a) {
		case 1:
			money(1);
			break;
		case 2:
			money(2);
			break;
		default:
			System.err.println("Enter Correct Option...!");
			AccountType();
		}
	}
	public void queries(int i) {
		if(i==1) {
			balance("savingbalance");
			miniStatement.transaction(this.total, 1,getUserid());
			total+=balance;
			String query="update bankaccount set savingbalance="+total+" where userid="+getUserid()+"";
			update(query);
		}else if(i==2) {
			balance("currentbalance");
			miniStatement.transaction(this.total, 1,getUserid());
			total+=balance;
			String query="update bankaccount set currentbalance="+total+" where userid="+getUserid()+"";
			update(query);
		}
		
	}
	public void update(String query) {
		try {
			Statement st=con.createStatement();
			st.execute(query);
			
			System.out.println("available balance :"+total);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void money(int i) {
		System.out.print("2000 * ");
		int x=ip.nextInt();
		System.out.print(" 500 * ");
		int y=ip.nextInt();
		System.out.print(" 100 * ");
		int z=ip.nextInt();
		total=(2000*x)+(500*y)+(100*z);
		System.out.println("2000 * "+x);
		System.out.println(" 500 * "+y);
		System.out.println(" 100 * "+z);
		System.out.println("total="+total);
		atmMoney.setmoney(x, y, z, total);
		queries(i);
	}
	public void balance(String account)
	{
		String query="select * from bankaccount where userid=?";
		try {
			PreparedStatement pst=con.prepareStatement(query);
			pst.setInt(1, getUserid());
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {	
				this.balance=rs.getInt(account);	
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
