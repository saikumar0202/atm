package com.atm.Banking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.atm.main.sqlConnector;
import com.atm.menu.login;

public class miniStatement extends login{
   static	Connection con=sqlConnector.connect();
	private  String fname;
	private  String lname;
	private static String statement;
	Scanner ip=new Scanner(System.in);
	
	public static void transaction(int input,int i,int id) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("E dd/MM/yyyy hh:mm:ss a");
		if (input > 0) {
			statement="\tCredited " + Math.abs(input) + " Rs on " + sdf.format(date);
		} else if (input < 0) {
			statement="\tDebited " + Math.abs(input) + " Rs on " + sdf.format(date);
		}
		String query="insert into transaction(userid,transanction,account) values ("+id+",'"+statement+"',"+i+")";
		try {
			
			Statement st=con.createStatement();
			st.execute(query);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	public  void showTransanction(String account,int i) {
		String query="select * from transaction where userid=? and account=?";
		try {
			PreparedStatement pst=con.prepareStatement(query);
			pst.setInt(1, getUserid());
			pst.setInt(2, i);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {	
				System.out.println(rs.getString("transanction"));
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		userDetails();
		System.out.println("\t\tID :\t\t" + getUserid());
		System.out.println("\t\tAccount :\t"+account);
		System.out.println("\t\tName :\t\t" + fname + " " +lname);
	}
	
	public void userDetails()
	{
		String query="select * from bankaccount where userid=?";
		try {
			PreparedStatement pst=con.prepareStatement(query);
			pst.setInt(1, getUserid());
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {	
				this.fname=rs.getString("firstname");
				this.lname=rs.getString("lastname");
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
