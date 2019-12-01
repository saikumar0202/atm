package com.atm.main;

import com.atm.menu.Menu;

public class main extends sqlConnector{
	static {
		System.out.println("welcome to money centre");
	}
	public static void main(String args[]) {
		Menu m=new Menu();
		exit();
	}
	public static void exit() {
		System.out.println("\n\nThank you...");
		System.exit(0);
	}
}
