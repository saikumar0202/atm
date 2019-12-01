package com.atm.Register;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

import com.atm.main.sqlConnector;
import com.atm.menu.Menu;

public class register {
	Scanner ip = new Scanner(System.in);
	private String fname;
	private String lname;
	private int userid;
	private int pin;
	Connection con=sqlConnector.connect();

	public void signup() {
		System.out.print("Enter your first name :");
		String fname = ip.nextLine();
		this.fname = fname;
		System.out.print("Enter your Last name :");
		String lname = ip.nextLine();
		this.lname = lname;
		creatPass();
		Random r = new Random();
		int userid = r.ints(1000, 9999).findFirst().getAsInt();
		this.userid = userid;
		System.out.println("\twelcome " + this.fname.toUpperCase() + " " + this.lname.toUpperCase()
				+ " your userid is \"" + this.userid + "\"");
		upload();
		Menu m=new Menu();
	}

	public void creatPass() {
		System.out.print("\nEnter new 4 digit pin : ");
		int pin = ip.nextInt();
		if (pin<=9999 && pin>999) {
			
		
		System.out.print("Re-Enter pin : ");
		int repin = ip.nextInt();
		this.pin = pin;
		if (pin==repin) {
			System.out.println("\tyour pin succesfully created.");
			System.out.println("\t\t_________________________");

		} else {
			System.err.println("\n\tpin mismatch..! Try again");
			creatPass();
		}
		}else {
			System.out.println("pin should be 4 numbers");
			creatPass();
		}
	}
	public int getPin() {
		return pin;
	}

	public void upload() {
		String query="insert into bankaccount values(?,?,?,?,0,0)";
		try {
			PreparedStatement pst=con.prepareStatement(query);
			pst.setString(1, fname);
			pst.setString(2, lname);
			pst.setInt(3, userid);
			pst.setInt(4, pin);
			pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
