package com.atm.menu;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.atm.main.sqlConnector;

public class login {
	Scanner ip = new Scanner(System.in);
	Connection con=sqlConnector.connect();
	private int pin;
	private int pin1;
	private static int userid;
	public login() {
	}
	public static int getUserid() {
		return userid;
	}
	public boolean checkLogin() {
		System.out.print("Enter your user id :");
		int userid=ip.nextInt();
		System.out.print("Enter your pin :");
		int pin=ip.nextInt();
		this.userid=userid;
		this.pin=pin;
		String query="select * from bankaccount where userid="+this.userid+"";
		try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			while(rs.next()) {
			int pin1=rs.getInt("pin");
			this.pin1=pin1;
			}
			if(this.pin==this.pin1) {
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			return false;
		}		
	}	
	public boolean checkAdmin() {
		System.out.print("Enter your user id :");
		int userid=ip.nextInt();
		System.out.print("Enter your pin :");
		int pin=ip.nextInt();
		this.userid=userid;
		this.pin=pin;
		String query="select * from admin where userid="+this.userid+"";
		try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			while(rs.next()) {
			int pin1=rs.getInt("pin");
			this.pin1=pin1;
			}
			if(this.pin==this.pin1) {
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			return false;
		}		
	}	
}
