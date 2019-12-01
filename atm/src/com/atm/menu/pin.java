package com.atm.menu;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.atm.Register.register;
import com.atm.main.sqlConnector;

public class pin extends register {
	Scanner ip = new Scanner(System.in);
	Connection con=sqlConnector.connect();
	private int pin;
	
	private static int userid;
	
	public void changepin() {
		if(checkpin()) {
			creatPass();
			pin=getPin();
			uploadpin();
		}
	}
	public boolean checkpin() {
		System.out.print("Enter your userid");
		int userpin=ip.nextInt();
		this.userid=userpin;
		String query="select * from bankaccount where userid="+this.userid+"";
		try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			while(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			return false;
		}
		return false;		
	}	
	public void uploadpin() {
		
		String query="update bankaccount set pin="+this.pin+" where userid="+this.userid+"";
		try {
			Statement st=con.createStatement();
			st.executeUpdate(query);
			
		} catch (SQLException e) {
		}
	}	

}
