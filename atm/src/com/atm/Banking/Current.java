package com.atm.Banking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.atm.Admin.checkingMoney;
import com.atm.main.main;
import com.atm.main.sqlConnector;

public class Current extends banking implements accounts {
	Scanner ip = new Scanner(System.in);

	private int withdraw;
	private int balance;
	Connection con = sqlConnector.connect();

	@Override
	public void menu() {
		System.out.println("1. Balance Withdraw");
		System.out.println("2. Balance Equiry");
		System.out.println("3. Mini Statement");
		System.out.println("4. Cancel Transanction");
		try {
			miniStatement mini = new miniStatement();
			int a = Integer.parseInt(ip.nextLine());
			switch (a) {
			case 1:
				withdraw();
				break;
			case 2:
				balanceEquiry();
				System.out.println("Your Current Balance " + balance + " Rs");
				break;
			case 3:
				mini.showTransanction("current", 2);
				break;
			case 4:
				main.exit();
			default:
				System.err.println("Enter Correct Option...!");
				menu();
			}
		} catch (Exception e) {
			System.out.println("Invalid Input..");
			menu();
		}
	}
	@Override
	public void withdraw() {
		checkingMoney cm = new checkingMoney();
		cm.Moneycheck();
		System.out.print("Enter Amount : ");
		int withdraw = ip.nextInt();
		balanceEquiry();
		if(withdraw%100!=0) {
			System.err.println("please enter the amount in multiple of 2000,500,100");
			withdraw();
		}
		if (balance >= withdraw) {
			cm.selectiveMoney(withdraw);
			this.withdraw = withdraw;
			this.balance -= this.withdraw;

			String query = "update bankaccount set currentbalance=" + balance + " where userid=" + getUserid() + "";
			try {
				PreparedStatement pst = con.prepareStatement(query);
				pst.execute();
				miniStatement.transaction(-this.withdraw, 2, getUserid());
				System.out.println("Available balance :" + balance);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("\t\nInsufcient balance\n");
		}
	}
	@Override
	public void balanceEquiry() {
		String query = "select * from bankaccount where userid=?";
		try {
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, getUserid());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				this.balance = rs.getInt("currentbalance");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
