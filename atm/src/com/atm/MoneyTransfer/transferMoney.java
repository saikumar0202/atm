package com.atm.MoneyTransfer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.atm.Banking.miniStatement;
import com.atm.main.main;
import com.atm.main.sqlConnector;
import com.atm.menu.login;

public class transferMoney extends transfer {
	private  int withdraw;
	private  int balance;
	
	Connection con=sqlConnector.connect();
	Scanner ip=new Scanner(System.in);
	public transferMoney(String account,int i) throws Exception{
		Transfer(account, i);
		creditTransfer(account, i);
	}
	public void Transfer(String account,int i) throws Exception{
		System.out.print("Enter Amount : ");
		int withdraw=ip.nextInt();
		balance(account,i,1);
		if(balance>=withdraw) {
			this.withdraw=withdraw;
			this.balance-=this.withdraw;
		String query="update bankaccount set "+account+"="+balance+" where userid="+getUserid()+"";
		try {
			PreparedStatement pst=con.prepareStatement(query);
			pst.execute();
			miniStatement.transaction(-this.withdraw, i,getUserid());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}else {
			System.out.println("\t\nInsufcient balance\n");
			main.exit();
		}
	}
	public void creditTransfer(String account,int i) throws Exception{
		
		balance(account,i,2);
		
		
		this.balance+=withdraw;
		
		String query="update bankaccount set "+account+"="+balance+" where userid="+getId()+"";
		try {
			PreparedStatement pst=con.prepareStatement(query);
			pst.execute();
			miniStatement.transaction(this.withdraw, i,getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void balance(String account,int i,int j)
	{
		String query="select * from bankaccount where userid=?";
		try {
			PreparedStatement pst=con.prepareStatement(query);
			if(j==1) {
			pst.setInt(1, getUserid());
			}else if(j==2) {
			pst.setInt(1, getId());
			}
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {	
				if(i==1) {
				this.balance=rs.getInt(account);
				}else if(i==2) {
					this.balance=rs.getInt(account);
				}
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
