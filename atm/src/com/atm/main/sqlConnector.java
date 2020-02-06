package com.atm.main;

import java.sql.Connection;
import java.sql.DriverManager;

public class sqlConnector {
	Connection con=null;
	
	
	public static Connection connect() {
		String url="jdbc:mysql://localhost:3306/atm";
		String user="root";
		String password="12345";
		
		try {
			Connection con=DriverManager.getConnection(url, user, password);
			Class.forName("com.mysql.cj.jdbc.Driver");
			return con;
			}
			 
		catch(Exception e) {
			System.out.println(e);
			 return null;
		}
		
	}

}
