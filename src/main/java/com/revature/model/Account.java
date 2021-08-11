package com.revature.model;

import java.util.Objects;

public class Account {
	
	double balance; //Current amount of money in the bank
	String accType; //Account number
	int accId; // id number that identifies the account
	int clientId; // id that corresponds to the client that owns that account.
	
	public Account() {
		super();
	}
	
	public Account(int accId, String accType, double balance, int clientId) {
		this.accId = accId;
		this.accType = accType;
		this.balance = balance;
		this.clientId = clientId;
		
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public int getAccId() {
		return accId;
	}

	public void setAccId(int accId) {
		this.accId = accId;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accId, accType, balance, clientId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return accId == other.accId && Objects.equals(accType, other.accType)
				&& Double.doubleToLongBits(balance) == Double.doubleToLongBits(other.balance)
				&& clientId == other.clientId;
	}

	@Override
	public String toString() {
		return "Account [balance=" + balance + ", accType=" + accType + ", accId=" + accId + ", clientId=" + clientId
				+ "]";
	}

	

	
	
	
}
