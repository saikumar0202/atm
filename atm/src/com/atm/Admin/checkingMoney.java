package com.atm.Admin;

public class checkingMoney extends atmMoney {
	private int x=getX();
	private int y=getY();
	private int z=getZ();
	private int total=getTotal();

	public void Moneycheck() {
		getmoney();
		if (getZ() == 0) {
			System.out.println("please enter the amount in multiple of 500");
		}
		if (getY() == 0) {
			System.out.println("please enter the amount in multiple of 2000");
		}
	}

	public void selectiveMoney(int a) {
		int h = 0;
		int f = 0;
		int t = 0;
		x=getX();
		y=getY();
		z=getZ();
		total=getTotal();
		if (a <= total) {
				if (a > 10000) {
					System.err.println("ATM cash Limit exceeds");
				} else {
					if (a >= 2000 && x > 0) {
						t = a / 2000;
					}
					if (a >= 500 && y > 0) {
						f = (a - (t * 2000)) / 500;
					}
					if (a >= 100 && z > 0) {
						h = ((a - (t * 2000)) - (f * 500)) / 100;
					}
				}
			
		} else {
			System.err.println("insuficent money in ATM");
		}
		x = t;
		y = f;
		z = h;
		total = (t * 2000) + (f * 500) + (h * 100);
		lessmoney(x, y, z, total);
	}
}
