package com.atm.Banking;

public class chooseAccount {
	
	public accounts getAccount(int i) {
		if(i==1) {
			return new Savings();
		}else if(i==2) {
			return new Current();
		}else {
			return null;
		}
	}

}
