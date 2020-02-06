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

	public boolean selectiveMoney(int a) {
		int hundreds = 0;
		int fiveHundres = 0;
		int twoThousands = 0;
		x=getX();
		y=getY();
		z=getZ();
		total=getTotal();
		if (a <= total) {
				if (a > 10000) {
					System.err.println("ATM cash Limit exceeds");
				} else {
					if (a >= 2000 && x > 0) {
						twoThousands = a / 2000;
					}
					if (a >= 500 && y > 0) {
						fiveHundres = (a - (twoThousands * 2000)) / 500;
					}
					if (a >= 100 && z > 0) {
						hundreds = ((a - (twoThousands * 2000)) - (fiveHundres * 500)) / 100;
					}
				}
			
		} else {
			System.err.println("insuficent money in ATM");
			return false;
		}
//		x = twoThousands;
//		y = fiveHundres;
//		z = hundreds;
		total = (twoThousands * 2000) + (fiveHundres * 500) + (hundreds * 100);
		if(x>=twoThousands&&y>=fiveHundres&&z>=hundreds) {
		lessmoney(twoThousands, fiveHundres, hundreds, total);
		}else {
			System.err.println("insuficent money in ATM");
			return false;
		}
		return true;
	}
}













