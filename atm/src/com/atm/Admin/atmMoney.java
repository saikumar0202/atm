package com.atm.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.atm.main.sqlConnector;

public class atmMoney {
	
	static int x;
	static int y;
	static int z;
	static int total;
	public static int getX() {
		return x;
	}
	public static int getY() {
		return y;
	}
	public static int getZ() {
		return z;
	}
	public static int getTotal() {
		return total;
	}
	public static void setmoney(int xx,int yy,int zz,int total1) {
		getmoney();
		x+=xx;
		y+=yy;
		z+=zz;
		total+=total1;
		String query="UPDATE `atm`.`money` SET `2000` = '"+x+"', `500` = '"+y+"', `100` = '"+z+"', `total` = '"+total+"' WHERE (`id` = '1')";
		try {
			Connection con=sqlConnector.connect();
			PreparedStatement pst=con.prepareStatement(query);
			pst.execute();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	public static void lessmoney(int xx,int yy,int zz,int total1) {
		getmoney();
		x-=xx;
		y-=yy;
		z-=zz;
		total-=total1;
		String query="UPDATE `atm`.`money` SET `2000` = '"+x+"', `500` = '"+y+"', `100` = '"+z+"', `total` = '"+total+"' WHERE (`id` = '1')";
		try {
			Connection con=sqlConnector.connect();
			PreparedStatement pst=con.prepareStatement(query);
			pst.execute();
			System.out.println("2000 * "+xx);
			System.out.println(" 500 * "+yy);
			System.out.println(" 100 * "+zz);
			System.out.println("total="+total1);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	public static void getmoney() {
		String query="select * from money";
		try {
			Connection con=sqlConnector.connect();
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			while(rs.next()) {
				x=rs.getInt("2000");
				y=rs.getInt("500");
				z=rs.getInt("100");
				total=rs.getInt("total");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
}
