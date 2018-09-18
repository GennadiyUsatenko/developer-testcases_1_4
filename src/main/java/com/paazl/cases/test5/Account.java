package com.paazl.cases.test5;

import java.math.BigInteger;

public class Account {
	private BigInteger balance = BigInteger.ZERO;

	public void deposit(int amount) {
		balance = balance.add(BigInteger.valueOf(amount));
	}

	public BigInteger getBalance() {
		return balance;
	}
}