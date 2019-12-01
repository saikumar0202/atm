package com.atm.MoneyTransfer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.atm.main.sqlConnector;
import com.atm.menu.login;

public class transfer extends login {

	private static int id;
	private String fname;
	private String lname;

	public int getId() {
		return id;
	}

	Connection con = sqlConnector.connect();
	Scanner ip = new Scanner(System.in);

	public void login() {
		if (checkLogin()) {
			AccountType();
		} else {
			System.out.println("\n\tincorrected userid or pin\n");
			login();
		}
	}

	public void AccountType() {
		System.out.println("1.Savings");
		System.out.println("2.Current");
		try {
			int a = Integer.parseInt(ip.nextLine());
			switch (a) {
			case 1:
				if (transferid()) {
					System.out.println("Name :" + fname + " " + lname);
					transferMoney t1 = new transferMoney("savingbalance", 1);
					System.out.println("Succesfully money transfer to " + fname + " " + lname + " Savings account");
				}else {
					System.err.println("sorry  no Account Exist with userid");
					AccountType();
				}
				break;
			case 2:
				if (transferid()) {
					System.out.println("Name :" + fname + " " + lname);
					transferMoney t = new transferMoney("currentbalance", 2);
					System.out.println("Succesfully money transfer to " + fname + " " + lname + " Current account");
				}else {
					System.err.println("sorry  no Account Exist with userid");
					AccountType();
				}
				break;
			default:
				System.err.println("Enter Correct Option...!");
				AccountType();
			}
		} catch (Exception e) {
			System.err.println("\n\tinavalid input");
			AccountType();
		}

	}

	public boolean transferid() {
		System.out.print("Enter transfer userid :");
		int id = ip.nextInt();
		this.id = id;
		String query = "select * from bankaccount where userid=" + this.id + "";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			if (rs.next()) {
				fname = rs.getString("firstname");
				lname = rs.getString("lastname");
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			return false;
		}
	}
}
